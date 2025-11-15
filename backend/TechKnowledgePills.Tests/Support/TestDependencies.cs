using TechKnowledgePills.Tests.Support;
using TechTalk.SpecFlow;

namespace TechKnowledgePills.Tests.Support;

[Binding]
public class TestDependencies
{
    private static TestWebApplicationFactory? _factory;
    private static TestContext? _context;

    [BeforeTestRun]
    public static void BeforeTestRun()
    {
        _factory = new TestWebApplicationFactory();
        _context = new TestContext();
    }

    [AfterTestRun]
    public static void AfterTestRun()
    {
        _factory?.Dispose();
    }

    public static TestWebApplicationFactory GetFactory()
    {
        return _factory ??= new TestWebApplicationFactory();
    }

    public static TestContext GetContext()
    {
        return _context ??= new TestContext();
    }
}

