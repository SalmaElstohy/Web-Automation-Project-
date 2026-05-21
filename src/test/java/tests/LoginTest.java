package tests;
import base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
public class LoginTest extends TestBase {
    LoginPage login;
    HomePage home;

    @BeforeMethod
    public void setupPages(){
        login = new LoginPage(driver);
        home = new HomePage(driver);
        home.navigateToMyAccountOption("Login");

    }
    @Test(priority = 1)
    public void loginWithValidCredentials(){
        login.performLogin("test283@gmail.com", "Test@123");
        Assert.assertEquals(driver.getCurrentUrl(),"https://awesomeqa.com/ui/index.php?route=account/account");
        Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(),"My Account");


    }
    @Test(priority = 2)
    public void loginWithInvalidEmail(){
        login.performLogin("test20155@gmail.com", "Test@123");
        Assert.assertTrue(login.getErrorMessageText().contains("Warning: No match")
                || login.getErrorMessageText().contains("exceeded allowed number"));
    }
    @Test(priority = 3)
    public void loginWithInvalidPassword(){
        login.performLogin("test283@gmail.com", "Test@1234abhh");
        Assert.assertTrue(login.getErrorMessageText().contains("Warning: No match")
                || login.getErrorMessageText().contains("exceeded allowed number"));
    }
    @Test(priority = 4)
    public void verifyNavigationToRegisterPage(){
        login.clickContinueAsNewCustomer();
        Assert.assertEquals(driver.getCurrentUrl(),"https://awesomeqa.com/ui/index.php?route=account/register");
        Assert.assertEquals(driver.getTitle(),"Register Account");
    }
}
