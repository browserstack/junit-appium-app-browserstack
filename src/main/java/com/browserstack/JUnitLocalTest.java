package com.browserstack;

import static org.junit.Assert.*;
import com.browserstack.local.Local;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class JUnitLocalTest {
  private static WebDriver driver;
  private Local bsLocal;

  @Before
  public void setUp() throws Exception {
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

    bsLocal = new Local();
    HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
    bsLocalArgs.put("key", accessKey);
    bsLocalArgs.put("forcelocal", "");
    bsLocal.start(bsLocalArgs);

    DesiredCapabilities capability = DesiredCapabilities.chrome();
    capability.setCapability("os", "OS X");
    capability.setCapability("os_version", "El Capitan");
    capability.setCapability("browser", "firefox");
    capability.setCapability("build", "Sample JUnit Tests");
    capability.setCapability("name", "Sample JUnit Local Tests");
    capability.setCapability("browserstack.local", "true");

    driver = new RemoteWebDriver(
      new URL("http://"+username+":"+accessKey+"@hub.browserstack.com/wd/hub"),
      capability
      );
  }

  @Test
  public void testSimple() throws Exception {
    driver.get("http://www.google.com");
    System.out.println("Page title is: " + driver.getTitle());
    assertEquals("Google", driver.getTitle());
    WebElement element = driver.findElement(By.name("q"));
    element.sendKeys("Browser Stack");
    element.submit();
  }

  @After
  public void tearDown() throws Exception {
    if(bsLocal != null) {
      bsLocal.stop();
    }
    driver.quit();
  }
}
