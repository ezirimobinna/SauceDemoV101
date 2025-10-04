package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    
    
    // Example locator: adjust to your actual app
    @FindBy(xpath = "//div[@class='app_logo']")  
    private WebElement headingMyAccount;
    
    //Locator for menu
    @FindBy (xpath ="/button[@id='react-burger-menu-btn']")
    private WebElement btnmenu;
    
    //Locate for Logout
    @FindBy (xpath ="//a[@id='logout_sidebar_link']")
    private WebElement lnkLogout;
    
    

    // Method to get the heading text

    public boolean isMyAccountPageExists() {
    	try {
    		return headingMyAccount.isDisplayed() && !headingMyAccount.getText().isEmpty();
    	} catch (Exception e) {
    		return false;
    	}
    }


    // Method to click the menu button
    public void clickMenuButton() {
     btnmenu.click();
    }

    // Method to click the logout link
    public void clickLogout() {
     lnkLogout.click();
    }

    
    

}
