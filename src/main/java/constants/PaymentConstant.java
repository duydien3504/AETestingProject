package constants;

public class PaymentConstant {
    public static final String nameOnCardInput = "input[data-qa='name-on-card']";
    public static final String cardNumberInput = "input[data-qa='card-number']";
    public static final String cvcInput = "input[data-qa='cvc']";
    public static final String expiryMonthInput = "input[data-qa='expiry-month']";
    public static final String expiryYearInput = "input[data-qa='expiry-year']";
    public static final String payAndConfirmBtn = "button[data-qa='pay-button']";

    public static final String orderPlacedTitle = "h2[data-qa='order-placed'] b";
    public static final String orderPlacedMsg = "//h2[@data-qa='order-placed']/following-sibling::p";
}
