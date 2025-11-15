using TechKnowledgePills.Tests.Support;
using TechTalk.SpecFlow;

namespace TechKnowledgePills.Tests.Support;

[Binding]
public class Hooks
{
    private readonly TestContext _context;

    public Hooks()
    {
        _context = TestDependencies.GetContext();
    }

    [BeforeScenario]
    public void BeforeScenario()
    {
        // Reset context for each scenario
        _context.Response = null;
        _context.AuthResponse = null;
        _context.AuthToken = null;
        _context.RegisterRequest = null;
        _context.LoginRequest = null;
        _context.ContentDto = null;
        _context.StressIndicatorDto = null;
        _context.CreatedUserId = null;
        _context.CreatedContentId = null;
        _context.CreatedStressIndicatorId = null;

        // Create a new client for each scenario
        _context.Client = _factory.CreateClient();
    }

    [AfterScenario]
    public void AfterScenario()
    {
        // Cleanup if needed
        _context.Client?.Dispose();
    }
}

