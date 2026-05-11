package tests.ui;

import com.microsoft.playwright.Page;
import config.ConfigReader;
import core.PlaywrightFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseUITest {
    protected PlaywrightFactory pf;
    protected Page page;

    @BeforeMethod
    public void setupUI() {
        pf = new PlaywrightFactory();
        page = pf.initBrowser();
        page.navigate(ConfigReader.getBaseUrl());
    }

    @AfterMethod
    public void tearDownUI() {
        page.context().browser().close();
    }
}
