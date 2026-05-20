package constants;

public class SearchConstant {
    public static final String productsLink = "//header[@id='header']//a[@href='/products']";
    public static final String searchInput = "//input[@id='search_product']";
    public static final String searchBtn = "//button[@id='submit_search']";
    public static final String searchedProductsTitle = "//h2[@class='title text-center' and text()='Searched Products']";
    public static final String productItems = "//div[@class='single-products']";
    public static final String addToCartBtn = "//div[@class='productinfo text-center']//a[contains(@class, 'add-to-cart')]";
    public static final String continueShoppingBtn = "//div[@id='cartModal']//button[contains(@class, 'close-modal')]";
    public static final String viewCartLink = "//div[@id='cartModal']//a[@href='/view_cart']";
    public static final String cartLink = "//header[@id='header']//a[@href='/view_cart']";
    
    public static final String signupLoginLink = "//header[@id='header']//a[@href='/login']";
    public static final String logoutLink = "//header[@id='header']//a[@href='/logout']";

    public static final String productItemByIndexXpath = "(//div[@class='features_items']//div[@class='col-sm-4'])[%d]";
    public static final String productNameByIndexXpath = productItemByIndexXpath + "//div[@class='productinfo text-center']/p";
    public static final String productPriceByIndexXpath = productItemByIndexXpath + "//div[@class='productinfo text-center']/h2";
    public static final String productAddToCartByIndexXpath = productItemByIndexXpath + "//a[contains(@class, 'add-to-cart')]";
    public static final String productOverlayAddToCartByIndexXpath = productItemByIndexXpath + "//div[@class='product-overlay']//a[contains(@class, 'add-to-cart')]";
    public static final String productInfoAddToCartByIndexXpath = productItemByIndexXpath + "//div[@class='productinfo text-center']//a[contains(@class, 'add-to-cart')]";
    public static final String productViewProductByIndexXpath = productItemByIndexXpath + "//a[contains(text(), 'View Product')]";
}
