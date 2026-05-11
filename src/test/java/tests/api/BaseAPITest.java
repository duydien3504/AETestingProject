package tests.api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BaseAPITest {
    protected ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    protected ThreadLocal<APIRequestContext> requestContext = new ThreadLocal<>();

    @BeforeMethod
    public void setupAPI() {
        tlPlaywright.set(Playwright.create());
        createRequestContext(null);
    }

    protected void createRequestContext(String token) {
        if (requestContext.get() != null) {
            requestContext.get().dispose();
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        if (token != null && !token.isEmpty()) {
            headers.put("Authorization", "Bearer " + token);
        }

        APIRequestContext context = tlPlaywright.get().request().newContext(
                new APIRequest.NewContextOptions()
                        .setExtraHTTPHeaders(headers)
        );

        requestContext.set(context);
    }

    @AfterMethod
    public void tearDownAPI() {
        if (requestContext.get() != null) {
            requestContext.get().dispose();
            requestContext.remove();
        }
        if (tlPlaywright.get() != null) {
            tlPlaywright.get().close();
            tlPlaywright.remove();
        }
    }
}
