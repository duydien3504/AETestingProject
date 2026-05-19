package pages;

import com.microsoft.playwright.Page;
import utils.FileUtils;

import static constants.ContactUsConstant.*;

public class ContactUsPage extends BasePage {

    public ContactUsPage(Page page) {
        super(page);
    }

    @Override
    public void navigateWebsite() {
        page.navigate(baseUrl);
        page.waitForLoadState(com.microsoft.playwright.options.LoadState.DOMCONTENTLOADED);
    }

    public void clickContactUsLink() {
        clickElement(contactUsLink);
    }

    public void fillName(String name) {
        fillElement(nameInput, name);
    }

    public void fillEmail(String email) {
        fillElement(emailInput, email);
    }

    public void fillSubject(String subject) {
        fillElement(subjectInput, subject);
    }

    public void fillMessage(String message) {
        fillElement(messageInput, message);
    }

    public void uploadFile(String filePath) {
        FileUtils.uploadFile(page, uploadFileInput, filePath);
    }

    public void clickSubmit() {
        page.onceDialog(dialog -> {
            System.out.println("Accepting dialog: " + dialog.message());
            dialog.accept();
        });
        clickElement(submitBtn);
    }

    public void clickHomeButton() {
        page.locator("//a[contains(text(), 'Home') and contains(@class, 'btn')]").click();
        page.waitForLoadState(com.microsoft.playwright.options.LoadState.DOMCONTENTLOADED);
    }

    public boolean isSuccessAlertVisible() {
        if (page.isVisible(successAlert)) {
            String text = page.locator(successAlert).textContent();
            return text != null && text.contains(successMessageText);
        }
        return false;
    }

    public String getValidationMessage(String selector) {
        page.waitForSelector(selector);
        return (String) page.locator(selector).evaluate("el => el.validationMessage");
    }

    public boolean isFieldInvalid(String selector) {
        // Use a short timeout so that if the page navigated away (because the site has a bug and submitted anyway),
        // the test fails quickly instead of waiting 30 seconds.
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(3000));
        return (boolean) page.locator(selector).evaluate("el => !el.validity.valid");
    }

    public String getNameValidationMessage() {
        return getValidationMessage(nameInput);
    }

    public String getEmailValidationMessage() {
        return getValidationMessage(emailInput);
    }

    public String getSubjectValidationMessage() {
        return getValidationMessage(subjectInput);
    }

    public String getMessageValidationMessage() {
        return getValidationMessage(messageInput);
    }

    public boolean isNameFieldInvalid() {
        return isFieldInvalid(nameInput);
    }

    public boolean isEmailFieldInvalid() {
        return isFieldInvalid(emailInput);
    }

    public boolean isSubjectFieldInvalid() {
        return isFieldInvalid(subjectInput);
    }

    public boolean isMessageFieldInvalid() {
        return isFieldInvalid(messageInput);
    }
}
