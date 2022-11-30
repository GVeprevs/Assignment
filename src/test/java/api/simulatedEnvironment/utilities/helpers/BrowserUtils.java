package api.simulatedEnvironment.utilities.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class BrowserUtils {

  public static WebElement waitForVisibility(WebElement element, Duration timeToWaitInSec)
      throws MalformedURLException {
    WebDriverWait wait = new WebDriverWait(Driver.get(), timeToWaitInSec);
    return wait.until(ExpectedConditions.visibilityOf(element));
  }

  public static WebElement waitForClickablility(WebElement element, Duration timeout)
      throws MalformedURLException {
    WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);
    return wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  public static void waitForPageToLoad(Duration timeOutInSeconds) {
    ExpectedCondition<Boolean> expectation =
        new ExpectedCondition<Boolean>() {
          public Boolean apply(WebDriver driver) {
            return ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete");
          }
        };
    try {
      WebDriverWait wait = new WebDriverWait(Driver.get(), timeOutInSeconds);
      wait.until(expectation);
    } catch (Throwable error) {
      error.printStackTrace();
    }
  }
}
