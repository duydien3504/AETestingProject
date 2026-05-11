package core;

import com.microsoft.playwright.*;
import config.ConfigReader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class PlaywrightFactory {
    private static final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> tlbBrowserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlbBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public Page initBrowser() {
        boolean isHeadless = Boolean.parseBoolean(ConfigReader.getHeadLess());
        tlPlaywright.set(Playwright.create());

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(isHeadless)
                .setArgs(Arrays.asList("--start-maximized"));

        String browserName = ConfigReader.getBrowser().toLowerCase();

        switch (ConfigReader.getBrowser()) {
            case "chrome":
                options.setChannel("chrome");
                tlBrowser.set(getPlaywright().chromium().launch(options));
                break;
            case "firefox":
                tlBrowser.set(getPlaywright().firefox().launch(options));
                break;
            default:
                tlBrowser.set(getPlaywright().chromium().launch(options));
                break;
        }

        tlbBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(null)));
        tlPage.set(getBrowserContext().newPage());
        return getPage();
    }

    public void quitBrowser() {
        if(tlPage.get() != null) {
            tlPage.get().close();
            tlbBrowserContext.get().close();
            tlBrowser.get().close();
            tlPlaywright.get().close();

            tlPlaywright.remove();
            tlbBrowserContext.remove();
            tlBrowser.remove();
            tlPlaywright.remove();
        }
    }

    public static String takeScreenShot() {
        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots" + System.currentTimeMillis()+".png")).setFullPage(true));
        return Base64.getEncoder().encodeToString(buffer);
    }
}
