package TestSuite;


import AppFramework.RetryAnalyzer;
import Pages.*;
import org.testng.annotations.Test;

public class EndToEndTestCases extends BaseTestSuite {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Test_End_To_End_Flow() {
        LoginPage loginPage = new LoginPage(driver);
        //loginPage.doLogin("automationwizards123@gmail.com", "Phonepe@123");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnRespectiveTab("Hotels");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.searchACityAndSelectIt("Goa");
        String checkIn = hotelSearchPage.clickOnCheckIn();
        String checkOut = hotelSearchPage.clickOnCheckOut();
        String totalGuestCount = hotelSearchPage.enterAdultsAndChildrenCountInEachRoom(2, 2, 2);
        hotelSearchPage.clickOnSearchButton();
        HotelListingPage hotelListingPage = new HotelListingPage(driver);
        hotelListingPage.clickOnSortAndFilterButton();
        SortAndFilterPage sortAndFilterPage = new SortAndFilterPage(driver);
        sortAndFilterPage.clickOnFourAndAboveRating();
        sortAndFilterPage.clickOnApplyFilter();
        String hotelName = hotelListingPage.clickOnFifthHotel();
        HotelDetailPage hotelDetailPage = new HotelDetailPage(driver);
        hotelDetailPage.clickOnSelectRoomButton();
        hotelDetailPage.clickOnContinueButton();
        BookingSummaryPage bookingSummaryPage = new BookingSummaryPage(driver);
        customSoftAssert.assertEquals(bookingSummaryPage.getHotelName(), hotelName);
        customSoftAssert.assertEquals(bookingSummaryPage.getCityName(), "Goa");
        customSoftAssert.assertTrue(bookingSummaryPage.getRoomCount().contains("2"));
        customSoftAssert.assertTrue(bookingSummaryPage.getNumberOfGuests().contains(String.valueOf(Integer.parseInt(totalGuestCount))));
        customSoftAssert.assertTrue(checkIn.contains(bookingSummaryPage.getCheckInDate()));
        customSoftAssert.assertTrue(checkOut.contains(bookingSummaryPage.getCheckOutDate()));
    }
}
