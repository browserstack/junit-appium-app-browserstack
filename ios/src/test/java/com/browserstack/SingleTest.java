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

    // element.sendKeys() method is not supported in Appium 1.6.3
    // Workaround for sendKeys() method:
    emailTextField.click();
    String email = "hello@browserstack.com";
    for (int i = 0; i < email.length(); i++) {
      driver.findElementByXPath("//XCUIElementTypeKey[@name='" + email.charAt(i) + "']").click();
    }

    driver.findElementByAccessibilityId("Next").click();      
    Thread.sleep(5000);

    List<IOSElement> textElements = driver.findElementsByXPath("//XCUIElementTypeStaticText");
    assertTrue(textElements.size() > 0);
    String matchedString = "";
    for(IOSElement textElement : textElements) {
      String textContent = textElement.getText();
      if(textContent.contains("not registered")) {
        matchedString = textContent;
      }
    }

    System.out.println(matchedString);
    assertTrue(matchedString.contains("not registered on WordPress.com"));
  }
}
