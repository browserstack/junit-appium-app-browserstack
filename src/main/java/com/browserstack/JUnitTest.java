package com.browserstack;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class JUnitTest {  
  private static WebDriver driver;  

  @Before
  public void setUp() throws Exception {  
    DesiredCapabilities capability = DesiredCapabilities.chrome();
    capability.setCapability("os", "OS X");
    capability.setCapability("os_version", "El Capitan");
    capability.setCapability("browser", "firefox");
    capability.setCapability("build", "Sample JUnit Tests");

    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

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
    driver.quit();  
  }  
}
