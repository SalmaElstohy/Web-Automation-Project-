package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ComparePage;
import pages.SearchResultPage;

public class CompareTest extends TestBase {
    ComparePage compare;
    SearchResultPage search;

    @BeforeMethod
    public void SetCompare() {
        compare = new ComparePage(driver);
        search = new SearchResultPage(driver);
        loginToApp();

    }

//    Validate adding the product for comparison from Grid View of Search Results page
    @Test
    public void compareTwoProducts(){
        search.SearchProduct("c");
        compare.AddtoCompare();
        compare.GotoComparePage();
        Assert.assertEquals(compare.Compareproduct1(), "iPod Classic");
        Assert.assertEquals(compare.Compareproduct2(), "iPod Touch");
    }
}