package constants;

public class RegisterLocator {
    public static final String signUpBtn = "//a[contains(text(), 'Signup / Login')]";
    public static final String nameInput = "//input[@name='name']";
    public static final String emailInput = "//input[@name='email' and @data-qa='signup-email']";
    public static final String signupBtn = "//button[@type='submit' and @data-qa='signup-button']";

    public static final String genderRBtn = "//input[@id='id_gender1']";
    public static final String passwordInput = "//input[@id='password']";

    public static final String daySelect = "//select[@id='days']";
    public static final String monthSelect = "//select[@id='months']";
    public static final String yearSelect = "//select[@id='years']";

    public static final String newsLetter = "//input[@type='checkbox' and @name = 'newsletter']";
    public static final String firstNameInput = "//input[@name = 'first_name']";
    public static final String lastNameInput = "//input[@name = 'last_name']";
    public static final String companyInput = "//input[@name = 'company']";
    public static final String addressInput = "//input[@name = 'address1']";
    public static final String address2Input = "//input[@name = 'address2']";
    public static final String countrySelect = "//select[@name = 'country']";
    public static final String stateInput = "//input[@name = 'state']";
    public static final String cityInput = "//input[@name = 'city']";
    public static final String zipcodeInput = "//input[@name = 'zipcode']";
    public static final String phoneInput = "//input[@id= 'mobile_number']";
    public static final String creatAcc = "//button[@type='submit' and @data-qa='create-account']";

    // Check
    public static final String titleSuccess = "//b[contains(text(), 'Account Created!')]";
    public static final String messageSuccess = "//p[contains(text(), 'Congratulations! Your new account has been successfully created!')]";
    public static final String nameRequireMessage = "Name is required";
    public static final String emailRequireMessage = "Email is required";
    public static final String allFieldRequireMessage = "All field is required";
    public static final String emailIvlFormat = "Invalid email format";
    public static final String emailExist = "Email Address already exist!";
    public static final String passwordWeakMessage = "Email Address already exist!";
    public static final String addressRequiredMessage = "Address is a required field";
    public static final String phoneIvlFormat = "Mobile Phone Ivl";
    public static final String zipcodeIvl = "Invalid Zipcode";
    public static final String firstNameEmpty = "First name is required";
    public static final String passwordShortMessage = "Password too short";
    public static final String continueBtn = "//a[@data-qa='continue-button']";
}
