package constants;

public class CartConstant {
    public static final String cartRows = "//table[@id='cart_info_table']/tbody/tr";
    public static final String cartItemNames = "//table[@id='cart_info_table']//td[@class='cart_description']//h4/a";

    public static final String productRowByNameXpath = "//table[@id='cart_info_table']//tr[descendant::a[contains(text(),'%s')]]";
    public static final String cartItemPriceXpath = productRowByNameXpath + "//td[@class='cart_price']/p";
    public static final String cartItemQuantityXpath = productRowByNameXpath + "//td[@class='cart_quantity']/button";
    public static final String cartItemTotalXpath = productRowByNameXpath + "//td[@class='cart_total']/p";
}
