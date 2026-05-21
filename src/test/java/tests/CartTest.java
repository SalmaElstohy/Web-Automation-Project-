package tests;
import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;

public class CartTest extends TestBase {

    CartPage cart;

    @BeforeMethod
    public void setUpCart() {
        cart = new CartPage(driver);
        loginToApp();
    }

    @Test(priority = 1)
    public void userCanAddProductToCart() {
        home.addProductToCart();
        home.navigateToCart();
        Assert.assertTrue(cart.isProductDisplayed());
    }

    @Test(priority = 2)
    public void userCanUpdateProductQuantity() {
        home.addProductToCart();
        home.navigateToCart();
        cart.updateQuantity("2");
        Assert.assertEquals(cart.getQuantityValue(), "2");
    }


    @Test(priority = 3)
    public void userCanContinueShoppingToHome() {
        home.navigateToCart();
        cart.navigateToHomeFromCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("route=common/home"));
        Assert.assertTrue(home.getBanner().isDisplayed());

    }

    @Test(priority = 4)
    public void productPriceIsDisplayedCorrectly() {
        home.addProductToCart();
        home.navigateToCart();
        Assert.assertFalse(cart.getProductPrice().isEmpty());
    }
}