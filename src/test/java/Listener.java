
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
public class Listener implements ITestListener {
        @Override
        public void onTestStart(ITestResult result) {
            log.info("Test Started");
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            TakesScreenshot ts = (TakesScreenshot) Methods.driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            try{
                FileHandler.copy(source, new File("Screenshots\\"+result.getName()+".png"));
                log.info("Screenshot taken on Successful run of "+ result.getName());
            }
            catch (Exception e)
            {
                log.warn("Exception "+e.getMessage());
            }
        }

        @Override
        public void onTestFailure(ITestResult result) {
            TakesScreenshot ts = (TakesScreenshot) Methods.driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            try{
                FileHandler.copy(source, new File("Screenshots\\"+result.getName()+".png"));
                log.error("Screenshot taken on failure of "+result.getName());
            }
            catch (Exception e)
            {
                log.warn("Exception caught while taking screenshot "+e.getMessage());
            }
        }

        @Override
        public void onTestSkipped(ITestResult result) {
            ITestListener.super.onTestSkipped(result);
        }

        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
            ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        }

        @Override
        public void onTestFailedWithTimeout(ITestResult result) {
            ITestListener.super.onTestFailedWithTimeout(result);
        }

        @Override
        public void onStart(ITestContext context) {
            ITestListener.super.onStart(context);
        }

        @Override
        public void onFinish(ITestContext context) {

        }
    }

