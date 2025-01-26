package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.WebDriverSetup;

public class LoginTest {
    WebDriver driver;
    WebDriverWait wait;
    String userName = "nbaaaiwjekdpkqppub@hthlm.com";
    String password = "0Z1m9A2P!";
    String incorrectUserName = "test@gmail.com";
    String incorrectPassword = "wrongPass";

    @Before
    public void setUp() {
        driver = WebDriverSetup.driverSetup();
        driver.get("https://prod-course.coderslab.com/index.php?");
    }

    @Test
    public void correctLogin() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.correctLoginCredentials(userName, password);
        String actualMessage = loginPage.userShouldBeLoggedIn();
        String expectedMessage = "Your account";
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void incorrectLogin() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.incorrectLoginCredentials(incorrectUserName, incorrectPassword);
        String actualText = loginPage.userShouldNotBeLoggedIn();
        String expectedMessage = "Authentication failed.";
        Assert.assertTrue(actualText.contains(expectedMessage));
    }
}