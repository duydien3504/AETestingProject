package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import config.ConfigReader;

public class BasePage {
    protected final Page page;
    String baseUrl = ConfigReader.getBaseUrl();

    public BasePage(Page page) {
        this.page = page;
    }

    public void navigateWebsite() {
        page.navigate(baseUrl);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void clickElement(String selector) {
        page.waitForSelector(selector);
        page.click(selector);
        page.waitForTimeout(500);
    }

    public void fillElement(String selectors, String inputValue) {
        if (inputValue != null) {
            page.waitForSelector(selectors);
            page.fill(selectors, inputValue);
            page.waitForTimeout(500);
        }
    }
}
