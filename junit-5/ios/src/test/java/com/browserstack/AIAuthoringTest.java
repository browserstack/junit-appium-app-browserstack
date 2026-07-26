package com.browserstack;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

public class AIAuthoringTest extends BrowserStackJUnitTest {

    @Test
    void testWithAIAgent() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Search using AI Agent commands
        js.executeScript("browserstack_executor: {\"action\": \"ai\", \"arguments\": [\"Tap on Search Wikipedia\"]}");
        js.executeScript("browserstack_executor: {\"action\": \"ai\", \"arguments\": [\"Type India in the search field\"]}");

        // Verify results
        js.executeScript("browserstack_executor: {\"action\": \"ai\", \"arguments\": [\"Verify search results are displayed\"]}");
    }
}
