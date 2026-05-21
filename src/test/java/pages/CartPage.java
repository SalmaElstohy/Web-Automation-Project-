package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    // Locators
    By quantityInput =
            By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[4]/div/input");

    By updateButton =
            By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[4]/div/span/button[1]");

    By continueButton =
            By.xpath("//*[@id=\"content\"]/div[3]/div[1]/a");

    By productName =
            By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[2]/a");

    By productPrice =
            By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[5]");

    By emptyCartMessage =
            By.xpath("//*[@id='content']/p");

    By checkoutButton =
            By.xpath("//*[@id='content']/div[3]/div[2]/a");

    // Actions
    public void updateQuantity(String number) {
        WebElement qty = wait.until(
                ExpectedConditions.visibilityOfElementLocated(quantityInput));
        qty.clear();
        qty.sendKeys(number);
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }

    public void navigateToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void navigateToHomeFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    // Assertions
    public boolean isProductDisplayed() {
        return !driver.findElements(productName).isEmpty();
    }

    public String getProductPrice() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(productPrice)
        ).getText();
    }

    public String getQuantityValue() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(quantityInput)
        ).getAttribute("value");
    }

    public String Cartproduct() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"checkout-cart\"]"))).getText();
    }
}