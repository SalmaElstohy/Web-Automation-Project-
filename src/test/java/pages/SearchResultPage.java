package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchResultPage {
    WebDriver driver;
    WebDriverWait wait;


    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void SearchProduct(String search) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"search\"]/input"))).sendKeys(search);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"search\"]/span/button"))).click();
    }

    public void SearchCriteriadescriptin() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("description"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("button-search"))).click();

    }

    public void SortSearch(String option) {
        Select sortDropdown = new Select(wait.until(ExpectedConditions.elementToBeClickable(
                By.id("input-sort"))));
        sortDropdown.selectByVisibleText(option);
    }

    //Assertion methods
    public String SearchedProducts() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a"))).getText();
    }

    public String NofoundProducts() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"content\"]/p[2]"))).getText();
    }

    public String SearchedProductsbyDescreption() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[1]/h4/a"))).getText();
    }
    public String getFirstProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[1]/h4/a"))).getText();
    }

    public String getLastProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"content\"]/div[3]/div[2]/div/div[2]/div[1]/h4/a"))).getText();
    }

}
