package constants;

public class CheckoutConstant {
    public static final String proceedToCheckoutBtn = "a.check_out";
    public static final String commentTextArea = "textarea[name='message']";
    public static final String placeOrderBtn = "a[href='/payment']";

    public static final String deliveryName = "ul#address_delivery li.address_firstname.address_lastname";
    public static final String deliveryCompany = "ul#address_delivery li:nth-child(3)";
    public static final String deliveryAddress1 = "ul#address_delivery li:nth-child(4)";
    public static final String deliveryAddress2 = "ul#address_delivery li:nth-child(5)";
    public static final String deliveryCityStateZip = "ul#address_delivery li.address_city.address_state_name.address_postcode";
    public static final String deliveryCountry = "ul#address_delivery li.address_country_name";
    public static final String deliveryPhone = "ul#address_delivery li.address_phone";

    public static final String billingName = "ul#address_invoice li.address_firstname.address_lastname";
    public static final String billingCompany = "ul#address_invoice li:nth-child(3)";
    public static final String billingAddress1 = "ul#address_invoice li:nth-child(4)";
    public static final String billingAddress2 = "ul#address_invoice li:nth-child(5)";
    public static final String billingCityStateZip = "ul#address_invoice li.address_city.address_state_name.address_postcode";
    public static final String billingCountry = "ul#address_invoice li.address_country_name";
    public static final String billingPhone = "ul#address_invoice li.address_phone";
}
