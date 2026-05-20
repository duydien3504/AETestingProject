package tests.ui;

import DataGenerate.RegisterGenData;
import listeners.TestListener;
import models.RegistrationData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.CartPage;
import pages.LoginPage;
import pages.RegisterPage;
import pages.CheckoutPage;
import pages.PaymentPage;
import pages.DeleteAccountPage;
import utils.ExcelUtils;
import utils.ExtentTestManager;

import java.util.List;

@Listeners(TestListener.class)
public class CheckoutTest extends BaseUITest {
    private SearchPage sp;
    private CartPage cp;
    private LoginPage lp;
    private RegisterPage rp;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;
    private DeleteAccountPage deleteAccountPage;

    @BeforeMethod
    public void setupTest() {
        sp = new SearchPage(page);
        cp = new CartPage(page);
        lp = new LoginPage(page);
        rp = new RegisterPage(page);
        checkoutPage = new CheckoutPage(page);
        paymentPage = new PaymentPage(page);
        deleteAccountPage = new DeleteAccountPage(page);
    }

    @Test(description = "Test Case 16: Place Order: Login before Checkout")
    public void TC016_PlaceOrderLoginBeforeCheckout() {
        sp.navigateWebsite();
        ExtentTestManager.info("Khoi chay trinh duyet va dieu huong den website");

        Assert.assertTrue(page.isVisible("//a[@href='/']/img"));
        ExtentTestManager.info("Xac minh trang chu hien thi thanh cong");

        List<RegistrationData> dataList = ExcelUtils.getTestData("DataTest_Register.xlsx", RegistrationData.class);
        RegistrationData data = dataList.stream()
                .filter(d -> d.getId().equals("TC002"))
                .findFirst()
                .orElse(dataList.get(0));
        String dynamicEmail = RegisterGenData.genEmail();

        sp.clickSignupLoginLink();
        rp.fillName(data.getName());
        rp.fillEmail(dynamicEmail);
        rp.clickSignup();
        ExtentTestManager.info("Dien thong tin dang ky ban dau (Email: " + dynamicEmail + ")");

        rp.checkGender();
        rp.fillPassword(data.getPassword());
        rp.selectBirtDay();
        rp.checkNewsLetter();
        rp.fillFirstName(data.getFirstName());
        rp.fillLastNameInput(data.getLastName());
        rp.fillCompany(data.getCompany());
        rp.fillAddress(data.getAddress());
        rp.fillAddress2(data.getAddress2() != null ? data.getAddress2() : "");
        rp.checkCountry();
        rp.fillState(data.getState());
        rp.fillCity(data.getCity());
        rp.fillZipcode(data.getZipcode());
        rp.fillPhone(data.getMobileNumber());
        rp.clickCreateAcc();
        ExtentTestManager.info("Dien thong tin dang ky chi tiet va gui");

        Assert.assertTrue(rp.isShowTitleSuccess());
        rp.clickContinue();
        ExtentTestManager.info("Xac minh ACCOUNT CREATED! va nhan Continue");

        sp.clickLogout();
        ExtentTestManager.info("Dang xuat nguoi dung");

        sp.clickSignupLoginLink();
        ExtentTestManager.info("Nhan nut 'Signup / Login'");

        lp.fillEmail(dynamicEmail);
        lp.fillPassword(data.getPassword());
        lp.clickLoginBtn();
        ExtentTestManager.info("Nhap email, mat khau va nhan nut 'Login'");

        Assert.assertTrue(page.isVisible("//a[contains(text(), 'Logged in as')]"));
        ExtentTestManager.info("Xac minh 'Logged in as username' o tren cung");

        sp.clickProductsLink();
        sp.addProductToCartByIndex(1);
        sp.clickContinueShopping();
        sp.addProductToCartByIndex(2);
        sp.clickViewCartFromModal();
        ExtentTestManager.info("Them san pham vao gio hang");

        Assert.assertTrue(page.url().contains("/view_cart"));
        ExtentTestManager.info("Xac minh trang gio hang hien thi");

        checkoutPage.clickProceedToCheckout();
        ExtentTestManager.info("Nhan nut Proceed To Checkout");

        Assert.assertFalse(checkoutPage.getDeliveryName().isEmpty());
        Assert.assertFalse(checkoutPage.getDeliveryAddress1().isEmpty());
        ExtentTestManager.info("Xac minh thong tin dia chi va danh gia don hang");

        checkoutPage.enterComment("Please deliver during business hours.");
        checkoutPage.clickPlaceOrder();
        ExtentTestManager.info("Nhap mo ta vao o binh luan va nhan 'Place Order'");

        paymentPage.enterPaymentDetails("John Doe", "4111111111111111", "123", "12", "2028");
        paymentPage.clickPayAndConfirmOrder();
        ExtentTestManager.info("Nhap thong tin thanh toan va nhan Pay and Confirm");

        Assert.assertTrue(paymentPage.isOrderPlacedSuccessfully());
        ExtentTestManager.info("Xac minh thong bao thanh cong 'Your order has been placed successfully!'");

        deleteAccountPage.clickDeleteAccount();
        ExtentTestManager.info("Nhan nut 'Delete Account'");

        Assert.assertTrue(deleteAccountPage.isAccountDeletedVisible());
        deleteAccountPage.clickContinue();
        ExtentTestManager.info("Xac minh 'ACCOUNT DELETED!' va nhan nut 'Continue'");
    }

