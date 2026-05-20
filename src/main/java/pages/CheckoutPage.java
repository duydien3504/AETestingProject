package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import static constants.CheckoutConstant.*;

public class CheckoutPage extends BasePage {

    public CheckoutPage(Page page) {
        super(page);
    }

    public void clickProceedToCheckout() {
        clickElement(proceedToCheckoutBtn);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void enterComment(String comment) {
        fillElement(commentTextArea, comment);
    }

    public void clickPlaceOrder() {
        clickElement(placeOrderBtn);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public String getDeliveryName() {
        return page.textContent(deliveryName).trim();
    }

    public String getDeliveryCompany() {
        return page.textContent(deliveryCompany).trim();
    }

    public String getDeliveryAddress1() {
        return page.textContent(deliveryAddress1).trim();
    }

    public String getDeliveryAddress2() {
        return page.textContent(deliveryAddress2).trim();
    }

    public String getDeliveryCityStateZip() {
        return page.textContent(deliveryCityStateZip).trim();
    }

    public String getDeliveryCountry() {
        return page.textContent(deliveryCountry).trim();
    }

    public String getDeliveryPhone() {
        return page.textContent(deliveryPhone).trim();
    }

    public String getBillingName() {
        return page.textContent(billingName).trim();
    }

    public String getBillingCompany() {
        return page.textContent(billingCompany).trim();
    }

    public String getBillingAddress1() {
        return page.textContent(billingAddress1).trim();
    }

    public String getBillingAddress2() {
        return page.textContent(billingAddress2).trim();
    }

    public String getBillingCityStateZip() {
        return page.textContent(billingCityStateZip).trim();
    }

    public String getBillingCountry() {
        return page.textContent(billingCountry).trim();
    }

    public String getBillingPhone() {
        return page.textContent(billingPhone).trim();
    }
}
