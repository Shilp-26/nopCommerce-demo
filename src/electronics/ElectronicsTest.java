package electronics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import Utilities.Utility;
import org.openqa.selenium.Keys;

import java.util.Random;

public class ElectronicsTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {
        mouseHoverOnly(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Electronics ']"));
        mouseHoverClick(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));
        verifyText("Cell phones", getTextFromElement(By.xpath("//h1[contains(text(),'Cell phones')]")), "Incorrect Page2");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        verifyUserShouldNavigateToCellPhonesPageSuccessfully();
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));
        sendKeysToElement(By.xpath("//div[@class='item-grid']//a[contains(text(),'Nokia Lumia 1020')]"), Keys.ENTER);
        verifyText("$349.00", getTextFromElement(By.xpath("//span[contains(text(),' $349.00 ')]")), "Incorrect Price");
        sendKeysToElement(By.xpath("//input[@id='product_enteredQuantity_20']"), Keys.BACK_SPACE + "2");
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));
        verifyText("shopping cart", getTextFromElement(By.xpath("//a[contains(text(),'shopping cart')]")), "Error");
        clickOnElement(By.xpath("//span[@title='Close']"));
        mouseHoverOnly(By.xpath("//span[contains(text(),'Shopping cart')]"));
        mouseHoverClick(By.xpath("//button[contains(text(),'Go to cart')]"));
        verifyText("Shopping cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Shopping cart not found");
        verifyText("(2)", getTextFromElement(By.xpath("//span[contains(text(),'(2)')]")), "Incorrect Quantity");
        verifyText("$698.00", getTextFromElement(By.xpath("//span[contains(text(),'$698.00') and @class='product-subtotal']")), "Error");
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        verifyText("Welcome, Please Sign In!", getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));
        verifyText("Register", getTextFromElement(By.xpath("//h1[contains(text(),'Register')]")), "Error");
        clickOnElement(By.xpath("//input[@id='gender-male']"));
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "John");
        sendTextToElement(By.xpath("//input[@id='LastName']"), "Anderson");
        clickOnElement(By.id("Email"));
        Random randomEmail = new Random();
        int randomInt = randomEmail.nextInt(1000);
        sendKeysToElement(By.xpath("//input[@id='Email']"), "username" + randomInt + "@gmail.com");
        sendTextToElement(By.id("Password"), "Prime123");
        sendTextToElement(By.id("ConfirmPassword"), "Prime123");
        clickOnElement(By.xpath("//button[@id='register-button']"));
        verifyText("Your registration completed", getTextFromElement(By.xpath("//div[contains(text(),'Your registration completed')]")), "Registration failed");
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        verifyText("Shopping cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Error");
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "26 Napier Road");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "HA0 2QP");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07593873654");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.xpath("//input[@id='shippingoption_2']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='CreditCardType']"), "Visa");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Shilp Patel");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5356 6548 1418 5420");
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='ExpireMonth']"), "11");
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='ExpireYear']"), "2024");
        sendKeysToElement(By.xpath("//input[@id='CardCode']"), "098");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        verifyText("Credit Card", getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]")), "Incorrect payment method");
        verifyText("2nd Day Air", getTextFromElement(By.xpath("//span[contains(.,'2nd Day Air')]")), "Error");
        verifyText("$698.00", getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        verifyText("Thank you", getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")), "Error");
        verifyText("Your order has been successfully processed!", getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        verifyText("Welcome to our store", getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")), "Error");
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://demo.nopcommerce.com/" );


    }

}
