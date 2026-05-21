package tests;
import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;

public class CheckoutTest extends TestBase {

    CheckoutPage checkout;
    HomePage home;
    CartPage cart;

    @BeforeMethod
    public void navigateToCheckout() {
        checkout = new CheckoutPage(driver);
        home = new HomePage(driver);
        cart = new CartPage(driver);
        loginToApp();
        home.addProductToCart();
        home.clickMonitorsFromComponents();
        home.addSamsungMonitorToCart();
        home.navigateToCart();
        cart.navigateToCheckout();
    }

    @Test(priority = 1)
    public void testSuccessfulCheckoutFlowForLoggedInUser() {
        checkout.confirmExistingBillingAddress();
        checkout.confirmExistingDeliveryAddress();
        checkout.confirmShippingMethod();
        checkout.confirmPaymentMethod();
        checkout.clickConfirmOrder();
        Assert.assertEquals(
                checkout.getSuccessMessage(),
                "Your order has been placed!"
        );
    }

    @Test(priority = 2)
    public void testNewAddressValidationFirstNameRequired() {
        checkout.clickNewAddress();
        checkout.enterFirstName("");
        checkout.enterLastName("Mostafa");
        checkout.enterAddress("123 Street");
        checkout.enterCity("Mansoura");
        checkout.selectCountry("Egypt");
        checkout.selectRegion("Ad Daqahliyah");
        checkout.clickPaymentAddressContinue();
        String nameError =
                checkout.getFieldErrorText("input-payment-firstname");
        Assert.assertTrue(
                nameError.contains("First Name must be")
        );
    }

    @Test(priority = 3)
    public void testWarningWhenMissingTermsAndConditions() {
        checkout.confirmExistingBillingAddress();
        checkout.confirmExistingDeliveryAddress();
        checkout.confirmShippingMethod();
        checkout.clickPaymentMethodContinueWithoutAgree();
        String globalWarning = checkout.getGlobalWarningText();
        Assert.assertTrue(
                globalWarning.contains("Warning: You must agree to the")
        );
    }

    @Test(priority = 4)
    public void testNewAddressValidationRegionRequired() {
        checkout.clickNewAddress();
        checkout.enterFirstName("Salma");
        checkout.enterLastName("Mostafa");
        checkout.enterAddress("123 Street");
        checkout.enterCity("Mansoura");
        checkout.selectCountry("Egypt");
        checkout.clickPaymentAddressContinue();
        Assert.assertEquals(
                checkout.getRegionErrorText(),
                "Please select a region / state!"
        );
    }
}