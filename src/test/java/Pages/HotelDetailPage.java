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
        return new Locator(By.id("btnShowAllHotels"), "Select Room Button");
    }

    private Locator continueButton() {
        return new Locator(By.id("btnShowAllHotels"), "Continue Button");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnSelectRoomButton() {
        click(selectRoomButton());
    }

    public void clickOnContinueButton() {
        click(continueButton());
    }
}
