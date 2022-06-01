package TestRunner;

import Pages.SignIn;
import Setup.Setup;
import Utils.ReadJSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;


public class SignInTestRunner extends Setup {

    SignIn objSignIn;

    @Test(enabled = false)
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
    }

    @Test(enabled = false)
    public void doLoginForWrongPassword() throws Exception {
        driver.get("http://automationpractice.com");

        objSignIn = new SignIn(driver);

        ReadJSONArray utils = new ReadJSONArray(driver);
        utils.readJSONArray(0);

        String authError = objSignIn.doLoginForWrongPassword(utils.getEmail(), utils.getPassword());
        Assert.assertEquals(authError, "Authentication failed.");
    }

    @Test(enabled = true)
    public void doLoginForInvalidEmail() throws Exception {
        driver.get("http://automationpractice.com");

        objSignIn = new SignIn(driver);

        ReadJSONArray utils = new ReadJSONArray(driver);
        utils.readJSONArray(1);

        String error = objSignIn.doLoginForInvalidEmail(utils.getEmail(), utils.getPassword());
        Assert.assertEquals(error, "Invalid email address.");
    }
}