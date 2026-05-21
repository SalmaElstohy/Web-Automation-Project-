package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchResultPage;

public class SearchResultTest extends TestBase {

    SearchResultPage search;

    @BeforeMethod
    public void searchSetUp() {
        search = new SearchResultPage(driver);
        loginToApp();

    }


    // Verify searching with existing product name after logging in
    @Test
    public void verifySearchWithexistingProduct() {

        search.SearchProduct("iPhone");
        Assert.assertEquals(search.SearchedProducts(),"iPhone");
    }

    // Verify searching with nonexisting product name after logging in
    @Test
    public void verifySearchWithnonexistingProduct() {

        search.SearchProduct("TV");
        Assert.assertEquals(search.NofoundProducts(),"There is no product that matches the search criteria.");
    }

    // Verify searching using product's description when marking the product description option
    @Test
    public void verifySearchWithProductsdescription() {

        search.SearchProduct("Intel Core 2 Duo processor");
        search.SearchCriteriadescriptin();
        Assert.assertEquals(search.SearchedProductsbyDescreption(),"iMac");
    }

    // Verify user is able to sort the search results from the sort functionality
    @Test
    public void verifySortSearchResults() {

        search.SearchProduct("samsung");
        search.SortSearch("Name (A - Z)");
        Assert.assertEquals(search.getFirstProductName(),"Samsung Galaxy Tab 10.1");
        Assert.assertEquals(search.getLastProductName(),"Samsung SyncMaster 941BW");

    }

}