    @Test(description = "Test Case 23: Verify address details in checkout page")
    public void TC_VerifyAddressDetailsInCheckoutPage() {
        sp.navigateWebsite();
        ExtentTestManager.info("Khoi chay trinh duyet va dieu huong den website");

        Assert.assertTrue(page.isVisible("//a[@href='/']/img"));
        ExtentTestManager.info("Xac minh trang chu hien thi thanh cong");

        List<RegistrationData> dataList = ExcelUtils.getTestData("DataTest_Register.xlsx", RegistrationData.class);
        RegistrationData data = dataList.stream()
                .filter(d -> d.getId().equals("TC002"))
                .findFirst()
                .orElse(dataList.get(0));
        String dynamicEmail = RegisterGenData.genEmail();

        sp.clickSignupLoginLink();
        rp.fillName(data.getName());
        rp.fillEmail(dynamicEmail);
        rp.clickSignup();
        ExtentTestManager.info("Dien thong tin dang ky ban dau");

        rp.checkGender();
        rp.fillPassword(data.getPassword());
        rp.selectBirtDay();
        rp.checkNewsLetter();
        rp.fillFirstName(data.getFirstName());
        rp.fillLastNameInput(data.getLastName());
        rp.fillCompany(data.getCompany());
        rp.fillAddress(data.getAddress());
        rp.fillAddress2(data.getAddress2() != null ? data.getAddress2() : "");
        rp.checkCountry();
        rp.fillState(data.getState());
        rp.fillCity(data.getCity());
        rp.fillZipcode(data.getZipcode());
        rp.fillPhone(data.getMobileNumber());
        rp.clickCreateAcc();
        ExtentTestManager.info("Dien tat ca thong tin dang ky chi tiet va tao tai khoan");

        Assert.assertTrue(rp.isShowTitleSuccess());
        rp.clickContinue();
        ExtentTestManager.info("Xac minh 'ACCOUNT CREATED!' va nhan nut 'Continue'");

        Assert.assertTrue(page.isVisible("//a[contains(text(), 'Logged in as')]"));
        ExtentTestManager.info("Xac minh 'Logged in as username' o tren cung");

        sp.clickProductsLink();
        sp.addProductToCartByIndex(1);
        sp.clickContinueShopping();
        sp.addProductToCartByIndex(2);
        sp.clickViewCartFromModal();
        ExtentTestManager.info("Them san pham vao gio hang");

        Assert.assertTrue(page.url().contains("/view_cart"));
        ExtentTestManager.info("Xac minh trang gio hang hien thi");

        checkoutPage.clickProceedToCheckout();
        ExtentTestManager.info("Nhan nut Proceed To Checkout");

        Assert.assertTrue(checkoutPage.getDeliveryName().contains(data.getFirstName()));
        Assert.assertTrue(checkoutPage.getDeliveryName().contains(data.getLastName()));
        Assert.assertEquals(checkoutPage.getDeliveryCompany(), data.getCompany());
        Assert.assertEquals(checkoutPage.getDeliveryAddress1(), data.getAddress());
        Assert.assertEquals(checkoutPage.getDeliveryAddress2(), data.getAddress2() != null ? data.getAddress2() : "");
        Assert.assertTrue(checkoutPage.getDeliveryCityStateZip().contains(data.getCity()));
        Assert.assertTrue(checkoutPage.getDeliveryCityStateZip().contains(data.getState()));
        Assert.assertTrue(checkoutPage.getDeliveryCityStateZip().contains(data.getZipcode()));
        Assert.assertEquals(checkoutPage.getDeliveryCountry(), "Australia");
        Assert.assertEquals(checkoutPage.getDeliveryPhone(), data.getMobileNumber());
        ExtentTestManager.info("Xac minh dia chi giao hang khop voi thong tin dang ky");

        Assert.assertTrue(checkoutPage.getBillingName().contains(data.getFirstName()));
        Assert.assertTrue(checkoutPage.getBillingName().contains(data.getLastName()));
        Assert.assertEquals(checkoutPage.getBillingCompany(), data.getCompany());
        Assert.assertEquals(checkoutPage.getBillingAddress1(), data.getAddress());
        Assert.assertEquals(checkoutPage.getBillingAddress2(), data.getAddress2() != null ? data.getAddress2() : "");
        Assert.assertTrue(checkoutPage.getBillingCityStateZip().contains(data.getCity()));
        Assert.assertTrue(checkoutPage.getBillingCityStateZip().contains(data.getState()));
        Assert.assertTrue(checkoutPage.getBillingCityStateZip().contains(data.getZipcode()));
        Assert.assertEquals(checkoutPage.getBillingCountry(), "Australia");
        Assert.assertEquals(checkoutPage.getBillingPhone(), data.getMobileNumber());
        ExtentTestManager.info("Xac minh dia chi thanh toan khop voi thong tin dang ky");

        deleteAccountPage.clickDeleteAccount();
        ExtentTestManager.info("Nhan nut 'Delete Account'");

        Assert.assertTrue(deleteAccountPage.isAccountDeletedVisible());
        deleteAccountPage.clickContinue();
        ExtentTestManager.info("Xac minh 'ACCOUNT DELETED!' va nhan nut 'Continue'");
    }
}
