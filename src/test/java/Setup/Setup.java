package Setup;

import Utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Setup {

    public WebDriver driver;
    WebDriverWait wait;

    @BeforeTest(groups = {"login", "purchase_product"})
    public void setup() {
        System.setProperty("webdriver.gecko.driver", ".//geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();

        options.addArguments("--headed");
        // options.addArguments("--headless");

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    //This method executes after every test execution
    public void screenShot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Utils util = new Utils(driver);
                util.takeScreenShot();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
    }

    @AfterTest(groups = {"login", "purchase_product"})
    public void finishTest() {
        driver.close();
    }
}
