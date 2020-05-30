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

    private Locator priceSeekBar() {
        return new Locator(By.id("price_seek_bar"), "Price Seek Bar");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnPriceFilter() {
        waitUntilDisplayed(priceSeekBar(), 15);
        int X = convertToWebElement(priceSeekBar()).getLocation().getX();
        int Y = convertToWebElement(priceSeekBar()).getLocation().getY();
        int ds = convertToWebElement(priceSeekBar()).getSize().getWidth();
        int dsw = convertToWebElement(priceSeekBar()).getSize().getHeight();
        clickOnSpecificPoint((int) (X + (ds * 0.08)), Y + dsw / 2);
        allureReportAndTestNgReport("Selected Greater Than Rs 1000 Filter");
    }

    public void clickOnFourAndAboveRating() {
        bringElementIntoViewDown(fourAndAboveRating(), 5);
        click(fourAndAboveRating());
    }

    public void clickOnApplyFilter() {
        click(applyFilters());
    }
}
