package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void navigateToHomePage(){
        driver.findElement(By.className("img-responsive")).click();
    }
    public void navigateToCart(){
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[4]/a/i")).click();
    }
    public void navigateToWishlist(){
        driver.findElement(By.id("wishlist-total")).click();
    }
    public void navigateToMyAccountOption(String optionName){
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]")).click();
        driver.findElement(By.linkText(optionName)).click();
    }
    public void addProductToCart(){
        driver.findElement(By.xpath("(//button[contains(@onclick,'cart.add')])[1]")).click();
    }
    public void chooseDollar(){
        driver.findElement(By.cssSelector("#form-currency .dropdown-toggle")).click();
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='USD']"))).click();
    }

    public void choosePound(){
        driver.findElement(By.cssSelector("#form-currency .dropdown-toggle")).click();
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='GBP']"))).click();
    }

    // مثال للتأكيد داخل HomePage لضمان استقرار العملات عند التشغيل المتتالي
    public void chooseEuro(){
        driver.findElement(By.cssSelector("#form-currency .dropdown-toggle")).click();
        // انتظار ظهور الخيار قبل النقر عليه لضمان عدم حدوث تداخل سريع
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='EUR']"))).click();
    }

    public String getCurrentCurrencySymbol(){
        return driver.findElement(By.cssSelector("#form-currency strong")).getText();
    }

    // ميثود بسيطة تجلب نص سعر أول منتج في الصفحة (مثل MacBook)
    public String getFirstProductPriceText() {
        // باستخدام Xpath يحدد أول عنصر سعر فقط في المنتجات
        return driver.findElement(By.xpath("(//p[@class='price'])[1]")).getText();
    }

    public void clickMonitorsFromComponents() {
        driver.findElement(By.xpath("//a[text()='Components']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Monitors')]")).click();
    }

    public void addSamsungMonitorToCart() {
        driver.findElement(By.xpath("//h4[a[contains(text(),'Samsung')]]/ancestor::div[@class='product-thumb']//button[contains(.,'Add to Cart')]")).click();
    }

    //Assertions
    public String getSuccessMessage(){
        return driver.findElement(By.cssSelector(".alert-success")).getText();
    }
    By banner = By.id("slideshow0");

    public WebElement getBanner() {
        return driver.findElement(By.id("slideshow0"));
    }

}