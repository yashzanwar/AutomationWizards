package Pages;

import AppFramework.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashSet;
import java.util.List;

public class HotelListingPage extends BasePage {
    public HotelListingPage(AndroidDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator sortFilterButton() {
        return new Locator(By.id("sort_filter"), "Sort Filter");
    }

    private Locator hotelName() {
        return new Locator(By.id("tvHotelName"), "Hotel Name");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnSortAndFilterButton() {
        click(sortFilterButton());
    }

    public String clickOnFifthHotel() {
        String hotelName = null;
        LinkedHashSet<String> hotels = new LinkedHashSet<>();
        while (hotels.size() < 5) {
            List<WebElement> elements = getWebElements(hotelName());
            for (WebElement element : elements) {
                hotels.add(getText(element));
                if (hotels.size() == 5) {
                    hotelName = getText(element);
                    int height = getHeightOfWindow();
                    if (element.getLocation().getY() > (height * 0.75)) swipeDown();
                    click(element);
                    allureReportAndTestNgReport("Clicked On Hotel");
                    break;
                }
            }
            if (hotels.size() < 5) swipeDown();
        }
        return hotelName;
    }
}
