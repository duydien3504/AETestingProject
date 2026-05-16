package tests.ui;

import listeners.TestListener;
import models.LoginData;
import models.RegistrationData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import utils.ExcelUtils;
import utils.ExtentTestManager;

import java.util.List;

@Listeners(TestListener.class)
public class LoginTest extends BaseUITest{
    private LoginPage lp;
    private RegisterPage rp;

    @BeforeMethod
    public void setupTest() {
        lp = new LoginPage(page);
        rp = new RegisterPage(page);
    }

    @DataProvider(name = "LoginDataProvider")
    public Object[][] getData(java.lang.reflect.Method method) {
        List<LoginData> dataList = ExcelUtils.getTestData("DataTest_Login.xlsx", LoginData.class);
        String methodName = method.getName();
        LoginData rowData = dataList.stream()
                .filter(data -> data.getId().startsWith(methodName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Khong tim thay data cho: " + methodName));
        return new Object[][] {{rowData}};
    }

    @Test(dataProvider = "LoginDataProvider",
        description = "Login successfully with valid credentials - Positive")
    public void TC001(LoginData data) {
        lp.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rp.clickSignupBtn();
        ExtentTestManager.info("Den trang dang nhap");

        lp.fillEmail(data.getEmailAddress());
        ExtentTestManager.info("Nhap email");

        lp.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap password");

        lp.clickLoginBtn();
        ExtentTestManager.info("Dang nhap");

        Assert.assertTrue(lp.isLoginSuccess());
    }

    @Test(dataProvider = "LoginDataProvider",
            description = "Leave Email Address field blank - Negative")
    public void TC002(LoginData data) {
        lp.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rp.clickSignupBtn();
        ExtentTestManager.info("Den trang dang nhap");

        lp.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap password");

        lp.clickLoginBtn();
        ExtentTestManager.info("Dang nhap");

        Assert.assertTrue(lp.isEmailEmpty());
    }


    @Test(dataProvider = "LoginDataProvider",
            description = "Leave Password field blank - Negative")
    public void TC003(LoginData data) {
        lp.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rp.clickSignupBtn();
        ExtentTestManager.info("Den trang dang nhap");

        lp.fillEmail(data.getEmailAddress());
        ExtentTestManager.info("Nhap email");

        lp.clickLoginBtn();
        ExtentTestManager.info("Dang nhap");

        Assert.assertTrue(lp.isPasswordEmpty());
    }

    @Test(dataProvider = "LoginDataProvider",
            description = "Leave both Email and Password fields blank - Negative")
    public void TC004(LoginData data) {
        lp.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rp.clickSignupBtn();
        ExtentTestManager.info("Den trang dang nhap");

        lp.clickLoginBtn();
        ExtentTestManager.info("Dang nhap");

        Assert.assertTrue(lp.isEmailEmpty() && lp.isPasswordEmpty());
    }

    @Test(dataProvider = "LoginDataProvider",
            description = "Enter an invalid email format - Negative")
    public void TC005(LoginData data) {
        lp.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rp.clickSignupBtn();
        ExtentTestManager.info("Den trang dang nhap");

        lp.fillEmail(data.getEmailAddress());
        ExtentTestManager.info("Nhap email");

        lp.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap password");

        lp.clickLoginBtn();
        ExtentTestManager.info("Dang nhap");

        Assert.assertTrue(lp.isEmailIvl());
    }

    @Test(dataProvider = "LoginDataProvider",
            description = "Enter an unregistered email address - Negative")
    public void TC006(LoginData data) {
        lp.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rp.clickSignupBtn();
        ExtentTestManager.info("Den trang dang nhap");

        lp.fillEmail(data.getEmailAddress());
        ExtentTestManager.info("Nhap email");

        lp.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap password");

        lp.clickLoginBtn();
        ExtentTestManager.info("Dang nhap");

        Assert.assertTrue(lp.isEmailUnkwown());
    }

    @Test(dataProvider = "LoginDataProvider",
            description = "Enter an incorrect password - Negative")
    public void TC007(LoginData data) {
        lp.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rp.clickSignupBtn();
        ExtentTestManager.info("Den trang dang nhap");

        lp.fillEmail(data.getEmailAddress());
        ExtentTestManager.info("Nhap email");

        lp.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap password");

        lp.clickLoginBtn();
        ExtentTestManager.info("Dang nhap");

        Assert.assertTrue(lp.isCorrectPass());
    }

    @Test(dataProvider = "LoginDataProvider",
            description = "Enter a password with spaces in between characters - Negative")
    public void TC008(LoginData data) {
        lp.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rp.clickSignupBtn();
        ExtentTestManager.info("Den trang dang nhap");

        lp.fillEmail(data.getEmailAddress());
        ExtentTestManager.info("Nhap email");

        lp.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap password");

        lp.clickLoginBtn();
        ExtentTestManager.info("Dang nhap");

        Assert.assertTrue(lp.isCorrectPass());
    }

    @Test(dataProvider = "LoginDataProvider",
            description = "Enter an email with leading or trailing spaces - Boundary")
    public void TC009(LoginData data) {
        lp.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rp.clickSignupBtn();
        ExtentTestManager.info("Den trang dang nhap");

        lp.fillEmail(data.getEmailAddress());
        ExtentTestManager.info("Nhap email");

        lp.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap password");

        lp.clickLoginBtn();
        ExtentTestManager.info("Dang nhap");

        Assert.assertTrue(lp.isLoginSuccess());
    }
}
