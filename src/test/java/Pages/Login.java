package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

    WebDriver driver;

    // Sign in
    @FindBy(className = "login")
    WebElement linkLogin;

    // Email Address
    @FindBy(id = "email")
    WebElement txtEmail;

    // Password
    @FindBy(id = "passwd")
    WebElement txtPassword;

    // Sign in
    @FindBy(id = "SubmitLogin")
    WebElement btnSubmitLogin;

    // User Name Validation
    @FindBy(xpath = "//span[contains(text(),'Test User')]")
    WebElement lblUserName;

    // Constructor
    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String doLogin(String email, String password) throws InterruptedException {
        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmitLogin.click();
        return lblUserName.getText();
    }
}
