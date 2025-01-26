package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    String incorrectUserName = "test@gmail.com";
    String incorrectPassword = "wrongPass";

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void goToLoginPage() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    public void typeCorrectLogin(String userName) {
        driver.findElement(By.id("field-email")).sendKeys(userName);
    }

    public void typeCorrectPassword(String password) {
        driver.findElement(By.id("field-password")).sendKeys(password);
    }

    public void typeIncorrectLogin(String incorrectUserName) {
        driver.findElement(By.id("field-email")).sendKeys(incorrectUserName);
    }
    public void typeIncorrectPassword(String incorrectPassword) {
        driver.findElement(By.id("field-password")).sendKeys(incorrectPassword);
    }
    public void clickLoginButton() {
        driver.findElement(By.id("submit-login")).click();
    }

    public String userShouldBeLoggedIn() {
        WebElement correctLoginMessage = driver.findElement(By.xpath("//h1[contains(text(), '  Your account')]"));
        return correctLoginMessage.getText();
    }

    public String userShouldNotBeLoggedIn() {
        WebElement autFialedMessage = driver.findElement(By.xpath("//li[contains(text(), 'Authentication failed.')]"));
        return autFialedMessage.getText();
    }

    public void correctLoginCredentials(String userName, String password) {
        goToLoginPage();
        typeCorrectLogin(userName);
        typeCorrectPassword(password);
        clickLoginButton();
        userShouldBeLoggedIn();
    }

    public void incorrectLoginCredentials(String incorrectUserName, String incorrectPassword) {
        goToLoginPage();
        typeIncorrectLogin(incorrectUserName);
        typeIncorrectPassword(incorrectPassword);
        clickLoginButton();
        userShouldNotBeLoggedIn();
    }
}