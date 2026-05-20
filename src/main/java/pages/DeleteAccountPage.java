package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import static constants.DeleteAccountConstant.*;

public class DeleteAccountPage extends BasePage {

    public DeleteAccountPage(Page page) {
        super(page);
    }

    public void clickDeleteAccount() {
        clickElement(deleteAccountLink);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public boolean isAccountDeletedVisible() {
        return page.isVisible(accountDeletedTitle);
    }

    public void clickContinue() {
        clickElement(continueBtn);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
}
