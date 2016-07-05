package com.browserstack;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LocalTest extends BrowserStackJUnitTest {

  @Test
  public void test() throws Exception {
    driver.get("http://bs-local.com:45691/check");

    assertTrue(driver.getPageSource().contains("Up and running"));
  }
}
