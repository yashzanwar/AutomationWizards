package Pages;


import AppFramework.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator emailTextBox() {
        return new Locator(By.xpath("//android.widget.EditText[@text='Enter Mobile No./Email']"), "E-Mail Text Box");
    }

    private Locator continueButton() {
        return new Locator(By.id("btn_submit"), "Continue Button");
    }

    private Locator submitButton() {
        return new Locator(By.id("btn_continue"), "Submit Button");
    }

    private Locator loginWithPasswordButton() {
        return new Locator(By.id("pwd_option"), "Login With Password Button");
    }

    private Locator passwrodBox() {
        return new Locator(By.xpath("//android.widget.EditText[@text='Enter Password']"), "Password Text Box");
    }

    /***************************************************************** Methods *****************************************************************/

    public void doLogin(String emailId, String password) {
        EnterValue(emailTextBox(), emailId);
        click(continueButton());
        click(loginWithPasswordButton());
        EnterValue(passwrodBox(), password);
        click(submitButton());
    }
}
