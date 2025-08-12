package Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Utilis.ConfigReader;
import Utilis.DriverFactory;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();

        int implicitWait = ConfigReader.getInt("implicit.wait", 5);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        driver.get(ConfigReader.get("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
