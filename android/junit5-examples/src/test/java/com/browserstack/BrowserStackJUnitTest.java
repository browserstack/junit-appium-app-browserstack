package com.browserstack;

import com.browserstack.local.Local;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

@Execution(ExecutionMode.CONCURRENT)
public class BrowserStackJUnitTest {

    public AndroidDriver driver;
    private Local local;
    public String username;
    public String accessKey;
    public UiAutomator2Options options;

    public static JSONObject config;

    private static Stream<Integer> devices() throws IOException, ParseException {
        List<Integer> taskIDs = new ArrayList<Integer>();

        if (System.getProperty("config") != null) {
            JSONParser parser = new JSONParser();
            config = (JSONObject) parser.parse(new FileReader("src/test/resources/com/browserstack/" + System.getProperty("config")));
            int envs = ((JSONArray) config.get("environments")).size();

            for (int i = 0; i < envs; i++) {
                taskIDs.add(i);
            }
        }

        return taskIDs.stream();
    }

    public void createConnection(int taskId) throws MalformedURLException {
        JSONArray envs = (JSONArray) config.get("environments");

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(taskId);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            options.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        driver = new AndroidDriver(new URL("http://"+config.get("server")+"/wd/hub"), options);
    }

    @BeforeEach
    public void setup() throws Exception {
        options = new UiAutomator2Options();

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        Iterator it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(options.getCapability(pair.getKey().toString()) == null){
                options.setCapability(pair.getKey().toString(), pair.getValue());
            }else if (pair.getKey().toString().equalsIgnoreCase("bstack:options")){
                HashMap bstackOptionsMap = (HashMap) pair.getValue();
                bstackOptionsMap.putAll((HashMap) options.getCapability("bstack:options"));
                options.setCapability(pair.getKey().toString(), bstackOptionsMap);
            }
        }

        JSONObject browserstackOptions = (JSONObject) options.getCapability("bstack:options");

        username = System.getenv("BROWSERSTACK_USERNAME");
        if(username == null) {
            username = (String) browserstackOptions.get("userName");
        }

        accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if(accessKey == null) {
            accessKey = (String) browserstackOptions.get("accessKey");
        }

        String app = System.getenv("BROWSERSTACK_APP_ID");
        if(app != null && !app.isEmpty()) {
            options.setCapability("app", app);
        }

        if(browserstackOptions.get("local") != null && browserstackOptions.get("local").toString() == "true"){
            local = new Local();
            Map<String, String> LocalOptions = new HashMap<String, String>();
            LocalOptions.put("key", accessKey);
            local.start(LocalOptions);
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Invoke driver.quit() to indicate that the test is completed. 
        // Otherwise, it will appear as timed out on BrowserStack.
        driver.quit();
        if(local != null) local.stop();
    }
}
