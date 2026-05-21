package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    WebDriver driver;
    WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    public void enterFirstname(String firstname){
        driver.findElement(By.id("input-firstname")).sendKeys(firstname);
    }

    public void enterLastname(String lastname){
        driver.findElement(By.id("input-lastname")).sendKeys(lastname);
    }

    public void enterEmail(String mail){
        driver.findElement(By.id("input-email")).sendKeys(mail);
    }

    public void enterPhone(String phone){
        driver.findElement(By.id("input-telephone")).sendKeys(phone);
    }

    public void enterPassword(String password){
        driver.findElement(By.id("input-password")).sendKeys(password);
    }

    public void confirmPassword(String confirmedPassword){
        driver.findElement(By.id("input-confirm")).sendKeys(confirmedPassword);
    }

    public void checkPrivacyPolicy() {
        driver.findElement(By.name("agree")).click();
    }

    public void clickContinueButton() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]")).click();
    }

    public String alertDuplicateEmail() {
        By alertLocator = By.cssSelector(".alert-danger");
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertLocator));
        return driver.findElement(alertLocator).getText().trim();
    }

    public String getSuccessMessage() {
        // إنشاء انتظار محلي للتأكد من تحميل صفحة النجاح تماماً
        WebDriverWait localWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        By successHeader = By.cssSelector("#content h1");
        localWait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(successHeader));

        return driver.findElement(successHeader).getText().trim();
    }

    public String getPrivacyPolicyWarningText() {
        WebDriverWait localWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        By warningLocator = By.cssSelector(".alert-danger");
        localWait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(warningLocator));
        return driver.findElement(warningLocator).getText().trim();
    }

    public String getFirstNameErrorText() {
        return driver.findElement(By.cssSelector("#input-firstname + .text-danger")).getText().trim();
    }

    public String getLastNameErrorText() {
        return driver.findElement(By.cssSelector("#input-lastname + .text-danger")).getText().trim();
    }

    // جلب رسالة الخطأ الخاصة بحقل البريد الإلكتروني
    public String getEmailErrorText() {
        return driver.findElement(By.cssSelector("#input-email + .text-danger")).getText().trim();
    }

    // جلب رسالة الخطأ الخاصة بحقل الهاتف
    public String getTelephoneErrorText() {
        return driver.findElement(By.cssSelector("#input-telephone + .text-danger")).getText().trim();
    }

    public String getPasswordErrorText() {
        return driver.findElement(By.cssSelector("#input-password + .text-danger")).getText().trim();
    }

    public String getPasswordConfirmErrorText() {
        return driver.findElement(By.cssSelector("#input-confirm + .text-danger")).getText().trim();
    }

    public String getEmailBrowserValidationMessage() {
        org.openqa.selenium.WebElement emailField = driver.findElement(By.id("input-email"));
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", emailField);
    }
}