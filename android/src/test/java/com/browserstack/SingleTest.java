package com.browserstack;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SingleTest extends BrowserStackJUnitTest {

  @Test
  public void test() throws Exception {
    WebElement searchElement = new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(By.id("Search Wikipedia")));
    searchElement.click();
    WebElement insertTextElement = new WebDriverWait(driver, 30).until(
        ExpectedConditions.elementToBeClickable(By.id("org.wikipedia.alpha:id/search_src_text")));
    insertTextElement.sendKeys("BrowserStack");
    Thread.sleep(5000);

    List<WebElement> allProductsName = driver.findElements(By.className("android.widget.TextView"));
    assertTrue(allProductsName.size() > 0);
  }
}
