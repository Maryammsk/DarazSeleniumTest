package Utilis;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (tlDriver.get() == null) {
            initDriver();
        }
        return tlDriver.get();
    }

    private static void initDriver() {
        String browser = ConfigReader.get("browser");
        if (browser == null) browser = "chrome";

        if (browser.equalsIgnoreCase("chrome")) {
            // Set your local ChromeDriver path here:
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");


            ChromeOptions options = new ChromeOptions();
            boolean headless = ConfigReader.getBoolean("headless", false);
            if (headless) options.addArguments("--headless=new");

            options.addArguments("--start-maximized");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            tlDriver.set(new ChromeDriver(options));
        } else {
            throw new RuntimeException("Currently only chrome is supported in DriverFactory");
        }
    }

    public static void quitDriver() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }
}
