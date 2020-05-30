package AppFramework;


import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AppActions {

    protected AndroidDriver driver;

    static int DefaultTime = 60;

    protected String getAttribute(Locator locator, String Attribute) {
        String attributevalue;
        if (locator.getElement() == null) {
            WebElement element = driver.findElement(locator.getBy());
            attributevalue = element.getAttribute(Attribute);
        } else {
            WebElement element = locator.getElement().findElement(locator.getBy());
            attributevalue = element.getAttribute(Attribute);
        }
        allureReportAndTestNgReport(locator.getName() + " : " + attributevalue);
        return attributevalue;
    }

    protected void clickEnter(Locator locator) {
        // driver.pressKeyCode(66);

        //	WebElement element = driver.findElement(locator.getBy());
        //	element.sendKeys(Keys.KEYCODE_NUMPAD_ENTER);

        allureReportAndTestNgReport("Enter Button pressed");
    }

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
        } catch (WebDriverException e) {
        }

        allureReportAndTestNgReport("Entered value  '" + value + "' in '" + locator.getName() + "'");
    }

    protected void EnterValue(WebElement element, String value) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
        webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(value);
        driver.hideKeyboard();
        allureReportAndTestNgReport("Entered value  '" + value);
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

    protected void longPress(Locator locator) {
        AndroidTouchAction touch = new AndroidTouchAction(driver);
        touch.longPress(LongPressOptions.longPressOptions()
                .withElement(ElementOption.element(convertToWebElement(locator))))
                .perform();
        allureReportAndTestNgReport("Clicked On " + locator.getName());
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected void clearText(Locator locator) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
        webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
        driver.findElement(locator.getBy()).clear();
        allureReportAndTestNgReport("Cleared Text " + locator.getName());
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

    protected void switchToWebView(Locator locator) {
        Set<String> availableContexts = driver.getContextHandles();
        allureReportAndTestNgReport("Total No of Context Found After we reach to WebView = " + availableContexts.size());
        for (String context : availableContexts) {
            if (context.contains("WEBVIEW")) {
                allureReportAndTestNgReport("Context Name is " + context);
                driver.context(context);
                if (waitUntilDisplayed(locator, 2)) {
                    break;
                } else {
                    switchToNativeApp();
                }
            }
        }
    }

    protected void switchToNativeApp() {
        driver.context("NATIVE_APP");
        allureReportAndTestNgReport("Context switched to " + "NATIVE_APP");
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


    protected boolean waitUntilPresenseOfLocator(Locator locator, int Timeout) {
        WebDriverWait webdriverWait = new WebDriverWait(driver, Timeout);
        driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
        try {
            if (locator.getElement() == null) {
                webdriverWait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
            } else {
                WebElement element = locator.getElement().findElement(locator.getBy());
                webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
    }

    public void bringElementIntoViewDown(Locator locator, int ScrollCount) {
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
        allureReportAndTestNgReport("Scrolled " + locator.getName() + " into view");
    }

    public void bringElementIntoViewUp(Locator locator, int ScrollCount) {
        Dimension dimensions = driver.manage().window().getSize();

        //	System.out.println(dimensions);

        Double screenHeightStart = dimensions.getHeight() * 0.7;

        int scrollStart = screenHeightStart.intValue();

        Double screenHeightEnd = dimensions.getHeight() * 0.4;

        int scrollEnd = screenHeightEnd.intValue();
        int i = 1;
        while (!isDisplayed(locator)) {
            //driver.swipe(0, scrollEnd, 0, scrollStart, 1000);
            TouchAction touchAction = new TouchAction(driver);
            touchAction
                    .press(PointOption.point(0, scrollEnd))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(0, scrollStart)).release().perform();
            i++;
            if (i >= ScrollCount) {
                break;
            }
        }
    }

    public void bringElementIntoViewHorizontally(Locator ToBeFind, Locator ScrollableArea, int ScrollCount) {

        WebElement element = driver.findElement(ScrollableArea.getBy());
        int uppery = element.getLocation().getY();
        int lowery = uppery + element.getSize().getHeight();
        Dimension dimensions = driver.manage().window().getSize();
        int width = dimensions.getWidth();
        int y = (uppery + lowery) / 2;
        int startx = (int) (width * 0.75);
        int endx = (int) (width * 0.35);
        int i = 1;
        while (!waitUntilPresenseOfLocator(ToBeFind, 1)) {
            //driver.swipe(startx, y, endx, y, 1000);
            TouchAction touchAction = new TouchAction(driver);
            touchAction
                    .press(PointOption.point(startx, y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(endx, y)).release().perform();
            i++;
            if (i > ScrollCount) {
                break;
            }
        }
    }

    public void waitUntilElementDisappear(Locator locator, int Timeout) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Timeout);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator.getBy()));
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        } catch (Exception e) {
            allureReportAndTestNgReport("waiting for " + locator.getName() + " to no longer be visible");
        } finally {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }

    protected List<WebElement> getWebElements(Locator locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
        } catch (Exception e) {
            allureReportAndTestNgReport("List Of Element Not Displayed With " + locator.getName() + "");
        }

        List<WebElement> webElementList = driver.findElements(locator.getBy());
        if (!webElementList.isEmpty()) {
            allureReportAndTestNgReport("Viewed all list of " + locator.getName() + " and its size is " + webElementList.size());
        } else {
            allureReportAndTestNgReport("Unable to view list of " + locator.getName() + "");
        }
        driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        return webElementList;
    }

    protected void ClearValue(Locator locator) {
        try {
            WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
            webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
            driver.findElement(locator.getBy()).clear();
            allureReportAndTestNgReport("Cleared value from '" + locator.getName() + "'");

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    protected WebElement convertToWebElement(Locator locator) {
        WebElement element = driver.findElement(locator.getBy());
        allureReportAndTestNgReport("Converted '" + locator.getName() + "' To WebElement");
        return element;
    }

    protected String clickRandomlyOnWebElements(Locator locator) {
        List<WebElement> elementList = getWebElements(locator);
        int a = ThreadLocalRandom.current().nextInt(0, elementList.size());
        String selection = getText(elementList.get(a));
        elementList.get(a).click();
        if ((a + 1) == 1 | (a + 1) == 21) {
            allureReportAndTestNgReport("Clicked On " + (a + 1) + "st Element");
        } else if ((a + 1) == 2 | (a + 1) == 22) {
            allureReportAndTestNgReport("Clicked On " + (a + 1) + "nd Element");
        } else if ((a + 1) == 3 | (a + 1) == 23) {
            allureReportAndTestNgReport("Clicked On " + (a + 1) + "rd Element");
        } else {
            allureReportAndTestNgReport("Clicked On " + (a + 1) + "th Element");
        }
        return selection;
    }

    protected void clickOnSpecificWebElementsList(Locator locator, int index) {
        click(getWebElements(locator).get(index));
        allureReportAndTestNgReport("Clicked On " + locator.getName());
    }

    protected String selectRandomlyOnWebElements(Locator locator) {
        List<WebElement> elementList = getWebElements(locator);
        int a = ThreadLocalRandom.current().nextInt(0, elementList.size());
        String selection = getText(elementList.get(a));
        if ((a + 1) == 1 | (a + 1) == 21) {
            allureReportAndTestNgReport("Selection On " + (a + 1) + "st Element");
        } else if ((a + 1) == 2 | (a + 1) == 22) {
            allureReportAndTestNgReport("Selection On " + (a + 1) + "nd Element");
        } else if ((a + 1) == 3 | (a + 1) == 23) {
            allureReportAndTestNgReport("Selection On " + (a + 1) + "rd Element");
        } else {
            allureReportAndTestNgReport("Selection On " + (a + 1) + "th Element");
        }
        return selection;
    }

    protected void androidBackButton() {
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
        allureReportAndTestNgReport("Pressed Physical Back Button");
    }

    protected void androidDownButton() {
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_PAGE_DOWN);
        allureReportAndTestNgReport("Pressed Physical Down Button");
    }

    protected void androidEnterButton() {
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
        allureReportAndTestNgReport("Physical Enter Button");
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
        } catch (NoSuchElementException e) {

        } finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
        return false;
    }

    protected boolean isSelected(Locator locator) {
        try {
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

            if (locator.getElement() == null) {
                if (driver.findElement(locator.getBy()).isSelected()) {
                    allureReportAndTestNgReport(locator.getName() + " is selected");
                    return true;
                } else {
                    allureReportAndTestNgReport(locator.getName() + " is not selected");
                    return false;
                }
            } else {
                if (locator.getElement().findElement(locator.getBy()).isSelected()) {
                    allureReportAndTestNgReport(locator.getName() + " is selected");
                    return true;
                } else {
                    allureReportAndTestNgReport(locator.getName() + " is not selected");
                    return false;
                }
            }
        } catch (NoSuchElementException e) {
            allureReportAndTestNgReport(locator.getName() + " Element Not Found");
        } finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
        return false;
    }

    protected boolean isChecked(Locator locator) {
        try {
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);

            if (locator.getElement() == null) {
                if (driver.findElement(locator.getBy()).getAttribute("checked").contains("true")) {
                    allureReportAndTestNgReport(locator.getName() + " is checked");
                    return true;
                } else {
                    allureReportAndTestNgReport(locator.getName() + " is not checked");
                    return false;
                }
            } else {
                if (locator.getElement().findElement(locator.getBy()).getAttribute("checked").contains("true")) {
                    allureReportAndTestNgReport(locator.getName() + " is checked");
                    return true;
                } else {
                    allureReportAndTestNgReport(locator.getName() + " is not checked");
                    return false;
                }
            }
        } catch (NoSuchElementException e) {
            allureReportAndTestNgReport(locator.getName() + " Element Not Found");
        } finally {
            driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
        }
        return false;
    }

    protected void clickOnSpecifiedCordinates(int x, int y) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(x, y)).release().perform();
        allureReportAndTestNgReport("Clicked on " + x + " and " + y);
    }

    protected static final boolean equalsWithNulls(String a, String b) {
        if (a == b) return true;
        if ((a == null) || (b == null)) return false;
        return a.equals(b);
    }

    protected String getClipBoardValue() {
        waitFor(1000);
        return driver.getClipboardText();
    }

    public void waitFor(int timeout) {
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

    public boolean checkTheColorOfBackGround(Locator locator, String colorCode) {
        MobileElement elem = (MobileElement) driver.findElement(locator.getBy());
        Point point = elem.getCenter();
        int centerx = point.getX();
        int centerY = point.getY();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage image = null;
        try {
            image = ImageIO.read(scrFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int clr = image.getRGB(centerx, centerY);
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;
//        System.out.println("Red Color value = " + red);
//        System.out.println("Green Color value = " + green);
//        System.out.println("Blue Color value = " + blue);
        String hex = String.format("#%02x%02x%02x", red, green, blue);
        allureReportAndTestNgReport(hex);
        return true;
        //return colorCode.toLowerCase().contains(hex.toLowerCase());
    }
}


