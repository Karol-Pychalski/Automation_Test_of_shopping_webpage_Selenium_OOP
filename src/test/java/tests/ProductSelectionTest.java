package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.OrderPage;
import pages.ProductParamsSelection;
import utils.WebDriverSetup;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ProductSelectionTest {
    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    LoginPage loginPage;
    ProductParamsSelection productParamsSelection;
    OrderPage orderPage;

    String userName = "nbaaaiwjekdpkqppub@hthlm.com";
    String password = "0Z1m9A2P!";

    @Before
    public void setUp() {
        driver = WebDriverSetup.driverSetup();
        driver.get("https://prod-course.coderslab.com/index.php?");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void productSelectionTest() {
        //login process:
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.correctLoginCredentials(userName, password);

        //go to homepage from my account page:
        MainPage mainPage = new MainPage(driver);
        mainPage.goToHomePage();
        mainPage.selectingProduct();

        //product params selecting:
        ProductParamsSelection productParamsSelection = new ProductParamsSelection(driver, wait);
        Assert.assertTrue("Discount is not displayed!", productParamsSelection.isDiscountDisplayed());
        assertEquals("Discount text is incorrect!", "-20%", productParamsSelection.getDiscountText());

        productParamsSelection.sizeSelection("M");
        productParamsSelection.clearQuantityField();
        String actualQuantity = productParamsSelection.selectedQuantity.getText();
        assertEquals("", actualQuantity);
        productParamsSelection.quantitySelection(5);

        //order process realization:
        OrderPage orderPage = new OrderPage(driver, wait);
        orderPage.addProductToBasket();
        orderPage.goToCheckout();
        orderPage.goToCheckout();
        orderPage.confirmAddress();
        orderPage.confirmDelivery();
        orderPage.paymentSelection();
        Assert.assertTrue("Checkbox 'terms of service' nie został zaznaczony!",
                orderPage.conditionsAprroveCheckbox.isSelected());
        orderPage.placeOrder();

        //take screenshot:
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "D:\\IT wiedza\\10 - kurs CodersLab\\testy\\FinalProject2\\screenshoots_" + timestamp + ".png";
        orderPage.takeScreenshot(filePath);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}