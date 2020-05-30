package Pages;

import AppFramework.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HotelSearchPage extends BasePage {
    public HotelSearchPage(AndroidDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    Locator cityText() {
        return new Locator(By.id("city"), "City Text");
    }

    Locator searchBar() {
        return new Locator(By.xpath("//android.widget.EditText[@text='Enter City/Area/Hotel Name']"), "Search bar");
    }

    Locator searchResult() {
        return new Locator(By.xpath("//*[contains(@resource-id,'rv_autosearch_locus')]/android.widget.RelativeLayout"), "Search Result");
    }

    Locator checkInText() {
        return new Locator(By.id("checkin"), "Check - In Text");
    }

    Locator checkOutText() {
        return new Locator(By.id("checkout_header"), "Check - Out Header");
    }

    Locator doneButton() {
        return new Locator(By.id("rlDone"), "Done Button");
    }

    Locator guestsText() {
        return new Locator(By.id("adult_count_header"), "Guests Header");
    }

    Locator roomsText() {
        return new Locator(By.id("room_count_header"), "Rooms Header");
    }

    Locator minusAdultButton() {
        return new Locator(By.id("ivadultsubtract"), "Minus Adult Button");
    }

    Locator plusAdultButton() {
        return new Locator(By.id("ivadultadd"), "Plus Adult Button");
    }

    Locator minusChildButton() {
        return new Locator(By.id("ivchildsubtract"), "Minus Child Button");
    }

    Locator plusChildButton() {
        return new Locator(By.id("ivchildadd"), "PLus Child Button");
    }

    Locator guestDoneButton() {
        return new Locator(By.id("btn_done"), "Done Button");
    }

    Locator addRoomButton() {
        return new Locator(By.id("btn_add_room"), "Add Room Button");
    }

    Locator searchButton() {
        return new Locator(By.id("search_button"), "Search Button");
    }


    /***************************************************************** Methods *******************************************************************/

    public void clickOnCity() {
        click(cityText());
    }

    public void searchACityAndSelectIt(String cityName) {
        clickOnCity();
        EnterValue(searchBar(), cityName);
        click(searchResult());
    }

    public void clickOnCheckIn() {
        click(checkInText());
        click(doneButton());
    }

    public void clickOnCheckOut() {
        click(checkOutText());
        click(doneButton());
    }

    public void enterAdultsAndChildrenCountInEachRoom(int adults, int children, int rooms) {
        click(guestsText());
        for (int k = 0; k < rooms; k++) {
            if (adults == 1) click(minusAdultButton());
            for (int i = 0; i < adults - 2; i++) click(plusAdultButton());
            for (int j = 0; j < children; j++) plusChildButton();
            if (k != rooms - 1) click(addRoomButton());
        }
        click(guestDoneButton());
    }

    public void clickOnSearchButton() {
        click(searchButton());
    }


}
