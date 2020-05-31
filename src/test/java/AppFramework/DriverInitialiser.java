package AppFramework;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverInitialiser {

    private String newCommandTimeout;
    private String automationName;
    private String appPackage;
    private String appActivity;
    private String deviceName;
    private String appiumUrl;

    public void loadConfigProp() throws IOException {
        FileReader reader = new FileReader("src/test/resources/capabilities.properties");
        Properties p = new Properties();
        p.load(reader);
        newCommandTimeout = p.getProperty("newCommandTimeout");
        automationName = p.getProperty("automationName");
        appPackage = p.getProperty("appPackage");
        appActivity = p.getProperty("appActivity");
        deviceName = System.getProperty("deviceName");
        appiumUrl = System.getProperty("appiumUrl");
    }


    public AndroidDriver getAndroidDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, deviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, newCommandTimeout);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, String.valueOf(true));
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
        AndroidDriver driver = new AndroidDriver<>(new URL(appiumUrl), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] captureScreenShot(WebDriver browser) {
        return ((TakesScreenshot) browser).getScreenshotAs(OutputType.BYTES);
    }
}
