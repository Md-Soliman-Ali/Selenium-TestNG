package TestRunner;

import Pages.Login;
import Setup.Setup;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;


public class LoginTestRunner extends Setup {

    Login objLogin;

    @Test
    public void doLogin() throws Exception {
        driver.get("http://automationpractice.com");

        objLogin = new Login(driver);

        /*String user = objLogin.doLogin("testuser4554@test.com", "P@ssword123");
        Assert.assertEquals(user, "Test User");*/

        // Convert JsonFile To JsonObject
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject jsonObject = (JSONObject) obj;

        String email = (String) jsonObject.get("email");
        String password = (String) jsonObject.get("password");

        String user = objLogin.doLogin(email, password);
        Assert.assertEquals(user, "Test User");
    }
}
