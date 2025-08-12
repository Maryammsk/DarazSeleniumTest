package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.SearchResultPage;
import Pages.ProductPage;
import Utilis.ConfigReader;

public class DarazTest extends BaseTest {

    @Test(description = "Simple Daraz search and product free shipping check")
    public void darazSimpleSearchTest() {
        HomePage home = new HomePage(driver);
        SearchResultPage results = new SearchResultPage(driver);
        ProductPage product = new ProductPage(driver);

        String keyword = ConfigReader.get("search.keyword");

        // 1. Search
        home.search(keyword);

        // 2. Wait and count products
        int count = results.getResultsCount();
        System.out.println("Products found: " + count);
        Assert.assertTrue(count > 0, "Expected products after search, found none.");

        // 3. Click first product
        results.clickFirstProduct();

        // 4. Check free shipping (optional: just print, no assert)
        boolean freeShipping = product.hasFreeShipping();
        System.out.println("Free shipping available: " + freeShipping);
    }
}
