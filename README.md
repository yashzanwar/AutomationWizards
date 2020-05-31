# AutomationWizards (Headspin Hackhathon)
An automation hackhathon to automate end to end flow of Makemytrip App using Appium, TestNg.

## Pre Assuming The Conditions:
1. The Playstore version App of Makemytrip is already installed on the device.
2. `MAVEN_PATH` and `JAVA_HOME` needs to be set up.

## Steps To Run the test cases:
1. **Clone The project** - `https://github.com/yashzanwar/AutomationWizards.git`
2. Take the deviceName and WebDriver URL from headspin Account.
3. Run command - `mvn clean test -DdeviceName={deviceName} -DappiumURL={webdriverUrl}`

## Steps to check allure report:
1. **Install allure** - `brew install allure`
2. Run allure service - `allure serve allure-results`
