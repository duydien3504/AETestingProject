package constants;

public class ContactUsConstant {
    public static final String contactUsLink = "//a[@href='/contact_us']";
    public static final String nameInput = "//input[@data-qa='name']";
    public static final String emailInput = "//input[@data-qa='email']";
    public static final String subjectInput = "//input[@data-qa='subject']";
    public static final String messageInput = "//textarea[@data-qa='message']";
    public static final String uploadFileInput = "//input[@name='upload_file']";
    public static final String submitBtn = "//input[@name='submit']";

    // Expected Message / Check constants
    public static final String successAlert = "//div[@id='contact-page']//div[contains(@class, 'alert-success')]";
    public static final String successMessageText = "Success! Your details have been submitted successfully.";

    // Expected Validation Error Messages (based on data test)
    public static final String nameRequiredMessage = "Name is required";
    public static final String emailRequiredMessage = "Email is required";
    public static final String emailInvalidMessage = "Invalid email format";
    public static final String subjectRequiredMessage = "Subject is required";
    public static final String messageRequiredMessage = "Message is required";
    public static final String allFieldsRequiredMessage = "All fields are required";
}
