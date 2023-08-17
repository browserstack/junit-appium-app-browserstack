package com.browserstack;

import com.browserstack.local.Local;

import java.net.URL;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.junit.runners.Parameterized;

import org.openqa.selenium.WebElement;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BrowserStackJUnitTest {
    public AndroidDriver driver;
    private Local local;

    private static JSONObject config;

    @Parameter(value = 0)
    public int taskID;

    @Parameters
    public static Iterable<? extends Object> data() throws Exception {
        List<Integer> taskIDs = new ArrayList<Integer>();

        if (System.getProperty("config") != null) {
            JSONParser parser = new JSONParser();
            config = (JSONObject) parser.parse(new FileReader("src/test/resources/com/browserstack/" + System.getProperty("config")));
            int envs = ((JSONArray) config.get("environments")).size();

            for (int i = 0; i < envs; i++) {
                taskIDs.add(i);
            }
        }
        return taskIDs;
    }

    @Before
    public void setUp() throws Exception {
        JSONArray envs = (JSONArray) config.get("environments");

        UiAutomator2Options options = new UiAutomator2Options();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(taskID);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            options.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (options.getCapability(pair.getKey().toString()) == null) {
                options.setCapability(pair.getKey().toString(), pair.getValue());
            }else if (pair.getKey().toString().equalsIgnoreCase("bstack:options")){
                HashMap bstackOptionsMap = (HashMap) pair.getValue();
                bstackOptionsMap.putAll((HashMap) options.getCapability("bstack:options"));
                options.setCapability(pair.getKey().toString(), bstackOptionsMap);
            }
        }

        JSONObject browserstackOptions = (JSONObject) options.getCapability("bstack:options");

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = (String) browserstackOptions.get("userName");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) browserstackOptions.get("accessKey");
        }

        String app = System.getenv("BROWSERSTACK_APP_ID");
        if (app != null && !app.isEmpty()) {
            options.setCapability("app", app);
        }

        if (browserstackOptions.get("local") != null && browserstackOptions.get("local").toString() == "true") {
            local = new Local();
            Map<String, String> LocalOptions = new HashMap<String, String>();
            LocalOptions.put("key", accessKey);
            local.start(LocalOptions);
        }

        driver = new AndroidDriver(new URL("http://"+config.get("server")+"/wd/hub"), options);
    }

    @After
    public void tearDown() throws Exception {
        // Invoke driver.quit() to indicate that the test is completed. 
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
        if (local != null) local.stop();
    }
}
