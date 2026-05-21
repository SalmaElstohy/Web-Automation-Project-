package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WishlistPage {
    WebDriver driver;
    WebDriverWait wait;


    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    public void AddproducttowishList() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div/div[3]/button[2]"))).click();
    }



  public void WishListAddedProductsRemove() {
      wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/a"))).click();
    }


    //  Assertion methods

    public String WishlistProductadded(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("body"))).getText();
   }
}
