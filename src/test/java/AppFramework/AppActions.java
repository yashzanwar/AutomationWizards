package AppFramework;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppActions {

    protected AndroidDriver driver;

    private static int DefaultTime = 60;

    protected void EnterValue(Locator locator, String value) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
        webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
        if (locator.getElement() == null) {
            driver.findElement(locator.getBy()).sendKeys(value);
        } else {
            locator.getElement().findElement(locator.getBy()).sendKeys(value);
        }
        try {
            driver.hideKeyboard();
        } catch (WebDriverException ignored) {
        }

        allureReportAndTestNgReport("Entered value  '" + value + "' in '" + locator.getName() + "'");
    }

    protected void click(Locator locator) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
        if (locator.getElement() == null) {
            webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
            driver.findElement(locator.getBy()).click();
        } else {
            WebElement element = locator.getElement().findElement(locator.getBy());
            webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
            element.click();
        }
        allureReportAndTestNgReport("Clicked On " + locator.getName());
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected String getText(Locator locator) {
        String Text = "No data";
        try {
            WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
            if (locator.getElement() == null) {
                WebElement webElement = driver.findElement(locator.getBy());
                Text = webElement.getText();
            } else {
                WebElement webElement = locator.getElement().findElement(locator.getBy());
                Text = webElement.getText();
            }
            allureReportAndTestNgReport(locator.getName() + " : " + Text);
            return Text;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Text;
    }

    protected String getText(WebElement element) {
        String Text = "No data";
        try {
            WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            Text = element.getText();
            return Text;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Text;
    }

    protected boolean waitUntilDisplayed(Locator locator, int Timeout) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, Timeout);
        driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
        try {
            if (locator.getElement() == null) {
                webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
            } else {
                WebElement element = locator.getElement().findElement(locator.getBy());
                webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
            }
            allureReportAndTestNgReport(locator.getName() + " is visible");
            return true;

        } catch (Exception e) {
            allureReportAndTestNgReport(locator.getName() + " is not visible");
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
    }

    protected int getHeightOfWindow() {
        return driver.manage().window().getSize().getHeight();
    }

    protected void bringElementIntoViewDown(Locator locator, int ScrollCount) {
        waitFor(2000);
        Dimension dimensions = driver.manage().window().getSize();

        //System.out.println(dimensions);

        Double screenHeightStart = dimensions.getHeight() * 0.8;

        int scrollStart = screenHeightStart.intValue();

        Double screenHeightEnd = dimensions.getHeight() * 0.4;

        int scrollEnd = screenHeightEnd.intValue();
        int i = 1;
        while (!isDisplayed(locator)) {
            TouchAction touchAction = new TouchAction(driver);
            touchAction
                    .press(PointOption.point(0, scrollStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(0, scrollEnd)).release().perform();
            i++;
            if (i >= ScrollCount) {
                break;
            }
        }
        if (!locator.getName().equals(""))
            allureReportAndTestNgReport("Scrolled " + locator.getName() + " into view");
    }

    protected List getWebElements(Locator locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
        } catch (Exception e) {
            //allureReportAndTestNgReport("List Of Element Not Displayed With " + locator.getName() + "");
        }

        List webElementList = driver.findElements(locator.getBy());
        if (webElementList.isEmpty()) {
            allureReportAndTestNgReport("Unable to view list of " + locator.getName() + "");
        }
        driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        return webElementList;
    }

    protected WebElement convertToWebElement(Locator locator) {
        return driver.findElement(locator.getBy());
    }


    protected boolean isDisplayed(Locator locator) {
        try {
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

            if (locator.getElement() == null) {
                if (driver.findElement(locator.getBy()).isDisplayed()) {
                    allureReportAndTestNgReport(locator.getName() + " is visible");
                    return true;
                } else {
                    allureReportAndTestNgReport(locator.getName() + " is not visible");
                    return false;
                }
            } else {
                if (locator.getElement().findElement(locator.getBy()).isDisplayed()) {
                    allureReportAndTestNgReport(locator.getName() + " is visible");
                    return true;
                } else {
                    allureReportAndTestNgReport(locator.getName() + " is not visible");
                    return false;
                }
            }
        } catch (NoSuchElementException ignored) {

        } finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
        return false;
    }


    protected void waitFor(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void clickOnSpecificPoint(int x, int y) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(x, y)).release().perform();
        Reporter.log("Clicked on " + x + " and " + y, true);
    }

    protected void clickAtCordinatesOfLocator(Locator locator) {
        int x = convertToWebElement(locator).getLocation().getX();
        int y = convertToWebElement(locator).getLocation().getY();
        clickOnSpecificPoint(x, y);
        Reporter.log("Clicked On " + locator.getName() + "", true);
    }

    @Step("{0}")
    public void step(String stepName) {
    }

    public void allureReportAndTestNgReport(String stepName) {
        step(stepName);
        Reporter.log(stepName, true);
    }

}


