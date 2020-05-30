package Pages;


import AppFramework.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SortAndFilterPage extends BasePage {
    public SortAndFilterPage(AndroidDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator fourAndAboveRating() {
        return new Locator(By.xpath("//*[@text='4.0 & Above']"), "4.0 & Above Rating");
    }

    private Locator applyFilters() {
        return new Locator(By.id("tv_apply"), "Apply Filter");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnFourAndAboveRating() {
        bringElementIntoViewDown(fourAndAboveRating(), 5);
        click(fourAndAboveRating());
    }

    public void clickOnApplyFilter() {
        click(applyFilters());
    }
}
