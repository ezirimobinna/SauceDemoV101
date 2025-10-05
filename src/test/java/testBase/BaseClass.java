package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;  // Log4j
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master", "Datadriven"})
    @Parameters({"os", "browser"})
    public void setup(
            @Optional("windows") String os,
            @Optional("chrome") String br) throws IOException {

        // Load Config.properties file
        FileReader file = new FileReader("src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        // Browser setup
        switch (br.toLowerCase()) {
            case "chrome": driver = new ChromeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            default: throw new IllegalArgumentException("Invalid browser name: " + br);
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Launch Application
        driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    // ✅ Reusable Login Helper Method
    public void loginToApplication() {
        logger.info("Logging into the application...");

        try {
            LoginPage lp = new LoginPage(driver);
            String username = p.getProperty("username");
            String password = p.getProperty("password");

            Assert.assertNotNull(username, "Username property missing");
            Assert.assertNotNull(password, "Password property missing");

            lp.setUsername(username);
            lp.setPassword(password);
            lp.clicklogin();

            // Optional: verify login success
            MyAccountPage myAccount = new MyAccountPage(driver);
            boolean isDisplayed = myAccount.isMyAccountPageExists();
            Assert.assertTrue(isDisplayed, "Login failed: My Account page not displayed.");

            logger.info("Login successful!");

        } catch (Exception e) {
            Assert.fail("Login failed due to exception: " + e.getMessage());
        }
    }

    // ✅ Optional Logout Helper
    public void logoutFromApplication() {
        try {
            MyAccountPage myAccount = new MyAccountPage(driver);
            myAccount.clickLogout(); // Implement clickLogout() in your MyAccountPage
            logger.info("Logout successful!");
        } catch (Exception e) {
            logger.warn("Logout not executed: " + e.getMessage());
        }
    }

    // ✅ Screenshot Helper
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
