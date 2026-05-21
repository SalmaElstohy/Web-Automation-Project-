package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends TestBase {

    @Test(priority = 1)
    public void testChangeCurrencyToDollar() {
        home.choosePound();
        home.chooseDollar();
        Assert.assertEquals(home.getCurrentCurrencySymbol(), "$");
        String firstProductPrice = home.getFirstProductPriceText();
        Assert.assertTrue(firstProductPrice.contains("$"),
                "السعر لا يحتوي على علامة الدولار! السعر الحالي هو: " + firstProductPrice);
    }

    @Test(priority = 2)
    public void testChangeCurrencyToPound() {
        home.chooseDollar();
        home.choosePound();
        Assert.assertEquals(home.getCurrentCurrencySymbol(), "£");
        String firstProductPrice = home.getFirstProductPriceText();
        Assert.assertTrue(firstProductPrice.contains("£"),
                "السعر لا يحتوي على علامة الإسترليني! السعر الحالي هو: " + firstProductPrice);
    }

    @Test(priority = 3)
    public void testChangeCurrencyToEuro() {
        home.choosePound();
        home.chooseEuro();
        Assert.assertEquals(home.getCurrentCurrencySymbol(), "€");
        String firstProductPrice = home.getFirstProductPriceText();
        Assert.assertTrue(firstProductPrice.contains("€"),
                "السعر لا يحتوي على علامة اليورو! السعر الحالي هو: " + firstProductPrice);
    }

    @Test(priority = 4)
    public void testNavigateToMonitorsByClicks() {
        home.clickMonitorsFromComponents();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("path=25_28"), "فشل الانتقال إلى صفحة Monitors!");
    }

}