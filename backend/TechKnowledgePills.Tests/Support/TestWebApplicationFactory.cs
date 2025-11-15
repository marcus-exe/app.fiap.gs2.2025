using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using TechKnowledgePills.Infrastructure.Data;

namespace TechKnowledgePills.Tests.Support;

public class TestWebApplicationFactory : WebApplicationFactory<Program>
{
    private static readonly string TestConnectionString = 
        Environment.GetEnvironmentVariable("TEST_DB_CONNECTION") ?? 
        "Host=localhost;Port=5433;Database=techknowledgepills_test;Username=postgres;Password=postgres";

    protected override void ConfigureWebHost(IWebHostBuilder builder)
    {
        builder.ConfigureAppConfiguration((context, config) =>
        {
            // Override configuration for testing
            config.AddInMemoryCollection(new Dictionary<string, string?>
            {
                { "Jwt:Key", "YourSuperSecretKeyThatShouldBeAtLeast32CharactersLong!" },
                { "Jwt:Issuer", "TechKnowledgePills" },
                { "Jwt:Audience", "TechKnowledgePillsUsers" },
                { "ConnectionStrings:DefaultConnection", TestConnectionString }
            });
        });

        builder.ConfigureServices(services =>
        {
            // Remove the real database
            var descriptor = services.SingleOrDefault(
                d => d.ServiceType == typeof(DbContextOptions<ApplicationDbContext>));

            if (descriptor != null)
            {
                services.Remove(descriptor);
            }

            // Use PostgreSQL for testing (Docker)
            var useDocker = Environment.GetEnvironmentVariable("USE_DOCKER_DB")?.ToLower() == "true" ||
                           !string.IsNullOrEmpty(Environment.GetEnvironmentVariable("TEST_DB_CONNECTION"));

            if (useDocker)
            {
                // Use PostgreSQL from Docker
                services.AddDbContext<ApplicationDbContext>(options =>
                {
                    options.UseNpgsql(TestConnectionString, npgsqlOptions =>
                    {
                        npgsqlOptions.EnableRetryOnFailure(
                            maxRetryCount: 3,
                            maxRetryDelay: TimeSpan.FromSeconds(5),
                            errorCodesToAdd: null);
                    });
                });
            }
            else
            {
                // Fallback to in-memory database
                services.AddDbContext<ApplicationDbContext>(options =>
                {
                    options.UseInMemoryDatabase($"TestDb_{Guid.NewGuid()}");
                });
            }

            // Build the service provider
            var sp = services.BuildServiceProvider();

            // Create a scope to obtain a reference to the database context
            using (var scope = sp.CreateScope())
            {
                var scopedServices = scope.ServiceProvider;
                var db = scopedServices.GetRequiredService<ApplicationDbContext>();

                // Ensure the database is created and migrations are applied
                try
                {
                    db.Database.EnsureDeleted(); // Clean up any existing test data
                    db.Database.EnsureCreated();
                }
                catch (Exception ex)
                {
                    // If using Docker, wait a bit and retry
                    if (useDocker)
                    {
                        System.Threading.Thread.Sleep(2000);
                        db.Database.EnsureDeleted();
                        db.Database.EnsureCreated();
                    }
                    else
                    {
                        throw;
                    }
                }
            }
        });
    }

    protected override void Dispose(bool disposing)
    {
        if (disposing)
        {
            // Clean up test database
            try
            {
                using var scope = Services.CreateScope();
                var db = scope.ServiceProvider.GetRequiredService<ApplicationDbContext>();
                db.Database.EnsureDeleted();
            }
            catch
            {
                // Ignore cleanup errors
            }
        }
        base.Dispose(disposing);
    }
}

