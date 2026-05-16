package pages;

import com.microsoft.playwright.Page;

import static constants.LoginLocator.*;

public class LoginPage extends BasePage{
    public LoginPage (Page page) {
        super(page);
    }

    public void fillEmail(String email) {
        fillElement(emailInput, email);
    }

    public void fillPassword(String password) {
        fillElement(passwordInput, password);
    }

    public void clickLoginBtn() {
        clickElement(loginBtn);
    }

    public boolean isLoginSuccess() {
        if(page.getByText(loginSuccess).isVisible() && page.getByText(deleteAccItem).isVisible() && page.getByText(logoutItem).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isEmailEmpty() {
        if (page.getByText(emailEmpty).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isPasswordEmpty() {
        if (page.getByText(passwordEmpty).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isEmailIvl() {
        if(page.getByText(emailIvl).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isEmailUnkwown() {
        if (page.getByText(unknownEmail).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isCorrectPass() {
        if(page.getByText(incorrectPassword).isVisible()) {
            return true;
        }
        return false;
    }
}
