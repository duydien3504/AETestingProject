package tests.ui;

import com.microsoft.playwright.Page;
import config.ConfigReader;
import core.PlaywrightFactory;
import com.microsoft.playwright.options.WaitUntilState;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseUITest {
    protected PlaywrightFactory pf;
    protected Page page;

    @BeforeMethod
    public void setupUI() {
        pf = new PlaywrightFactory();
        page = pf.initBrowser();
        page.route("**/*googlesyndication*", route -> route.abort());
        page.route("**/*googleadservices*", route -> route.abort());
        page.route("**/*googleads*", route -> route.abort());
        page.route("**/*doubleclick*", route -> route.abort());
        page.route("**/*adnxs*", route -> route.abort());
        page.route("**/*adsystem*", route -> route.abort());
        page.route("**/*adsbygoogle*", route -> route.abort());
        page.route("**/*adservice*", route -> route.abort());
        page.route("**/*aswift*", route -> route.abort());
        page.navigate(ConfigReader.getBaseUrl(),
                new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
    }

    @AfterMethod
    public void tearDownUI(ITestResult result) {
        if (page != null) {
            com.microsoft.playwright.Video video = page.video();
            page.context().browser().close();
            if (result.getStatus() == ITestResult.SUCCESS && video != null) {
                video.delete();
            }
        }
    }
}
