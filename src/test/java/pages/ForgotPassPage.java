package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPassPage {
    WebDriver driver;
    WebDriverWait wait;

    public ForgotPassPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }
    public void EnterMail(String mail){
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("input-email"))).sendKeys(mail);
    }
    public void ClickContinue(){
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"content\"]/form/div/div[2]/input"))).click();
    }
    // Assertion methods
    public String SuccessMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"account-login\"]/div[1]"))).getText();
    }
    public String ErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"account-forgotten\"]/div[1]"))).getText();
    }
}
