package AppFramework;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;

public class DriverInitialiser {

    public AndroidDriver getAndroidDriver(AppiumDriverLocalService appiumDriverLocalService, String DeviceName) {
        String appiumServiceUrl = appiumDriverLocalService.getUrl().toString();
        System.out.println("Appium Service Address ************************: - " + appiumServiceUrl);
        System.out.println("Appium Service Device *************************: - " + DeviceName);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, DeviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "30000");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        //desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, String.valueOf(true));
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.makemytrip");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.mmt.travel.app.home.ui.SplashActivity");
        //desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.mmt.travel.app.common.login.ui.LoginActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        AndroidDriver<AndroidElement> driver = null;
        try {
            driver = new AndroidDriver<>(new URL(appiumServiceUrl), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Reporter.log("Android Driver Created", true);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    public AppiumDriverLocalService getAppiumDriverLocalService() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort().withArgument(LOG_LEVEL, "error");
        AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        //AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildDefaultService();
        appiumService.start();
        Reporter.log("Appium Server Started", true);
        return appiumService;

    }


    public String getAttachedDevices() {

        List DeviceList = new ArrayList<String>();
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            Pattern pattern = Pattern.compile("^([a-zA-Z0-9:.\\-]+)(\\s+)(device)");
            Matcher matcher = null;
            while ((line = in.readLine()) != null) {
                if (line.matches(pattern.pattern())) {
                    matcher = pattern.matcher(line);
                    if (matcher.find())
                        System.out.println(matcher.group(1));
                    DeviceList.add(matcher.group(1));
                }
            }
            return matcher.group(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] captureScreenShot(WebDriver browser) {
        return ((TakesScreenshot) browser).getScreenshotAs(OutputType.BYTES);
    }
}
