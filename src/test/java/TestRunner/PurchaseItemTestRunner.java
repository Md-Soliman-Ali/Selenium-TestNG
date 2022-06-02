package TestRunner;

import Pages.PurchaseItem;
import Pages.SignIn;
import Setup.Setup;
import Utils.Utils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseItemTestRunner extends Setup {

    PurchaseItem objPurchase;
    SignIn objSignIn;
    Utils utils;

    // Pre-Requisite
    @BeforeTest (groups = "purchase_product")
    public void doLogin() throws IOException, ParseException, InterruptedException {
        driver.get("http://automationpractice.com");

        utils = new Utils(driver);
        utils.readJSONFile();
        String email = utils.getEmail();
        String password = utils.getPassword();

        objSignIn = new SignIn(driver);
        objSignIn.doLogin(email, password);
    }

    @Test(enabled = true, description = "check cart")
    public void checkHasCart() throws Exception {
        objPurchase = new PurchaseItem(driver);
        boolean status = objPurchase.checkHasCart();
        Assert.assertEquals(status, true);
        utils.addDescription("User can view cart");
    }

    @Test(enabled = true, description = "check order history")
    public void checkOrderHistory() throws Exception {
        objPurchase = new PurchaseItem(driver);
        String headerText = objPurchase.orderHistory();
        Assert.assertEquals(headerText, "ORDER HISTORY");
        utils.addDescription("user can view his/her order history");
    }

    @Test(enabled = true, description = "search products", groups = "purchase_product")
    public void checkSearchTextBox() throws Exception {
        objPurchase = new PurchaseItem(driver);
        String result = objPurchase.checkSearch();
        Assert.assertTrue(result.contains("results have been found"));
        utils.addDescription("user can search by product in search box");
    }

    @Test(enabled = true, description = "purchase product", groups = "purchase_product")
    public void doPurchase() throws Exception {
        objPurchase = new PurchaseItem(driver);
        String successMessage = objPurchase.purchaseItem();
        Assert.assertEquals(successMessage, "Your order on My Store is complete.");
        utils.addDescription("user can purchase a product successfully");
    }
}
