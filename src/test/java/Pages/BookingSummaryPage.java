package Pages;

import AppFramework.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class BookingSummaryPage extends BasePage {
    public BookingSummaryPage(AndroidDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator hotelName() {
        return new Locator(By.id("tvHotelName"), "Hotel Name");
    }

    private Locator cityName() {
        return new Locator(By.id("tvCityName"), "City Name");
    }

    private Locator roomsCount() {
        return new Locator(By.id("tvReviewRoomText"), "Rooms");
    }

    private Locator numberOfGuest() {
        return new Locator(By.id("tvReviewGuestText"), "No. Of Guest");
    }

    private Locator checkInDate() {
        return new Locator(By.id("tvReviewCheckInDate"), "Check In Date");
    }

    private Locator checkOutDate() {
        return new Locator(By.id("tvReviewCheckoutDate"), "Check Out Date");
    }

    /***************************************************************** Methods *******************************************************************/

    public String getHotelName() {
        return getText(hotelName());
    }

    public String getCityName() {
        return getText(cityName()).replaceAll(",", "");
    }

    public String getRoomCount() {
        return getText(roomsCount());
    }

    public String getNumberOfGuests() {
        return getText(numberOfGuest());
    }

    public String getCheckInDate() {
        return getText(checkInDate()).split(",")[0];
    }

    public String getCheckOutDate() {
        return getText(checkOutDate()).split(",")[0];
    }
}
