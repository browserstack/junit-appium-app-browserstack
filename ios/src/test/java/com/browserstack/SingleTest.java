package com.browserstack;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.List;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SingleTest extends BrowserStackJUnitTest {

  @Test
  public void test() throws Exception {
    IOSElement loginButton = (IOSElement) new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Log In")));
    loginButton.click();
    IOSElement emailTextField = (IOSElement) new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Email address")));
    emailTextField.sendKeys("hello@browserstack.com");

    driver.findElementByAccessibilityId("Next").click();      
    Thread.sleep(5000);

    List<IOSElement> textElements = driver.findElementsByXPath("//XCUIElementTypeStaticText");
    assertTrue(textElements.size() > 0);
    String matchedString = "";
    for(IOSElement textElement : textElements) {
      String textContent = textElement.getText();
      if(textContent != null && textContent.contains("not registered")) {
        matchedString = textContent;
      }
    }

    System.out.println(matchedString);
    assertTrue(matchedString.contains("not registered on WordPress.com"));
  }
}
