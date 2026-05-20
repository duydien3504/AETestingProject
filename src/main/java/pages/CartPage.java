package pages;

import com.microsoft.playwright.Page;
import constants.CartConstant;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(Page page) {
        super(page);
    }

    @Override
    public void navigateWebsite() {
        page.navigate(baseUrl + "view_cart");
        page.waitForLoadState(com.microsoft.playwright.options.LoadState.DOMCONTENTLOADED);
    }

    public int getCartItemsCount() {
        return page.locator(CartConstant.cartRows).count();
    }

    public List<String> getCartItemNames() {
        List<String> names = new ArrayList<>();
        int count = page.locator(CartConstant.cartItemNames).count();
        for (int i = 0; i < count; i++) {
            String nameText = page.locator(CartConstant.cartItemNames).nth(i).textContent();
            if (nameText != null) {
                names.add(nameText.trim());
            }
        }
        return names;
    }

    public boolean isProductInCart(String productName) {
        List<String> names = getCartItemNames();
        for (String name : names) {
            if (name.toLowerCase().contains(productName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public String getProductPriceByName(String productName) {
        String xpath = String.format(CartConstant.cartItemPriceXpath, productName);
        return page.locator(xpath).textContent().trim();
    }

    public String getProductQuantityByName(String productName) {
        String xpath = String.format(CartConstant.cartItemQuantityXpath, productName);
        return page.locator(xpath).textContent().trim();
    }

    public String getProductTotalByName(String productName) {
        String xpath = String.format(CartConstant.cartItemTotalXpath, productName);
        return page.locator(xpath).textContent().trim();
    }
}
