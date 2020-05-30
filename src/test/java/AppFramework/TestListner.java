package AppFramework;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListner implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        Reporter.log("*******************************************************************", true);
        Reporter.log("TEST CASE : " + iTestResult.getName().replace("_", " "), true);
        Reporter.log("*******************************************************************", true);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}

