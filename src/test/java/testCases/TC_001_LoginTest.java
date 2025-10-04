package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_001_LoginTest extends BaseClass {

    @Test(groups = {"Sanity", "Master"})
    public void verify_login() {
        logger.info("******** Starting TC_001_LoginTest *******");

        try {
			/*
			 * // Login Page LoginPage lp = new LoginPage(driver);
			 * lp.setUsername(p.getProperty("username"));
			 * lp.setPassword(p.getProperty("password")); lp.clicklogin();
			 */
        	
        	LoginPage lp = new LoginPage(driver);
        	String username = p.getProperty("username");
        	String password = p.getProperty("password");

        	Assert.assertNotNull(username, "Username property missing");
        	Assert.assertNotNull(password, "Password property missing");

        	lp.setUsername(username);
        	lp.setPassword(password);
        	lp.clicklogin();

            // My Account Page verification
            MyAccountPage mop = new MyAccountPage(driver);
            boolean targetPage = mop.isMyAccountPageExists();

            Assert.assertTrue(targetPage, "Login failed: My Account page is not displayed.");

        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }

        logger.info("******** Finished TC_001_LoginTest *******");
    }
}
