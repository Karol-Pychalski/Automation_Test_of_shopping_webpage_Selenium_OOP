package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    WebDriver driver;

    @FindBy(className = "logo")
    private WebElement homePageLogo;
    @FindBy(xpath = "//a[contains(text(), 'Hummingbird printed sweater')]")
    private WebElement linkToProduct;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //przejście na stronę główną:
    public void goToHomePage() {
        homePageLogo.click();
    }

    //kliknięcie w wybrany produkt:
    public void selectingProduct() {
        linkToProduct.click();
    }
}
