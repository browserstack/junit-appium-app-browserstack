package com.browserstack.run_local_test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalTest extends BrowserStackJUnitTest{

  @ParameterizedTest
  @MethodSource("devices")
  void testCalcOne(int taskId) throws IOException {

    createConnection(taskId);

    IOSElement testButton = (IOSElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("TestBrowserStackLocal")));
    testButton.click();

    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver d) {
        String result = d.findElement(MobileBy.AccessibilityId("ResultBrowserStackLocal")).getAttribute("value");
        return result != null && result.length() > 0;
      }
    });
    IOSElement resultElement = (IOSElement) driver.findElement(MobileBy.AccessibilityId("ResultBrowserStackLocal"));

    String resultString = resultElement.getText().toLowerCase();
    System.out.println(resultString);
    if(resultString.contains("not working")) {
      File scrFile = (File) ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/screenshot.png"));
      System.out.println("Screenshot stored at " + System.getProperty("user.dir") + "/screenshot.png");
      throw new Error("Unexpected BrowserStackLocal test result");
    }

    String expectedString = "Up and running";
    assertTrue(resultString.contains(expectedString.toLowerCase()));
  }
}
