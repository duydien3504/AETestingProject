package tests.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import java.util.HashMap;
import java.util.Map;

public class LGGetToken {

    public static String getToken(String email, String password) {
        String token = null;

        try (Playwright playwright = Playwright.create()) {
            APIRequestContext requestContext = playwright.request().newContext(
                    new APIRequest.NewContextOptions());

            Map<String, String> data = new HashMap<>();
            data.put("email", email);
            data.put("password", password);

            APIResponse response = requestContext.post("/api/login",
                    RequestOptions.create().setData(data));

            if (response.status() == 200) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode rootNode = mapper.readTree(response.body());

                    JsonNode tokenNode = rootNode.path("data").path("token");

                    if (!tokenNode.isMissingNode()) {
                        token = tokenNode.asText();
                    } else {
                        System.out.println("Không tìm thấy trường token trong response!");
                        System.out.println("Response body: " + new String(response.body()));
                    }

                } catch (Exception e) {
                    System.err.println("Lỗi khi parse JSON: " + e.getMessage());
                }
            } else {
                System.out.println("Đăng nhập thất bại! Status code: " + response.status());
                System.out.println("Response body: " + new String(response.body()));
            }

            requestContext.dispose();
        }

        return token;
    }
}
