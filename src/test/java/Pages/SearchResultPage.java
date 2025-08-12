package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilis.ConfigReader;

import java.time.Duration;
import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Stable locator for products
    private By productCards = By.cssSelector("div[data-qa-locator='product-item']");

    // Price filter inputs
    private By minPriceInput = By.xpath("//input[@placeholder='Min']");
    private By maxPriceInput = By.xpath("//input[@placeholder='Max']");
    private By applyPriceButton = By.xpath("//button[contains(.,'Apply')]");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(
                ConfigReader.getInt("explicit.wait", 10)));
    }

    public void chooseBrand(String brandName) {
        By brandCheckbox = By.xpath("//label[contains(., '" + brandName + "')]");
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(brandCheckbox));
        el.click();
        waitUntilResultsLoad();
    }

    public void setPriceRange(String minPrice, String maxPrice) {
        WebElement minEl = wait.until(ExpectedConditions.visibilityOfElementLocated(minPriceInput));
        WebElement maxEl = wait.until(ExpectedConditions.visibilityOfElementLocated(maxPriceInput));
        minEl.clear();
        minEl.sendKeys(minPrice);
        maxEl.clear();
        maxEl.sendKeys(maxPrice);
        driver.findElement(applyPriceButton).click();
        waitUntilResultsLoad();
    }

    public void waitUntilResultsLoad() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(productCards, 0));
    }

    public int getResultsCount() {
        waitUntilResultsLoad();
        List<WebElement> products = driver.findElements(productCards);
        return products.size();
    }

    public void clickFirstProduct() {
        waitUntilResultsLoad();
        driver.findElements(productCards).get(0).click();
    }
}
