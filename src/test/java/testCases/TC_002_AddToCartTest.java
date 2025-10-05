package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AddToCartPage;
import testBase.BaseClass;

public class TC_002_AddToCartTest extends BaseClass {

    @Test(groups = {"Sanity", "Master"})
    public void verify_addToCart() {
        logger.info("******** Starting TC_002_AddToCartTest *******");

        try {
            // ✅ Reusable Login (from BaseClass)
            loginToApplication();

            // ✅ Add to Cart
            AddToCartPage addToCartPage = new AddToCartPage(driver);
            addToCartPage.clickAddToCart();

            // ✅ Verify item was added
            WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-bike-light"));
            Assert.assertTrue(removeButton.isDisplayed(), "Item was not added to cart successfully.");

            logger.info("******** Finished TC_002_AddToCartTest *******");

        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
}
