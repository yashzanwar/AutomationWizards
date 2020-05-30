package Pages;

import AppFramework.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HotelListingPage extends BasePage {
    public HotelListingPage(AndroidDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    Locator sortFilterButton() {
        return new Locator(By.id("sort_filter"), "Sort Filter");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnSortAndFilterButton() {
        click(sortFilterButton());
    }
}
