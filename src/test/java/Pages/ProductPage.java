package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilis.ConfigReader;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Look for "Free Shipping" or "Free delivery" text on the page
    private By shippingInfo = By.xpath("//*[contains(text(), 'Free Shipping') or contains(text(),'Free delivery')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(
                ConfigReader.getInt("explicit.wait", 10)));
    }

    public boolean hasFreeShipping() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            return !driver.findElements(shippingInfo).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
