package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.WishlistPage;
import pages.HomePage;



public class WishlistTest extends TestBase {
    WishlistPage wishlist;
    HomePage home;


    @BeforeMethod
    public void setWishlist() {
        wishlist = new WishlistPage(driver);
        home = new HomePage(driver);
        loginToApp();
    }


    //Verify adding products to the wishlist after logging in
    @Test
    public void AddtoWishlist() {
        wishlist.AddproducttowishList();
        home.navigateToWishlist();
        Assert.assertEquals(
                wishlist.WishlistProductadded().contains("Canon EOS 5D"),
                true
        );
    }

    // Verify removing products from the wishlist
    @Test
    public void RemovefromWishList() {
        wishlist.AddproducttowishList();
        home.navigateToWishlist();
        wishlist.WishListAddedProductsRemove();
        Assert.assertEquals(
                wishlist.WishlistProductadded().contains("Canon EOS 5D"),
                false
        );

    }
}
