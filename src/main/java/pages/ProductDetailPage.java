package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import static constants.ProductDetailConstant.*;

public class ProductDetailPage extends BasePage {

    public ProductDetailPage(Page page) {
        super(page);
    }

    public void setQuantity(String qty) {
        page.locator(quantityInput).clear();
        fillElement(quantityInput, qty);
    }

    public void clickAddToCart() {
        clickElement(addToCartBtn);
    }

    public void clickViewCart() {
        clickElement(viewCartLink);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
}
