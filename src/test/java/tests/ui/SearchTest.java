package tests.ui;

import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;
import pages.CartPage;
import pages.ProductDetailPage;
import config.ConfigReader;
import utils.ExtentTestManager;

import java.util.List;

@Listeners(TestListener.class)
public class SearchTest extends BaseUITest {
    private SearchPage sp;
    private CartPage cp;
    private LoginPage lp;
    private ProductDetailPage pdp;

    @BeforeMethod
    public void setupTest() {
        sp = new SearchPage(page);
        cp = new CartPage(page);
        lp = new LoginPage(page);
        pdp = new ProductDetailPage(page);
    }

    @Test(description = "Test Case 9: Search Product")
    public void TC_SearchProduct() {
        sp.navigateWebsite();
        ExtentTestManager.info("Launch browser and navigate to URL");

        Assert.assertTrue(page.isVisible("//a[@href='/']/img"));
        ExtentTestManager.info("Verify that home page is visible successfully");

        sp.clickProductsLink();
        ExtentTestManager.info("Click on 'Products' button");

        Assert.assertTrue(sp.isAllProductsPageVisible());
        ExtentTestManager.info("Verify user is navigated to ALL PRODUCTS page successfully");

        String searchKeyword = "t-shirt";
        sp.searchProduct(searchKeyword);
        ExtentTestManager.info("Enter product name '" + searchKeyword + "' in search input and click search button");

        Assert.assertTrue(sp.isSearchedProductsTitleVisible());
        ExtentTestManager.info("Verify 'SEARCHED PRODUCTS' is visible");

        int productCount = sp.getSearchedProductsCount();
        Assert.assertTrue(productCount > 0);
        ExtentTestManager.info("Verify all the products related to search are visible (Found: " + productCount + ")");
    }

    @Test(description = "Test Case 20: Search Products and Verify Cart After Login")
    public void TC_SearchProductsAndVerifyCartAfterLogin() {
        sp.navigateWebsite();
        ExtentTestManager.info("Launch browser and navigate to URL");

        sp.clickProductsLink();
        ExtentTestManager.info("Click on 'Products' button");

        Assert.assertTrue(sp.isAllProductsPageVisible());
        ExtentTestManager.info("Verify user is navigated to ALL PRODUCTS page successfully");

        String searchKeyword = "t-shirt";
        sp.searchProduct(searchKeyword);
        ExtentTestManager.info("Enter product name '" + searchKeyword + "' in search input and click search button");

        Assert.assertTrue(sp.isSearchedProductsTitleVisible());
        ExtentTestManager.info("Verify 'SEARCHED PRODUCTS' is visible");

        int productCount = sp.getSearchedProductsCount();
        Assert.assertTrue(productCount > 0);
        ExtentTestManager.info("Verify all the products related to search are visible (Found: " + productCount + ")");

        sp.addAllSearchedProductsToCart();
        ExtentTestManager.info("Add those products to cart");

        sp.clickCartLink();
        ExtentTestManager.info("Click 'Cart' button");

        int cartCountBefore = cp.getCartItemsCount();
        Assert.assertTrue(cartCountBefore > 0);
        ExtentTestManager.info("Verify that products are visible in cart (Found in cart: " + cartCountBefore + ")");

        List<String> productNamesBefore = cp.getCartItemNames();

        sp.clickSignupLoginLink();
        ExtentTestManager.info("Click 'Signup / Login' button");

        String email = ConfigReader.getEmail();
        String password = ConfigReader.getPassword();
        lp.fillEmail(email);
        lp.fillPassword(password);
        lp.clickLoginBtn();
        ExtentTestManager.info("Submit login details (Email: " + email + ")");

        sp.clickCartLink();
        ExtentTestManager.info("Again, go to Cart page");

        int cartCountAfter = cp.getCartItemsCount();
        Assert.assertTrue(cartCountAfter > 0);
        ExtentTestManager.info("Verify that those products are visible in cart after login as well (Found in cart: " + cartCountAfter + ")");

        List<String> productNamesAfter = cp.getCartItemNames();
        Assert.assertEquals(productNamesAfter, productNamesBefore);
        ExtentTestManager.info("Verify products matches the ones added before login");
    }

