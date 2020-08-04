package com.browserstack.run_first_test;

import static org.junit.Assert.*;

import io.appium.java_client.ios.IOSElement;
import org.junit.Test;

import io.appium.java_client.MobileBy;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FirstTest extends BrowserStackJUnitTest {

  @Test
  public void test() throws Exception {
    IOSElement textButton = (IOSElement) new WebDriverWait(driver, 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Button")));
    textButton.click();
    IOSElement textInput = (IOSElement) new WebDriverWait(driver, 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Input")));
    textInput.sendKeys("hello@browserstack.com" + "\n");

    Thread.sleep(5000);

    IOSElement textOutput = (IOSElement) new WebDriverWait(driver, 30).until(
          ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Output")));

    assertEquals(textOutput.getText(),"hello@browserstack.com");
  }
}
