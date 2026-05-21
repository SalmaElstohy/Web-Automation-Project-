package tests;
import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ForgotPassPage;
import pages.HomePage;
import pages.LoginPage;

public class ForgotPassTest extends TestBase {
    LoginPage login;
    ForgotPassPage pass;
    HomePage home;

    @BeforeMethod
    public void SetupForgotPass(){
        login = new LoginPage(driver);
        pass = new ForgotPassPage(driver);
        home = new HomePage(driver);
    }
    @Test
    public void RegisteredMail(){
        home.navigateToMyAccountOption("Login");
        login.NavigatetoForgotPass();
        pass.EnterMail("test283@gmail.com");
        pass.ClickContinue();
        Assert.assertTrue(pass.SuccessMessage().contains("An email with a confirmation link has been sent"));
    }
    @Test
    public void UNRegisteredMail() {
        home.navigateToMyAccountOption("Login");
        login.NavigatetoForgotPass();
        pass.EnterMail("test2883@gmail.com");
        pass.ClickContinue();
        Assert.assertTrue(pass.ErrorMessage().contains("The E-Mail Address was not found"));

    }
    @Test
    public void LeaveEmpty() {
        home.navigateToMyAccountOption("Login");
        login.NavigatetoForgotPass();
        pass.ClickContinue();
        Assert.assertTrue(pass.ErrorMessage().contains("The E-Mail Address was not found"));

    }
}
