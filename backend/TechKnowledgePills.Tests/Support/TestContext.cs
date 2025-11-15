using System.Net.Http.Headers;
using TechKnowledgePills.Core.DTOs;

namespace TechKnowledgePills.Tests.Support;

public class TestContext
{
    public HttpClient? Client { get; set; }
    public HttpResponseMessage? Response { get; set; }
    public AuthResponse? AuthResponse { get; set; }
    public string? AuthToken { get; set; }
    public RegisterRequest? RegisterRequest { get; set; }
    public LoginRequest? LoginRequest { get; set; }
    public ContentDto? ContentDto { get; set; }
    public StressIndicatorDto? StressIndicatorDto { get; set; }
    public int? CreatedUserId { get; set; }
    public int? CreatedContentId { get; set; }
    public int? CreatedStressIndicatorId { get; set; }

    public void SetAuthToken(string token)
    {
        AuthToken = token;
        if (Client != null)
        {
            Client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
        }
    }

    public void ClearAuthToken()
    {
        AuthToken = null;
        if (Client != null)
        {
            Client.DefaultRequestHeaders.Authorization = null;
        }
    }
}

