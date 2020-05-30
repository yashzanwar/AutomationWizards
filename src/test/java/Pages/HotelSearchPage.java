package Pages;

import AppFramework.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HotelSearchPage extends BasePage {
    public HotelSearchPage(AndroidDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator cityText() {
        return new Locator(By.id("city"), "City Text");
    }

    private Locator searchBar() {
        return new Locator(By.xpath("//android.widget.EditText[@text='Enter City/Area/Hotel Name']"), "Search bar");
    }

    private Locator searchResult() {
        return new Locator(By.xpath("//*[contains(@resource-id,'rv_autosearch_locus')]/android.widget.RelativeLayout"), "Search Result");
    }

    private Locator checkInDate() {
        return new Locator(By.id("checkInDate"), "Check-In Date");
    }

    private Locator checkInMonthYear() {
        return new Locator(By.id("checkInMonthYear"), "Check-In Month");
    }

    private Locator checkOutDate() {
        return new Locator(By.id("checkOutDate"), "Check-Out Date");
    }

    private Locator checkOutMonthYear() {
        return new Locator(By.id("checkOutMonthYear"), "Check-Out Month");
    }

    private Locator doneButton() {
        return new Locator(By.id("rlDone"), "Done Button");
    }

    private Locator guestCount() {
        return new Locator(By.id("guest_count"), "Guests Count");
    }

    private Locator minusAdultButton() {
        return new Locator(By.id("ivadultsubtract"), "Minus Adult Button");
    }

    private Locator plusAdultButton() {
        return new Locator(By.id("ivadultadd"), "Plus Adult Button");
    }

    private Locator plusChildButton() {
        return new Locator(By.id("ivchildadd"), "PLus Child Button");
    }

    private Locator guestDoneButton() {
        return new Locator(By.id("btn_done"), "Done Button");
    }

    private Locator addRoomButton() {
        return new Locator(By.id("btn_add_room"), "Add Room Button");
    }

    private Locator searchButton() {
        return new Locator(By.id("search_button"), "Search Button");
    }

    private Locator businessTypeTrip() {
        return new Locator(By.id("rb_travel_type_business"), "Business Type Trip");
    }

    private Locator adultCount() {
        return new Locator(By.id("tvadultcount"), "Adult Count");
    }

    private Locator childCount() {
        return new Locator(By.id("tvchildcount"), "Child Count");
    }


    /***************************************************************** Methods *******************************************************************/

    private void clickOnCity() {
        click(cityText());
    }

    public void searchACityAndSelectIt(String cityName) {
        clickOnCity();
        EnterValue(searchBar(), cityName);
        click(searchResult());
    }

    public String clickOnCheckIn() {
        click(checkInDate());
        click(doneButton());
        return getText(checkInDate()) + " " + getText(checkInMonthYear());
    }

    public String clickOnCheckOut() {
        click(checkOutDate());
        click(doneButton());
        return getText(checkOutDate()) + " " + getText(checkOutMonthYear());
    }

    public String enterAdultsAndChildrenCountInEachRoom(int adults, int children, int rooms) {
        click(guestCount());
        for (int k = 0; k < rooms; k++) {
            int existingAdultCount = Integer.parseInt(getText(adultCount()));
            for (int i = 0; i < adults - existingAdultCount; i++) click(plusAdultButton());
            int existingChildCount = Integer.parseInt(getText(childCount()));
            for (int j = 0; j < children - existingChildCount; j++) click(plusChildButton());
            if (k != rooms - 1) click(addRoomButton());
        }
        click(guestDoneButton());
        return getText(guestCount());
    }

    public void clickOnSearchButton() {
        click(searchButton());
    }

    public void clickOnBusinessTypeTrip() {
        click(businessTypeTrip());
    }


}
