package TestRunner;

import Pages.SignIn;
import Setup.Setup;
import Utils.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;


public class SignInTestRunner extends Setup {

    SignIn objSignIn;

    @Test(enabled = true, priority = 1, description = "Login with valid username and password", groups = "login")
    public void doLogin() throws Exception {
        driver.get("http://automationpractice.com");

        objSignIn = new SignIn(driver);

        /*String user = objLogin.doLogin("testuser4554@test.com", "P@ssword123");
        Assert.assertEquals(user, "Test User");*/

        // Convert JsonFile To JsonObject
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader("./src/test/resources/user.json"));
        JSONObject jsonObject = (JSONObject) obj;

        String email = (String) jsonObject.get("email");
        String password = (String) jsonObject.get("password");

        String user = objSignIn.doLogin(email, password);
        Assert.assertEquals(user, "Test User");

        // Sign Out
        driver.findElement(By.xpath("//a[@class='logout']")).click();
    }

    @Test(enabled = true, priority = 2, description = "Login with wrong password", groups = "login")
    public void doLoginForWrongPassword() throws Exception {
        driver.get("http://automationpractice.com");

        objSignIn = new SignIn(driver);

        Utils util = new Utils(driver);
        util.readJSONArray(0);

        String authError = objSignIn.doLoginForWrongPassword(util.getEmail(), util.getPassword());
        Assert.assertEquals(authError, "Authentication failed.");
    }

    @Test(enabled = true, priority = 3, description = "Login with invalid email", groups = "login")
    public void doLoginForInvalidEmail() throws Exception {
        driver.get("http://automationpractice.com");

        objSignIn = new SignIn(driver);

        Utils util = new Utils(driver);
        util.readJSONArray(1);

        String error = objSignIn.doLoginForInvalidEmail(util.getEmail(), util.getPassword());
        Assert.assertEquals(error, "Invalid email address.");
    }
}
