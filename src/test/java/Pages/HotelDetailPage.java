package Pages;

import AppFramework.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HotelDetailPage extends BasePage {
    public HotelDetailPage(AndroidDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator selectRoomButton() {
        return new Locator(By.xpath("//*[contains(@resource-id,'btnShowAllHotels')][@text='SELECT ROOM']"), "Select Room Button");
    }

    private Locator continueButton() {
        return new Locator(By.xpath("//*[contains(@resource-id,'btnShowAllHotels')][@text='CONTINUE']"), "Continue Button");
    }

    private Locator roomTypeName() {
        return new Locator(By.id("roomTypeName"), "Room Type");
    }

    private Locator bedType() {
        return new Locator(By.id("bedType"), "Bed Type");
    }

    private Locator sizeOfRoom() {
        return new Locator(By.id("tvSize"), "Size Of The Room");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnSelectRoomButton() {
        waitUntilDisplayed(selectRoomButton(), 10);
        waitFor(5000);
        click(selectRoomButton());
        if (!waitUntilDisplayed(continueButton(),3)) click(selectRoomButton());
    }

    public void clickOnContinueButton() {
        click(continueButton());
    }

    public void getRoomDetails() {
        bringElementIntoViewDown(roomTypeName(), 2);
        getText(roomTypeName());
        bringElementIntoViewDown(bedType(), 1);
        if (isDisplayed(bedType())) getText(bedType());
        if (isDisplayed(sizeOfRoom())) getText(sizeOfRoom());
    }
}
