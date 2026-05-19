package tests.ui;

import listeners.TestListener;
import models.ContactUsData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import utils.ExcelUtils;
import utils.ExtentTestManager;

import java.util.List;

@Listeners(TestListener.class)
public class ContactUsTest extends BaseUITest {
    private ContactUsPage cup;

    @BeforeMethod
    public void setupTest() {
        cup = new ContactUsPage(page);
    }

    @DataProvider(name = "ContactUsDataProvider")
    public Object[][] getData(java.lang.reflect.Method method) {
        List<ContactUsData> dataList = ExcelUtils.getTestData("DataTest_ContactUs.xlsx", ContactUsData.class);
        String methodName = method.getName();
        ContactUsData rowData = dataList.stream()
                .filter(data -> data.getId().startsWith(methodName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Khong tim thay data cho: " + methodName));
        return new Object[][] { { rowData } };
    }

    @Test(dataProvider = "ContactUsDataProvider", description = "Submit contact us form successfully - Happy Path")
    public void TC001(ContactUsData data) {
        cup.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        cup.clickContactUsLink();
        ExtentTestManager.info("Di chuyen den trang Contact Us");

        cup.fillName(data.getName());
        ExtentTestManager.info("Nhap ten: " + data.getName());

        cup.fillEmail(data.getEmail());
        ExtentTestManager.info("Nhap email: " + data.getEmail());

        cup.fillSubject(data.getSubject());
        ExtentTestManager.info("Nhap subject: " + data.getSubject());

        cup.fillMessage(data.getMessage());
        ExtentTestManager.info("Nhap message: " + data.getMessage());

        cup.clickSubmit();
        ExtentTestManager.info("Submit form Contact Us");

        Assert.assertTrue(cup.isSuccessAlertVisible());
        ExtentTestManager.info("Xac nhan form submit thanh cong");
    }

    @Test(dataProvider = "ContactUsDataProvider", description = "Leave Name field blank - Negative Case")
    public void TC002(ContactUsData data) {
        cup.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        cup.clickContactUsLink();
        ExtentTestManager.info("Di chuyen den trang Contact Us");

        cup.fillEmail(data.getEmail());
        ExtentTestManager.info("Nhap email: " + data.getEmail());

        cup.fillSubject(data.getSubject());
        ExtentTestManager.info("Nhap subject: " + data.getSubject());

        cup.fillMessage(data.getMessage());
        ExtentTestManager.info("Nhap message: " + data.getMessage());

        cup.clickSubmit();
        ExtentTestManager.info("Submit form Contact Us");

        Assert.assertTrue(cup.isNameFieldInvalid());
        ExtentTestManager.info("Xac nhan loi Name is required");
    }

    @Test(dataProvider = "ContactUsDataProvider", description = "Leave Email field blank - Negative Case")
    public void TC003(ContactUsData data) {
        cup.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        cup.clickContactUsLink();
        ExtentTestManager.info("Di chuyen den trang Contact Us");

        cup.fillName(data.getName());
        ExtentTestManager.info("Nhap ten: " + data.getName());

        cup.fillSubject(data.getSubject());
        ExtentTestManager.info("Nhap subject: " + data.getSubject());

        cup.fillMessage(data.getMessage());
        ExtentTestManager.info("Nhap message: " + data.getMessage());

        cup.clickSubmit();
        ExtentTestManager.info("Submit form Contact Us");

        Assert.assertTrue(cup.isEmailFieldInvalid());
        ExtentTestManager.info("Xac nhan loi Email is required");
    }

    @Test(dataProvider = "ContactUsDataProvider", description = "Enter invalid email format - Negative Case")
    public void TC004(ContactUsData data) {
        cup.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        cup.clickContactUsLink();
        ExtentTestManager.info("Di chuyen den trang Contact Us");

        cup.fillName(data.getName());
        ExtentTestManager.info("Nhap ten: " + data.getName());

        cup.fillEmail(data.getEmail());
        ExtentTestManager.info("Nhap email: " + data.getEmail());

        cup.fillSubject(data.getSubject());
        ExtentTestManager.info("Nhap subject: " + data.getSubject());

        cup.fillMessage(data.getMessage());
        ExtentTestManager.info("Nhap message: " + data.getMessage());

        cup.clickSubmit();
        ExtentTestManager.info("Submit form Contact Us");

        Assert.assertTrue(cup.isEmailFieldInvalid());
        ExtentTestManager.info("Xac nhan loi Invalid email format");
    }

    @Test(dataProvider = "ContactUsDataProvider", description = "Leave Subject field blank - Negative Case")
    public void TC005(ContactUsData data) {
        cup.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        cup.clickContactUsLink();
        ExtentTestManager.info("Di chuyen den trang Contact Us");

        cup.fillName(data.getName());
        ExtentTestManager.info("Nhap ten: " + data.getName());

        cup.fillEmail(data.getEmail());
        ExtentTestManager.info("Nhap email: " + data.getEmail());

        cup.fillMessage(data.getMessage());
        ExtentTestManager.info("Nhap message: " + data.getMessage());

        cup.clickSubmit();
        ExtentTestManager.info("Submit form Contact Us");

        Assert.assertTrue(cup.isSubjectFieldInvalid());
        ExtentTestManager.info("Xac nhan loi Subject is required");
    }

    @Test(dataProvider = "ContactUsDataProvider", description = "Leave Message field blank - Negative Case")
    public void TC006(ContactUsData data) {
        cup.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        cup.clickContactUsLink();
        ExtentTestManager.info("Di chuyen den trang Contact Us");

        cup.fillName(data.getName());
        ExtentTestManager.info("Nhap ten: " + data.getName());

        cup.fillEmail(data.getEmail());
        ExtentTestManager.info("Nhap email: " + data.getEmail());

        cup.fillSubject(data.getSubject());
        ExtentTestManager.info("Nhap subject: " + data.getSubject());

        cup.clickSubmit();
        ExtentTestManager.info("Submit form Contact Us");

        Assert.assertTrue(cup.isMessageFieldInvalid());
        ExtentTestManager.info("Xac nhan loi Message is required");
    }

    @Test(dataProvider = "ContactUsDataProvider", description = "Submit contact us form with file upload - Happy Path")
    public void TC007(ContactUsData data) {
        cup.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        cup.clickContactUsLink();
        ExtentTestManager.info("Di chuyen den trang Contact Us");

        cup.fillName(data.getName());
        ExtentTestManager.info("Nhap ten: " + data.getName());

        cup.fillEmail(data.getEmail());
        ExtentTestManager.info("Nhap email: " + data.getEmail());

        cup.fillSubject(data.getSubject());
        ExtentTestManager.info("Nhap subject: " + data.getSubject());

        cup.fillMessage(data.getMessage());
        ExtentTestManager.info("Nhap message: " + data.getMessage());

        cup.uploadFile(data.getFileUpload());
        ExtentTestManager.info("Upload file tu duong dan: " + data.getFileUpload());

        cup.clickSubmit();
        ExtentTestManager.info("Submit form Contact Us");

        Assert.assertTrue(cup.isSuccessAlertVisible());
        ExtentTestManager.info("Xac nhan form submit thanh cong kem file dinh kem");

        cup.clickHomeButton();
        ExtentTestManager.info("Click nut Home va quay ve trang chu");
    }

    @Test(dataProvider = "ContactUsDataProvider", description = "Leave all mandatory fields blank - Negative Case")
    public void TC008(ContactUsData data) {
        cup.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        cup.clickContactUsLink();
        ExtentTestManager.info("Di chuyen den trang Contact Us");

        cup.clickSubmit();
        ExtentTestManager.info("Submit form Contact Us");

        Assert.assertTrue(cup.isNameFieldInvalid() || cup.isEmailFieldInvalid() || cup.isSubjectFieldInvalid()
                || cup.isMessageFieldInvalid());
        ExtentTestManager.info("Xac nhan cac truong bat buoc deu bi loi validation");
    }
}
