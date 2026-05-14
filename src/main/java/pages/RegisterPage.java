package pages;

import com.microsoft.playwright.Page;
import config.ConfigReader;

import java.util.Base64;

import static constants.RegisterLocator.*;

public class RegisterPage extends BasePage {
    public RegisterPage(Page page) {
        super(page);
    }

    public void blurActiveElement() {
        page.evaluate("document.activeElement.blur()");
    }

    public void clickSignupBtn() {
        page.click(signUpBtn);
    }

    public void fillName(String name) {
        fillElement(nameInput, name);
    }

    public void fillEmail(String email) {
        fillElement(emailInput, email);
    }

    public void clickSignup() {
        clickElement(signupBtn);
    }

    public void checkGender() {
        page.check(genderRBtn);
    }

    public void fillPassword(String password) {
        fillElement(passwordInput, password);
    }

    public void selectBirtDay() {
        page.locator(daySelect).selectOption("5");
        page.locator(monthSelect).selectOption("2");
        page.locator(yearSelect).selectOption("2000");
    }

    public void checkNewsLetter() {
        page.check(newsLetter);
    }

    public void fillFirstName(String firstName) {
        fillElement(firstNameInput, firstName);
    }

    public void fillLastNameInput(String lastName) {
        fillElement(lastNameInput, lastName);
    }

    public void fillCompany(String company) {
        fillElement(companyInput, company);
    }

    public void fillAddress(String address) {
        fillElement(addressInput, address);
    }

    public void checkCountry() {
        page.locator(countrySelect).selectOption("Australia");
    }

    public void fillState(String state) {
        fillElement(stateInput, state);
    }

    public void fillCity(String city) {
        fillElement(cityInput, city);
    }

    public void fillZipcode(String zipcode) {
        fillElement(zipcodeInput, zipcode);
    }

    public void fillPhone(String phone) {
        fillElement(phoneInput, phone);
    }

    public void fillAddress2(String address2) {
        fillElement(address2Input, address2);
    }

    public void clickCreateAcc() {
        page.click(creatAcc);
    }

    public boolean isShowTitleSuccess() {
        if(page.isVisible(titleSuccess)) {
            return true;
        }
        return false;
    }

    public boolean isShowMessageSuccess() {
        if(page.isVisible(messageSuccess)) {
            return true;
        }
        return false;
    }

    public boolean isShowNameRequireMessage() {
        if (page.getByText(nameRequireMessage).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isEmailRequireMessage() {
        if (page.getByText(emailRequireMessage).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isAllFieldRequire() {
        if (page.getByText(allFieldRequireMessage).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isEmailIvlMessage() {
        if (page.getByText(emailIvlFormat).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isEmailExist() {
        if (page.getByText(emailExist).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isPasswordWeakMessage() {
        if (page.getByText(passwordWeakMessage).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isAddressRequired() {
        if (page.getByText(addressRequiredMessage).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isPhoneIvlMessage() {
        if (page.getByText(phoneIvlFormat).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isZipcodeIvl() {
        if (page.getByText(zipcodeIvl).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isFirstNameEmptyMessage() {
        if (page.getByText(firstNameEmpty).isVisible()) {
            return true;
        }
        return false;
    }

    public boolean isPasswordShortMessage() {
        if (page.getByText(passwordShortMessage).isVisible()) {
            return true;
        }
        return false;
    }
}
