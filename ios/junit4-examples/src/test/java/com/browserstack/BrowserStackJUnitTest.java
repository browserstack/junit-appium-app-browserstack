package com.browserstack;

import java.net.URL;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.junit.After;
import org.junit.Before;

public class BrowserStackJUnitTest {
    public IOSDriver driver;

    @Before
    public void setUp() throws Exception {
        XCUITestOptions options = new XCUITestOptions();
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
    }

    @After
    public void tearDown() throws Exception {
        // Invoke driver.quit() to indicate that the test is completed.
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
    }
}
