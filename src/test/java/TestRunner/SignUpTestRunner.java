package TestRunner;

import Pages.SignUp;
import Setup.Setup;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;

public class SignUpTestRunner extends Setup {

    SignUp objSignup;

    @Test(enabled = true, description = "Signup successful")
    public void doSignup() throws Exception {
        driver.get("http://automationpractice.com");
        objSignup = new SignUp(driver);
        objSignup.memberSignUp();
        Allure.description("Signup successful");
    }
}
