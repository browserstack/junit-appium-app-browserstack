package com.browserstack;

import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.After;
import org.junit.Before;

public class BrowserStackJUnitTest {
    public AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
    }

    @After
    public void tearDown() throws Exception {
        // Invoke driver.quit() to indicate that the test is completed. 
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
    }
}
