package com.browserstack;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

/**
 * AI Authoring Test - Cross Device Automation Agent (CDAA)
 *
 * This test demonstrates how to use BrowserStack's AI Agent to write
 * test automation using natural language commands instead of traditional
 * element locators (xpath, id, accessibility id, etc.).
 *
 * Benefits:
 * - No need to find and maintain complex locator strategies
 * - Tests are resilient to minor UI changes
 * - Just describe what you see on the screen and AI executes it
 */
public class AIAuthoringTest extends BrowserStackJUnitTest {

    @Test
    void testWithAIAgent() throws Exception {
        // Cast the driver to JavascriptExecutor to execute BrowserStack AI commands
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // --- AI Agent Command ---
        // Instead of: driver.findElement(By.accessibilityId("Search Wikipedia")).click();
        // Simply describe the action in natural language:
        js.executeScript("browserstack_executor: {\"action\": \"ai\", \"arguments\": [\"Tap on Search Wikipedia\"]}");

        // --- AI Agent Command ---
        // Instead of: driver.findElement(By.id("search_src_text")).sendKeys("India");
        // Simply describe what you want to type and where:
        js.executeScript("browserstack_executor: {\"action\": \"ai\", \"arguments\": [\"Type India in the search field\"]}");

        // --- AI Agent Command ---
        // Instead of writing complex assertions with element locators,
        // simply describe what you expect to see on screen:
        js.executeScript("browserstack_executor: {\"action\": \"ai\", \"arguments\": [\"Verify search results are displayed\"]}");
    }
}
