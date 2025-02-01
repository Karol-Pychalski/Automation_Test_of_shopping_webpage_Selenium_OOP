package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import utils.WebDriverSetup;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ProductSelectionTest {
    WebDriver driver;
    WebDriverWait wait;
    MainPage mainPage;
    LoginPage loginPage;
    ProductParamsSelection productParamsSelection;
    OrderPage orderPage;
    OrderHistory orderHistory;

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

        //get order reference value:
        String orderValue = orderPage.getOrderReferenceValue();
        System.out.println("Order ref is: " + orderValue);

        //go to order history page:
        OrderHistory orderHistory = new OrderHistory(driver, wait);
        orderHistory.goToCustomerAccount();
        Assert.assertTrue(orderHistory.clickToCustomerAccount.isDisplayed());
        Assert.assertTrue(orderHistory.clickToOrderHistory.isDisplayed());

        orderHistory.goToOrderHistoryPage();
        //verify order reference number (value):
        Assert.assertEquals(orderValue, orderHistory.verifyOrderOnTheHistoryList());


        //to do:
        //1. Check if the order is on the order's list - done
        //2. Make verification of total price in order history
        //3. Make price discount verification (before order confirmation) - check if price is -20% and check if selected quantity is 5 (assertion)
    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }
}