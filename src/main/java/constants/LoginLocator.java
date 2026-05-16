package constants;

public class LoginLocator {
    public static final String emailInput = "//input[@data-qa='login-email']";
    public static final String passwordInput = "//input[@data-qa='login-password']";
    public static final String loginBtn = "//button[@data-qa='login-button']";

    //check
    public static final String loginSuccess = "Logged in as";
    public static final String deleteAccItem = "Delete Account";
    public static final String logoutItem = "Logout";
    public static final String emailEmpty = "Email is required";
    public static final String passwordEmpty = "Password is required";
    public static final String emailIvl = "Invalid email format";
    public static final String unknownEmail = "Email does not exist or incorrect information";
    public static final String incorrectPassword = "Your email or password is incorrect!";
}
