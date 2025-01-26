package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductParamsSelection {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "discount")
    private WebElement discount;

    @FindBy(className = "form-control-select")
    private WebElement sizeSelectionDropdown;

    @FindBy(id = "quantity_wanted")
    public WebElement selectedQuantity;

    public ProductParamsSelection(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public boolean isDiscountDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(discount));
        return discount.isDisplayed();
    }

    public String getDiscountText() {
        wait.until(ExpectedConditions.visibilityOf(discount));
        return discount.getText();
    }

    //selecting size M:
    public void sizeSelection(String selectedSize) {
        Select select = new Select(sizeSelectionDropdown);
        select.selectByVisibleText(selectedSize);
    }

    //clearing field Quantity from defaultValue='1':
    public void clearQuantityField() {
        selectedQuantity.sendKeys(Keys.CONTROL + "a");
        selectedQuantity.sendKeys(Keys.BACK_SPACE);
    }

    public void quantitySelection(int quantity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(selectedQuantity ,"value", ""));
        selectedQuantity.sendKeys(String.valueOf(quantity));
    }
}