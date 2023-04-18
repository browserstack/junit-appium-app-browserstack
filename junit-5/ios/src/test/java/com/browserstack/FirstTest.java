package com.browserstack;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTest extends BrowserStackJUnitTest{

  @Test
  void testCalcOne() throws IOException, InterruptedException {
    WebElement textButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Button")));
    textButton.click();
    WebElement textInput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Input")));
    textInput.sendKeys("hello@browserstack.com"+"\n");

    Thread.sleep(5000);

    WebElement textOutput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Output")));

    assertEquals(textOutput.getText(),"hello@browserstack.com");
  }
}
