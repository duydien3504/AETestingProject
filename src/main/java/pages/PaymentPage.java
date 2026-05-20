package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import static constants.PaymentConstant.*;

public class PaymentPage extends BasePage {

    public PaymentPage(Page page) {
        super(page);
    }

    public void enterPaymentDetails(String name, String cardNum, String cvc, String expMonth, String expYear) {
        fillElement(nameOnCardInput, name);
        fillElement(cardNumberInput, cardNum);
        fillElement(cvcInput, cvc);
        fillElement(expiryMonthInput, expMonth);
        fillElement(expiryYearInput, expYear);
    }

    public void clickPayAndConfirmOrder() {
        clickElement(payAndConfirmBtn);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public boolean isOrderPlacedSuccessfully() {
        if (page.isVisible(orderPlacedTitle)) {
            String text = page.textContent(orderPlacedMsg);
            return text != null && text.contains("Your order has been confirmed!");
        }
        return false;
    }
}
