package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderHistory {
    WebDriver driver;
    WebDriverWait wait;

    public OrderHistory(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='account']")
    public WebElement clickToCustomerAccount;

    @FindBy(id = "history-link")
    public WebElement clickToOrderHistory;

    @FindBy(xpath = "//th[@scope='row']")
    public WebElement orderHistoryReferenceRow;

    public void goToCustomerAccount() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=my-account");
        clickToCustomerAccount.click();
    }

    public void goToOrderHistoryPage() {
        wait.until(ExpectedConditions.visibilityOf(clickToOrderHistory));
        clickToOrderHistory.click();
    }

    public String verifyOrderOnTheHistoryList() {
        String orderRefInHistoryPage = orderHistoryReferenceRow.getText();
        System.out.println("ref number at order page is: " + orderRefInHistoryPage);
        return orderHistoryReferenceRow.getText();
    }
}