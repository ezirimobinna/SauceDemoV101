package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
        super(driver);
    }
	
	@FindBy(xpath ="//input[@id='user-name']")
    private WebElement txtUsername;
	
	@FindBy(xpath ="//input[@id='password']")
    private WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='login-button']")
    private WebElement btnLogin;
	
	public void setUsername(String exp) {
		txtUsername.sendKeys(exp);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clicklogin() {
		btnLogin.click();
	}
}
