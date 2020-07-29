package com.browserstack.run_local_test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalTest extends BrowserStackJUnitTest{

  @ParameterizedTest
  @MethodSource("devices")
  void testCalcOne(int taskId) throws IOException, InterruptedException {

    JSONArray envs = (JSONArray) config.get("environments");

    //Insert device capability
    Map<String, String> envCapabilities = (Map<String, String>) envs.get(taskId);
    Iterator it = envCapabilities.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
    }

    //Trigger session
    driver = new AndroidDriver(new URL("http://"+username+":"+accessKey+"@"+config.get("server")+"/wd/hub"), capabilities);

    AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.id("com.example.android.basicnetworking:id/test_action")));
    searchElement.click();
    AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.className("android.widget.TextView")));

    AndroidElement testElement = null;
    List<AndroidElement> allTextViewElements = driver.findElementsByClassName("android.widget.TextView");
    Thread.sleep(10);
    for(AndroidElement textElement : allTextViewElements) {
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
