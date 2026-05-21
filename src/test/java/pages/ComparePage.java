package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ComparePage {
    WebDriver driver;
    WebDriverWait wait;

    public ComparePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void AddtoCompare() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"content\"]/div[3]/div[5]/div/div[2]/div[2]/button[3]")
        )).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"content\"]/div[3]/div[6]/div/div[2]/div[2]/button[3]")
        )).click();
    }

    public void GotoComparePage() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"compare-total\"]")
        )).click();
    }

    // Assertion methods
    public String Compareproduct1(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"content\"]/table/tbody[1]/tr[1]/td[2]")
        )).getText();
    }

    public String Compareproduct2() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"content\"]/table/tbody[1]/tr[1]/td[3]")
        )).getText();
    }
}