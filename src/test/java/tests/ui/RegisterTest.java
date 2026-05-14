package tests.ui;

import DataGenerate.RegisterGenData;
import listeners.TestListener;
import models.RegistrationData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.RegisterPage;
import utils.ExcelUtils;
import utils.ExtentTestManager;

import java.util.List;
import java.util.Objects;

@Listeners(TestListener.class)
public class RegisterTest extends BaseUITest{
    private RegisterPage rP;

    @DataProvider(name = "RegisterDataProvider")
    public Object[][] getData(java.lang.reflect.Method method) {
        List<RegistrationData> dataList = ExcelUtils.getTestData("DataTest_Register.xlsx", RegistrationData.class);
        String methodName = method.getName();
        RegistrationData rowData = dataList.stream()
                .filter(data -> data.getId().startsWith(methodName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Khong tim thay data cho: " + methodName));
        return new Object[][] {{rowData}};
    }


    @Test(dataProvider = "RegisterDataProvider",
            description = "Register with all valid fields - Happy Path")
    public void TC001(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.selectBirtDay();
        ExtentTestManager.info("Chon ngay thang nam sinh");

        rP.checkNewsLetter();
        ExtentTestManager.info("Check nhan thong bao email");

        rP.fillFirstName(data.getFirstName());
        ExtentTestManager.info("Nhap ten");

        rP.fillLastNameInput(data.getLastName());
        ExtentTestManager.info("Nhap ho");

        rP.fillCompany(data.getCompany());
        ExtentTestManager.info("Nhap ten cong ty");

        rP.fillAddress(data.getAddress());
        ExtentTestManager.info("Nhap dia chi");

        rP.checkCountry();
        ExtentTestManager.info("Chon quoc gia");

        rP.fillState(data.getState());
        ExtentTestManager.info("Nhap state");

        rP.fillCity(data.getCity());
        ExtentTestManager.info("Nhap thanh pho");

        rP.fillZipcode(data.getZipcode());
        ExtentTestManager.info("Nhap zipcode");

        rP.fillPhone(data.getMobileNumber());
        ExtentTestManager.info("Nhap so dien thoai");

        rP.clickCreateAcc();
        ExtentTestManager.info("Tao acc");

        Assert.assertTrue(rP.isShowTitleSuccess());
        Assert.assertTrue(rP.isShowMessageSuccess());
    }

    @Test(dataProvider = "RegisterDataProvider",
    description = " Register with all fields (including optional ones) - Happy Path")
    public void TC002(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.selectBirtDay();
        ExtentTestManager.info("Chon ngay thang nam sinh");

        rP.checkNewsLetter();
        ExtentTestManager.info("Check nhan thong bao email");

        rP.fillFirstName(data.getFirstName());
        ExtentTestManager.info("Nhap ten");

        rP.fillLastNameInput(data.getLastName());
        ExtentTestManager.info("Nhap ho");

        rP.fillCompany(data.getCompany());
        ExtentTestManager.info("Nhap ten cong ty");

        rP.fillAddress(data.getAddress());
        ExtentTestManager.info("Nhap dia chi");

        rP.fillAddress2(data.getAddress2());
        ExtentTestManager.info("Nhap dia chi 2");

        rP.checkCountry();
        ExtentTestManager.info("Chon quoc gia");

        rP.fillState(data.getState());
        ExtentTestManager.info("Nhap state");

        rP.fillCity(data.getCity());
        ExtentTestManager.info("Nhap thanh pho");

        rP.fillZipcode(data.getZipcode());
        ExtentTestManager.info("Nhap zipcode");

        rP.fillPhone(data.getMobileNumber());
        ExtentTestManager.info("Nhap so dien thoai");

        rP.clickCreateAcc();
        ExtentTestManager.info("Tao acc");

        Assert.assertTrue(rP.isShowTitleSuccess());
        Assert.assertTrue(rP.isShowMessageSuccess());
    }


    @Test(dataProvider = "RegisterDataProvider",
            description = "Empty Name/Email in initial signup - Negative Case")
    public void TC003(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        ExtentTestManager.info("Bo trong ten");
        ExtentTestManager.info("Bo trong email");

        Assert.assertTrue(rP.isShowNameRequireMessage() && rP.isEmailRequireMessage() && rP.isAllFieldRequire());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Invalid Email format - Negative Case")
    public void TC004(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(data.getEmail());
        ExtentTestManager.info("Dien email vao textbox");

        Assert.assertTrue(rP.isEmailIvlMessage());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Duplicate Email registration - Negative Case")
    public void TC005(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(data.getEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        Assert.assertTrue(rP.isEmailExist());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Password too short - Negative Case")
    public void TC006(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.blurActiveElement();
        ExtentTestManager.info("Bo focus textbox Password");

        Assert.assertTrue(rP.isPasswordWeakMessage());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Empty mandatory field - Negative Case")
    public void TC007(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.selectBirtDay();
        ExtentTestManager.info("Chon ngay thang nam sinh");

        rP.checkNewsLetter();
        ExtentTestManager.info("Check nhan thong bao email");

        rP.fillFirstName(data.getFirstName());
        ExtentTestManager.info("Nhap ten");

        rP.fillLastNameInput(data.getLastName());
        ExtentTestManager.info("Nhap ho");

        rP.fillCompany(data.getCompany());
        ExtentTestManager.info("Nhap ten cong ty");

        rP.checkCountry();
        ExtentTestManager.info("Chon quoc gia");

        rP.fillState(data.getState());
        ExtentTestManager.info("Nhap state");

        rP.fillCity(data.getCity());
        ExtentTestManager.info("Nhap thanh pho");

        rP.fillZipcode(data.getZipcode());
        ExtentTestManager.info("Nhap zipcode");

        rP.fillPhone(data.getMobileNumber());
        ExtentTestManager.info("Nhap so dien thoai");

        rP.clickCreateAcc();
        ExtentTestManager.info("Tao acc");

        Assert.assertTrue(rP.isAddressRequired());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Mobile Number containing letters - Negative Case")
    public void TC008(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.selectBirtDay();
        ExtentTestManager.info("Chon ngay thang nam sinh");

        rP.checkNewsLetter();
        ExtentTestManager.info("Check nhan thong bao email");

        rP.fillFirstName(data.getFirstName());
        ExtentTestManager.info("Nhap ten");

        rP.fillLastNameInput(data.getLastName());
        ExtentTestManager.info("Nhap ho");

        rP.fillCompany(data.getCompany());
        ExtentTestManager.info("Nhap ten cong ty");

        rP.fillAddress(data.getAddress());
        ExtentTestManager.info("Nhap dia chi");

        rP.checkCountry();
        ExtentTestManager.info("Chon quoc gia");

        rP.fillState(data.getState());
        ExtentTestManager.info("Nhap state");

        rP.fillCity(data.getCity());
        ExtentTestManager.info("Nhap thanh pho");

        rP.fillZipcode(data.getZipcode());
        ExtentTestManager.info("Nhap zipcode");

        rP.fillPhone(data.getMobileNumber());
        ExtentTestManager.info("Nhap so dien thoai");

        rP.clickCreateAcc();
        ExtentTestManager.info("Tao acc");

        Assert.assertTrue(rP.isPhoneIvlMessage());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Zipcode with special characters - Negative Case")
    public void TC009(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.selectBirtDay();
        ExtentTestManager.info("Chon ngay thang nam sinh");

        rP.checkNewsLetter();
        ExtentTestManager.info("Check nhan thong bao email");

        rP.fillFirstName(data.getFirstName());
        ExtentTestManager.info("Nhap ten");

        rP.fillLastNameInput(data.getLastName());
        ExtentTestManager.info("Nhap ho");

        rP.fillCompany(data.getCompany());
        ExtentTestManager.info("Nhap ten cong ty");

        rP.fillAddress(data.getAddress());
        ExtentTestManager.info("Nhap dia chi");

        rP.checkCountry();
        ExtentTestManager.info("Chon quoc gia");

        rP.fillState(data.getState());
        ExtentTestManager.info("Nhap state");

        rP.fillCity(data.getCity());
        ExtentTestManager.info("Nhap thanh pho");

        rP.fillZipcode(data.getZipcode());
        ExtentTestManager.info("Nhap zipcode");

        rP.fillPhone(data.getMobileNumber());
        ExtentTestManager.info("Nhap so dien thoai");

        rP.clickCreateAcc();
        ExtentTestManager.info("Tao acc");

        Assert.assertTrue(rP.isZipcodeIvl());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Empty First Name in detailed form - Negative Case")
    public void TC010(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.selectBirtDay();
        ExtentTestManager.info("Chon ngay thang nam sinh");

        rP.checkNewsLetter();
        ExtentTestManager.info("Check nhan thong bao email");

        rP.fillFirstName(data.getFirstName());
        ExtentTestManager.info("Nhap ten");

        rP.fillLastNameInput(data.getLastName());
        ExtentTestManager.info("Nhap ho");

        rP.fillCompany(data.getCompany());
        ExtentTestManager.info("Nhap ten cong ty");

        rP.fillAddress(data.getAddress());
        ExtentTestManager.info("Nhap dia chi");

        rP.checkCountry();
        ExtentTestManager.info("Chon quoc gia");

        rP.fillState(data.getState());
        ExtentTestManager.info("Nhap state");

        rP.fillCity(data.getCity());
        ExtentTestManager.info("Nhap thanh pho");

        rP.fillZipcode(data.getZipcode());
        ExtentTestManager.info("Nhap zipcode");

        rP.fillPhone(data.getMobileNumber());
        ExtentTestManager.info("Nhap so dien thoai");

        rP.clickCreateAcc();
        ExtentTestManager.info("Tao acc");

        Assert.assertTrue(rP.isFirstNameEmptyMessage());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Password at minimum limit (8 chars) - Boundary Value")
    public void TC011(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.selectBirtDay();
        ExtentTestManager.info("Chon ngay thang nam sinh");

        rP.checkNewsLetter();
        ExtentTestManager.info("Check nhan thong bao email");

        rP.fillFirstName(data.getFirstName());
        ExtentTestManager.info("Nhap ten");

        rP.fillLastNameInput(data.getLastName());
        ExtentTestManager.info("Nhap ho");

        rP.fillCompany(data.getCompany());
        ExtentTestManager.info("Nhap ten cong ty");

        rP.fillAddress(data.getAddress());
        ExtentTestManager.info("Nhap dia chi");

        rP.checkCountry();
        ExtentTestManager.info("Chon quoc gia");

        rP.fillState(data.getState());
        ExtentTestManager.info("Nhap state");

        rP.fillCity(data.getCity());
        ExtentTestManager.info("Nhap thanh pho");

        rP.fillZipcode(data.getZipcode());
        ExtentTestManager.info("Nhap zipcode");

        rP.fillPhone(data.getMobileNumber());
        ExtentTestManager.info("Nhap so dien thoai");

        rP.clickCreateAcc();
        ExtentTestManager.info("Tao acc");

        Assert.assertTrue(rP.isShowTitleSuccess());
        Assert.assertTrue(rP.isShowMessageSuccess());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Password below minimum limit (7 chars) - Boundary Value")
    public void TC012(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.selectBirtDay();
        ExtentTestManager.info("Chon ngay thang nam sinh");

        rP.checkNewsLetter();
        ExtentTestManager.info("Check nhan thong bao email");

        rP.fillFirstName(data.getFirstName());
        ExtentTestManager.info("Nhap ten");

        rP.fillLastNameInput(data.getLastName());
        ExtentTestManager.info("Nhap ho");

        rP.fillCompany(data.getCompany());
        ExtentTestManager.info("Nhap ten cong ty");

        rP.fillAddress(data.getAddress());
        ExtentTestManager.info("Nhap dia chi");

        rP.checkCountry();
        ExtentTestManager.info("Chon quoc gia");

        rP.fillState(data.getState());
        ExtentTestManager.info("Nhap state");

        rP.fillCity(data.getCity());
        ExtentTestManager.info("Nhap thanh pho");

        rP.fillZipcode(data.getZipcode());
        ExtentTestManager.info("Nhap zipcode");

        rP.fillPhone(data.getMobileNumber());
        ExtentTestManager.info("Nhap so dien thoai");

        rP.clickCreateAcc();
        ExtentTestManager.info("Tao acc");

        Assert.assertTrue(rP.isPasswordShortMessage());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Mobile Number at exact length (10 digits) - Boundary Value")
    public void TC013(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.selectBirtDay();
        ExtentTestManager.info("Chon ngay thang nam sinh");

        rP.checkNewsLetter();
        ExtentTestManager.info("Check nhan thong bao email");

        rP.fillFirstName(data.getFirstName());
        ExtentTestManager.info("Nhap ten");

        rP.fillLastNameInput(data.getLastName());
        ExtentTestManager.info("Nhap ho");

        rP.fillCompany(data.getCompany());
        ExtentTestManager.info("Nhap ten cong ty");

        rP.fillAddress(data.getAddress());
        ExtentTestManager.info("Nhap dia chi");

        rP.checkCountry();
        ExtentTestManager.info("Chon quoc gia");

        rP.fillState(data.getState());
        ExtentTestManager.info("Nhap state");

        rP.fillCity(data.getCity());
        ExtentTestManager.info("Nhap thanh pho");

        rP.fillZipcode(data.getZipcode());
        ExtentTestManager.info("Nhap zipcode");

        rP.fillPhone(data.getMobileNumber());
        ExtentTestManager.info("Nhap so dien thoai");

        rP.clickCreateAcc();
        ExtentTestManager.info("Tao acc");

        Assert.assertTrue(rP.isShowTitleSuccess());
        Assert.assertTrue(rP.isShowMessageSuccess());
    }

    @Test(dataProvider = "RegisterDataProvider",
            description = "Mobile Number above max length (e.g. 15 digits) - Boundary Value")
    public void TC014(RegistrationData data) {
        rP = new RegisterPage(page);

        rP.navigateWebsite();
        ExtentTestManager.info("Dieu huong den website");

        rP.clickSignupBtn();
        ExtentTestManager.info("Di chuyen den trang dang ky");

        rP.fillName(data.getName());
        ExtentTestManager.info("Dien ten vao textbox");

        rP.fillEmail(RegisterGenData.genEmail());
        ExtentTestManager.info("Dien email vao textbox");

        rP.clickSignup();
        ExtentTestManager.info("Di chuyen den trang dang ky chi tiet");

        rP.checkGender();
        ExtentTestManager.info("Chon gioi tinh");

        rP.fillPassword(data.getPassword());
        ExtentTestManager.info("Nhap mat khau");

        rP.selectBirtDay();
        ExtentTestManager.info("Chon ngay thang nam sinh");

        rP.checkNewsLetter();
        ExtentTestManager.info("Check nhan thong bao email");

        rP.fillFirstName(data.getFirstName());
        ExtentTestManager.info("Nhap ten");

        rP.fillLastNameInput(data.getLastName());
        ExtentTestManager.info("Nhap ho");

        rP.fillCompany(data.getCompany());
        ExtentTestManager.info("Nhap ten cong ty");

        rP.fillAddress(data.getAddress());
        ExtentTestManager.info("Nhap dia chi");

        rP.checkCountry();
        ExtentTestManager.info("Chon quoc gia");

        rP.fillState(data.getState());
        ExtentTestManager.info("Nhap state");

        rP.fillCity(data.getCity());
        ExtentTestManager.info("Nhap thanh pho");

        rP.fillZipcode(data.getZipcode());
        ExtentTestManager.info("Nhap zipcode");

        rP.fillPhone(data.getMobileNumber());
        ExtentTestManager.info("Nhap so dien thoai");

        rP.clickCreateAcc();
        ExtentTestManager.info("Tao acc");

        Assert.assertTrue(rP.isPhoneIvlMessage());
    }
}
