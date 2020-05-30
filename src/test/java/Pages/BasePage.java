package Pages;

import AppFramework.AppActions;
import AppFramework.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

class BasePage extends AppActions {

    BasePage(AndroidDriver driver) {
        this.driver = driver;
    }

    /************************************************************************** Methods ********************************************************************************/

    void swipeDown() {
        bringElementIntoViewDown(new Locator(By.id("dummy"), ""), 1);
    }
}
