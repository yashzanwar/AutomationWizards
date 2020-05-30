package Pages;

import AppFramework.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * created by yash.zanwar on 2020-05-30
 */
public class HomePage extends BasePage {
    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator hotelsTab(String tabs) {
        return new Locator(By.xpath("//*[@text='"+tabs+"'][contains(@resource-id,'title')]"), tabs + " Title");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnRespectiveTab(String tabName) {
        click(hotelsTab(tabName));
    }
}
