import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(
                "[START] " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(
                "[PASS] " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable error = result.getThrowable();

        System.out.println(
                "[FAIL] " + result.getName()
                        + " | Exception: "
                        + (error == null ? "Unknown" : error.getMessage()));
    }
}
