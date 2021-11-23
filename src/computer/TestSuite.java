package computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import Utilities.Utility;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {

        clickOnElement(By.xpath("//a[@href='/computers']"));
        clickOnElement(By.xpath("//img[@alt='Picture for category Desktops']"));
        selectByVisibleTextFromDropdown(By.name("products-orderby"), "Name: Z to A");
        verifyText("Name: Z to A", getTextFromElement(By.xpath("//option[contains(text(),'Name: Z to A')]")), "Error, Message not displayed");
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        clickOnElement(By.xpath("//a[@href='/computers']"));
        clickOnElement(By.xpath("//img[@alt='Picture for category Desktops']"));

        selectByVisibleTextFromDropdown(By.name("products-orderby"), "Name: A to Z");
        sendKeysToElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//button[1]"), Keys.ENTER);
        verifyText("Build your own computer", getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]")), "Message not found");

        selectByVisibleTextFromDropdown(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='product_attribute_2']"), "8GB [+$60.00]");
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
        verifyText("$1,475.00", getTextFromElement(By.xpath("//span[contains(text(),'$1,475.00')]")), "Incorrect price!");
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        verifyText("shopping cart", getTextFromElement(By.xpath("//a[contains(text(),'shopping cart')]")), "Incorrect Message");
        clickOnElement(By.className("close"));
        mouseHoverClick(By.xpath("//span[contains(text(),'Shopping cart')]"));
        verifyText("Shopping cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Incorrect page");
        sendKeysToElement(By.xpath("(//input[contains(@id, 'itemquantity')])"), Keys.BACK_SPACE + "2");
        clickOnElement(By.xpath("//button[text()='Update shopping cart']"));
        verifyText("$2,950.00", getTextFromElement(By.className("product-subtotal")), "Incorrect price");
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        verifyText("Welcome, Please Sign In!", getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Shilp");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Patel");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "Prime24@gmail.com");
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "26 Napier Road");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "HA0 2QP");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07593873654");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='CreditCardType']"), "Master card");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Shilp Patel");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5356 6548 1418 5420");
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='ExpireMonth']"), "11");
        selectByVisibleTextFromDropdown(By.xpath("//select[@id='ExpireYear']"), "2024");
        sendKeysToElement(By.xpath("//input[@id='CardCode']"), "098");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        verifyText("Credit Card", getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]")), "Incorrect payment method");
        verifyText("Next Day Air", getTextFromElement(By.xpath("//span[contains(text(),'Next Day Air') and @class='value']")), "Incorrect Shipping method");
        verifyText("$2,950.00", getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$2,950.00')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        verifyText("Thank you", getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")), "Incorrect Message");
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        verifyText("Welcome to our store", getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")), "Incorrect page");
    }

    @After
    public void tearDown(){
        closeBrowser();
    }


}
