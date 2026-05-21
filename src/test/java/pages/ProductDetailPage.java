package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage {
    WebDriver driver;
    WebDriverWait wait;


    public ProductDetailPage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }
    public void GoToPDP(){
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[2]/h4/a"))).click();
    }
    public void AddToCartPDP(){
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("button-cart"))).click();
    }



}
