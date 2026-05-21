package tests;
import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;

public class RegisterTest extends TestBase {
    RegisterPage register;
    HomePage home;

    @BeforeMethod
    public void openBrowser(){
        register = new RegisterPage(driver);
        home =new HomePage(driver);
        home.navigateToMyAccountOption("Register");
    }

    @Test (priority = 1)
    public void verifySuccessfulRegistration(){
        register.enterFirstname("Salma");
        register.enterLastname("Tester");
        register.enterEmail("salmatester" + System.currentTimeMillis() + "@gmail.com");
        register.enterPhone("01204868811");
        register.enterPassword("Test@123");
        register.confirmPassword("Test@123");
        register.checkPrivacyPolicy();
        register.clickContinueButton();

        String expectedHeader = "Your Account Has Been Created!";
        String actualHeader = register.getSuccessMessage();

        Assert.assertEquals(actualHeader, expectedHeader, "فشل التسجيل: لم تظهر صفحة النجاح أو العنوان غير متطابق!");
    }

    @Test (priority = 2)
    public void testRegistrationWithDuplicateMail(){
        register.enterFirstname("Ahmed");
        register.enterLastname("Ali");
        register.enterEmail("salmatester28332222@gmail.com");
        register.enterPhone("010225599");
        register.enterPassword("Ahmed@!123");
        register.confirmPassword("Ahmed@!123");
        register.checkPrivacyPolicy();
        register.clickContinueButton();

        String actualAlertText = register.alertDuplicateEmail();

        System.out.println("الرسالة التي ظهرت في الموقع هي: [" + actualAlertText + "]");

        Assert.assertTrue(actualAlertText.contains("Warning: E-Mail Address is already registered!"));
    }

    @Test (priority = 3)
    public void verifyErrorWhenAllFieldsAreEmpty(){
        register.clickContinueButton();

        String privacyWarning = register.getPrivacyPolicyWarningText();
        Assert.assertTrue(privacyWarning.contains("Warning: You must agree to the Privacy Policy!"),
                "خطأ: رسالة تحذير السياسة العامة لم تظهر أو غير صحيحة!");

        Assert.assertEquals(register.getFirstNameErrorText(), "First Name must be between 1 and 32 characters!",
                "خطأ في رسالة حقل الـ First Name!");

        Assert.assertEquals(register.getLastNameErrorText(), "Last Name must be between 1 and 32 characters!",
                "خطأ في رسالة حقل الـ Last Name!");

        Assert.assertEquals(register.getEmailErrorText(), "E-Mail Address does not appear to be valid!",
                "خطأ في رسالة حقل الـ E-Mail!");

        Assert.assertEquals(register.getTelephoneErrorText(), "Telephone must be between 3 and 32 characters!",
                "خطأ في رسالة حقل الـ Telephone!");

        Assert.assertEquals(register.getPasswordErrorText(), "Password must be between 4 and 20 characters!",
                "خطأ في رسالة حقل الـ Password!");
    }


    @Test (priority = 4)
    public void testRegistrationWithMismatchedPasswords(){
        register.enterFirstname("Ahmed");
        register.enterLastname("Ali");
        register.enterEmail("test" + System.currentTimeMillis() + "@gmail.com");

        register.enterPhone("01011223344");
        register.enterPassword("Ahmed@!1234");
        register.confirmPassword("Ahmed");
        register.checkPrivacyPolicy();
        register.clickContinueButton();
        String expectedError = "Password confirmation does not match password!";
        String actualError = register.getPasswordConfirmErrorText();

        Assert.assertEquals(actualError, expectedError, "خطأ: رسالة عدم تطابق كلمة المرور لم تظهر أو نصها غير صحيح!");
    }

    @Test (priority = 5)
    public void testRegistrationWithWrongEmailFormat(){
        register.enterFirstname("Ahmed");
        register.enterLastname("Ali");
        register.enterEmail("test11.com"); // صيغة خاطئة بدون @
        register.enterPhone("01033669988");
        register.enterPassword("Ahmed@!1234");
        register.confirmPassword("Ahmed@!1234");
        register.checkPrivacyPolicy();
        register.clickContinueButton();

        String browserMessage = register.getEmailBrowserValidationMessage();
        Assert.assertTrue(browserMessage.contains("@") || browserMessage.contains("email"),
                "خطأ: المتصفح لم يمنع الإرسال ولم تظهر رسالة التحقق الخاصة بصيغة الإيميل! الرسالة الحالية: " + browserMessage);
    }
}