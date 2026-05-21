package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.HomePage;
import pages.LoginPage;

import java.lang.reflect.Method;
import java.time.Duration;

public class TestBase {

    protected WebDriver driver;
    protected HomePage home;
    protected LoginPage login;

    // Extent Reports static variables shared across all test classes
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public void setUpReport() {
        // 1. Initialize the report path and look & feel once before any test runs
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("OpenCart Regression Suite");
        sparkReporter.config().setReportName("Regression Results");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "OpenCart");
    }

    @BeforeMethod
    public void setUp(Method method) {
        // 2. Automatically create a test entry in the report using the current method's name
        test = extent.createTest(method.getName());

        driver = new ChromeDriver();
        home = new HomePage(driver);
        login = new LoginPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
    }

    public void loginToApp() {
        home.navigateToMyAccountOption("Login");
        login.performLogin("sdfd@yahoo.com", "qqq@12345");
        home.navigateToHomePage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // 3. Log the Pass/Fail/Skip status dynamically into the report
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test FAILED: " + result.getName());
            test.fail(result.getThrowable()); // Logs the exception/error details
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test PASSED: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test SKIPPED: " + result.getName());
        }

        // 4. Close the browser safely
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownReport() {
        // 5. Write all test logs to the HTML file after the entire suite finishes
        if (extent != null) {
            extent.flush();
        }
    }
}