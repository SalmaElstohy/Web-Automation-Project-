package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductDetailPage;
import pages.HomePage;

public class ProductDetailTest extends TestBase {
    ProductDetailPage pdp;
    CartPage cart;
    HomePage home;

    @BeforeMethod
    public void SetPDP(){
        pdp = new ProductDetailPage(driver);
        cart = new CartPage(driver);
        home = new HomePage((driver));
        loginToApp();

    }

    // Validate adding the product to the shopping cart from the Product Display page
    @Test
    public void addtoCartfromPDP(){
        pdp.GoToPDP();
        pdp.AddToCartPDP();
        home.navigateToCart();
        Assert.assertTrue(cart.Cartproduct().contains("MacBook"));
    }
}
