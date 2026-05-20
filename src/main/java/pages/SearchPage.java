package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import static constants.SearchConstant.*;

public class SearchPage extends BasePage {

    public SearchPage(Page page) {
        super(page);
    }

    @Override
    public void navigateWebsite() {
        page.navigate(baseUrl);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void clickProductsLink() {
        clickElement(productsLink);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public boolean isAllProductsPageVisible() {
        return page.isVisible(searchInput) && page.url().contains("/products");
    }

    public void searchProduct(String productName) {
        fillElement(searchInput, productName);
        clickElement(searchBtn);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public boolean isSearchedProductsTitleVisible() {
        return page.isVisible(searchedProductsTitle);
    }

    public int getSearchedProductsCount() {
        return page.locator(productItems).count();
    }

    public String getProductNameByIndex(int index) {
        String xpath = String.format(productNameByIndexXpath, index);
        return page.locator(xpath).textContent().trim();
    }

    public String getProductPriceByIndex(int index) {
        String xpath = String.format(productPriceByIndexXpath, index);
        return page.locator(xpath).textContent().trim();
    }

    public void addProductToCartByIndex(int index) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String xpath = String.format(productInfoAddToCartByIndexXpath, index);
        clickElement(xpath);
        page.waitForSelector(continueShoppingBtn);
    }

    public void clickContinueShopping() {
        clickElement(continueShoppingBtn);
    }

    public void clickViewCartFromModal() {
        clickElement(viewCartLink);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void clickProductViewProduct(int index) {
        String xpath = String.format(productViewProductByIndexXpath, index);
        clickElement(xpath);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void addAllSearchedProductsToCart() {
        int count = page.locator(addToCartBtn).count();
        for (int i = 0; i < count; i++) {
            page.locator(addToCartBtn).nth(i).click();
            page.waitForSelector(continueShoppingBtn);
            page.locator(continueShoppingBtn).click();
            page.waitForTimeout(500);
        }
    }

    public void clickCartLink() {
        clickElement(cartLink);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void clickSignupLoginLink() {
        clickElement(signupLoginLink);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void clickLogout() {
        clickElement(logoutLink);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
}
