package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddToCartPage extends BasePage {
	
	public AddToCartPage (WebDriver driver) {
        super(driver);
    }

	// ===== Locators =====
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bike-light']")
    private WebElement btnAddToCart;
    
    // ====== Action =========    

    public void clickAddToCart() {
    	btnAddToCart.click();
    }

}
