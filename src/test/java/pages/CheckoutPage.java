package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators
     By newAddressRadio = By.xpath("//input[@name='payment_address' and @value='new']");
     By firstNameInput = By.id("input-payment-firstname");
     By lastNameInput = By.id("input-payment-lastname");
     By addressInput = By.id("input-payment-address-1");
     By cityInput = By.id("input-payment-city");
     By countryDropdown = By.id("input-payment-country");
     By regionDropdown = By.id("input-payment-zone");

     By paymentAddressContinueBtn = By.id("button-payment-address");
     By shippingAddressContinueBtn = By.id("button-shipping-address");
     By shippingMethodContinueBtn = By.id("button-shipping-method");
     By paymentMethodContinueBtn = By.id("button-payment-method");
     By confirmOrderBtn = By.id("button-confirm");
     By existingAddressRadio =
            By.xpath("//input[@name='payment_address' and @value='existing']");

     By agreeCheckbox = By.name("agree");

     By warningAlert = By.cssSelector(".alert-danger");

    // Helper Method
    public void type(By locator, String text) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();
        element.sendKeys(text);
    }

    // Actions
    public void clickNewAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(newAddressRadio)).click();
    }

    public void enterFirstName(String fName) {
        type(firstNameInput, fName);
    }

    public void enterLastName(String lName) {
        type(lastNameInput, lName);
    }

    public void enterAddress(String address) {
        type(addressInput, address);
    }

    public void enterCity(String city) {
        type(cityInput, city);
    }

    public void selectCountry(String country) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropdown));

        Select countrySelect = new Select(driver.findElement(countryDropdown));
        countrySelect.selectByVisibleText(country);
    }

    public void selectRegion(String region) {

        wait.until(ExpectedConditions.presenceOfElementLocated(regionDropdown));

        Select regionSelect = new Select(
                wait.until(ExpectedConditions.elementToBeClickable(regionDropdown)));

        regionSelect.selectByVisibleText(region);
    }

    public void clickPaymentAddressContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(
                paymentAddressContinueBtn)).click();
    }

    public void confirmExistingBillingAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(
                existingAddressRadio)).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                paymentAddressContinueBtn)).click();
    }

    public void confirmExistingDeliveryAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(
                shippingAddressContinueBtn)).click();
    }

    public void confirmShippingMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(
                shippingMethodContinueBtn)).click();
    }

    public void confirmPaymentMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(
                agreeCheckbox)).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                paymentMethodContinueBtn)).click();
    }

    public void clickPaymentMethodContinueWithoutAgree() {
        wait.until(ExpectedConditions.elementToBeClickable(
                paymentMethodContinueBtn)).click();
    }

    public void clickConfirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(
                confirmOrderBtn)).click();
    }

    // Assertions
    public String getSuccessMessage() {

        wait.until(ExpectedConditions.urlContains("success"));

        By successHeader = By.cssSelector("#content h1");

        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                successHeader)).getText();
    }

    public String getFieldErrorText(String fieldId) {
        By errorText = By.xpath(
                "//input[@id='" + fieldId + "']/following-sibling::div[@class='text-danger']");

        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                errorText)).getText();
    }

    public String getRegionErrorText() {

        By regionError = By.xpath(
                "//select[@id='input-payment-zone']/following-sibling::div[@class='text-danger']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                regionError)).getText();
    }

    public String getGlobalWarningText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                warningAlert)).getText();
    }
}
