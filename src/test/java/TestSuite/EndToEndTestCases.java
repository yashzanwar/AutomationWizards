package TestSuite;


import AppFramework.RetryAnalyzer;
import Pages.HomePage;
import Pages.HotelListingPage;
import Pages.HotelSearchPage;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class EndToEndTestCases extends BaseTestSuite {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Test_End_To_End_Flow() {
        LoginPage loginPage = new LoginPage(driver);
        //loginPage.doLogin("automationwizards123@gmail.com", "Phonepe@123");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnRespectiveTab("Hotels");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.searchACityAndSelectIt("Nagpur");
        hotelSearchPage.clickOnCheckIn();
        hotelSearchPage.clickOnCheckOut();
        hotelSearchPage.enterAdultsAndChildrenCountInEachRoom(2, 2, 2);
        hotelSearchPage.clickOnSearchButton();
        HotelListingPage hotelListingPage = new HotelListingPage(driver);
        hotelListingPage.clickOnSortAndFilterButton();
    }
}