    @Test(description = "Test Case 12: Add Products in Cart")
    public void TC_AddProductsInCart() {
        sp.navigateWebsite();
        ExtentTestManager.info("Launch browser and navigate to URL");

        Assert.assertTrue(page.isVisible("//a[@href='/']/img"));
        ExtentTestManager.info("Verify that home page is visible successfully");

        sp.clickProductsLink();
        ExtentTestManager.info("Click 'Products' button");

        String firstProdName = sp.getProductNameByIndex(1);
        String firstProdPrice = sp.getProductPriceByIndex(1);
        ExtentTestManager.info("First product: " + firstProdName + " | Price: " + firstProdPrice);

        String secondProdName = sp.getProductNameByIndex(2);
        String secondProdPrice = sp.getProductPriceByIndex(2);
        ExtentTestManager.info("Second product: " + secondProdName + " | Price: " + secondProdPrice);

        sp.addProductToCartByIndex(1);
        ExtentTestManager.info("Hover over first product and click 'Add to cart'");

        sp.clickContinueShopping();
        ExtentTestManager.info("Click 'Continue Shopping' button");

        sp.addProductToCartByIndex(2);
        ExtentTestManager.info("Hover over second product and click 'Add to cart'");

        sp.clickViewCartFromModal();
        ExtentTestManager.info("Click 'View Cart' button");

        Assert.assertTrue(cp.isProductInCart(firstProdName));
        Assert.assertTrue(cp.isProductInCart(secondProdName));
        ExtentTestManager.info("Verify both products are added to Cart successfully");

        String cartFirstPrice = cp.getProductPriceByName(firstProdName);
        String cartFirstQty = cp.getProductQuantityByName(firstProdName);
        String cartFirstTotal = cp.getProductTotalByName(firstProdName);
        Assert.assertEquals(cartFirstPrice, firstProdPrice);
        Assert.assertEquals(cartFirstQty, "1");
        Assert.assertEquals(cartFirstTotal, firstProdPrice);
        ExtentTestManager.info("Verify 1st product details in Cart - Price: " + cartFirstPrice + ", Qty: " + cartFirstQty + ", Total: " + cartFirstTotal);

        String cartSecondPrice = cp.getProductPriceByName(secondProdName);
        String cartSecondQty = cp.getProductQuantityByName(secondProdName);
        String cartSecondTotal = cp.getProductTotalByName(secondProdName);
        Assert.assertEquals(cartSecondPrice, secondProdPrice);
        Assert.assertEquals(cartSecondQty, "1");
        Assert.assertEquals(cartSecondTotal, secondProdPrice);
        ExtentTestManager.info("Verify 2nd product details in Cart - Price: " + cartSecondPrice + ", Qty: " + cartSecondQty + ", Total: " + cartSecondTotal);
    }

    @Test(description = "Test Case 13: Verify Product quantity in Cart")
    public void TC_VerifyProductQuantityInCart() {
        sp.navigateWebsite();
        ExtentTestManager.info("Launch browser and navigate to URL");

        Assert.assertTrue(page.isVisible("//a[@href='/']/img"));
        ExtentTestManager.info("Verify that home page is visible successfully");

        String prodName = sp.getProductNameByIndex(1);
        sp.clickProductViewProduct(1);
        ExtentTestManager.info("Click 'View Product' for '" + prodName + "' on home page");

        Assert.assertTrue(page.url().contains("/product_details/"));
        ExtentTestManager.info("Verify product detail is opened successfully");

        pdp.setQuantity("4");
        ExtentTestManager.info("Increase quantity to 4");

        pdp.clickAddToCart();
        ExtentTestManager.info("Click 'Add to cart' button");

        pdp.clickViewCart();
        ExtentTestManager.info("Click 'View Cart' button");

        Assert.assertTrue(cp.isProductInCart(prodName));
        String actualQty = cp.getProductQuantityByName(prodName);
        Assert.assertEquals(actualQty, "4");
        ExtentTestManager.info("Verify that product is displayed in cart page with exact quantity (Expected: 4, Actual: " + actualQty + ")");
    }
}
