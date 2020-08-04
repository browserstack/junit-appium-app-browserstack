package com.browserstack.run_first_test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTest extends BrowserStackJUnitTest{

  @ParameterizedTest
  @MethodSource("devices")
  void testCalcOne(int taskId) throws IOException, InterruptedException {

    createConnection(taskId);

    IOSElement textButton = (IOSElement) new WebDriverWait(driver, 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Button")));
    textButton.click();
    IOSElement textInput = (IOSElement) new WebDriverWait(driver, 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Input")));
    textInput.sendKeys("hello@browserstack.com"+"\n");

    Thread.sleep(5000);

    IOSElement textOutput = (IOSElement) new WebDriverWait(driver, 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Output")));

    assertEquals(textOutput.getText(),"hello@browserstack.com");
  }
}
