package com.browserstack;

import io.appium.java_client.AppiumBy;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalTest extends BrowserStackJUnitTest{

  @Test
  void test() throws IOException, InterruptedException {
    WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.id("com.example.android.basicnetworking:id/test_action")));
    searchElement.click();
    WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.TextView")));

    WebElement testElement = null;
    List<WebElement> allTextViewElements = driver.findElements(AppiumBy.className("android.widget.TextView"));
    Thread.sleep(10);
    for(WebElement textElement : allTextViewElements) {
      if(textElement.getText().contains("The active connection is")) {
        testElement = textElement;
      }
    }

    if(testElement == null) {
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "screenshot.png"));
      System.out.println("Screenshot stored at " + System.getProperty("user.dir") + "screenshot.png");
      throw new Error("Cannot find the needed TextView element from app");
    }
    String matchedString = testElement.getText();
    System.out.println(matchedString);
    assertTrue(matchedString.contains("The active connection is wifi"));
    assertTrue(matchedString.contains("Up and running"));
  }
}
