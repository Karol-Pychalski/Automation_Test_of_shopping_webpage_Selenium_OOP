package pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class OrderPage {
    WebDriver driver;
    WebDriverWait wait;

    public OrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "add-to-cart")
    private WebElement addToCartButton;

    @FindBy(xpath="//a[contains(text(), 'Proceed to checkout')]")
    private WebElement checkoutButton;

    @FindBy(name="confirm-addresses")
    private WebElement confirmAddressButton;

    @FindBy(name="confirmDeliveryOption")
    private  WebElement confirmDeliveryButton;

    @FindBy(id="payment-option-1")
    private WebElement paymentOptionSelection;

    @FindBy(id="conditions_to_approve[terms-and-conditions]")
    public WebElement conditionsAprroveCheckbox;

    @FindBy(xpath = "//button[contains(text(), 'Place order')]")
    private WebElement placeOrderButton;

    //adding selected product to basket:
    public void addProductToBasket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.elementToBeClickable(addToCartButton),
                ExpectedConditions.visibilityOf(addToCartButton)
        ));
        addToCartButton.click();
    }

    //go to checkout option:
    public void goToCheckout() {
        checkoutButton.click();
    }

    public void confirmAddress() {
        confirmAddressButton.click();
    }

    public void confirmDelivery() {
        confirmDeliveryButton.click();
    }

    public void paymentSelection() {
        paymentOptionSelection.click();
        conditionsAprroveCheckbox.click();
    }

    public void placeOrder() {
        placeOrderButton.click();
    }

    public void takeScreenshot(String filePath) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(srcFile, new File(filePath));
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}