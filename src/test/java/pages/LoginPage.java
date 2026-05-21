package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public  LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void  enterEmail (String mail){
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("input-email"))).sendKeys(mail);
    }

    public void  enterPassword (String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("input-password"))).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input"))).click();
    }

    public void performLogin(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
    public void NavigatetoForgotPass(){
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/div[2]/a"))).click();
    }
    public void clickContinueAsNewCustomer() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"))).click();
    }
    //Assertion Method
    public String getErrorMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"account-login\"]/div[1]"))).getText();
    }
}
