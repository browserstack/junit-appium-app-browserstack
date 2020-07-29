import io.appium.java_client.android.AndroidDriver;
import org.json.simple.JSONArray;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class SingleTest extends  AppTest{

    @ParameterizedTest
    @MethodSource("devices")
    void testCalcOne(int taskId) throws IOException {

        JSONArray envs = (JSONArray) config.get("environments");

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(taskId);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        driver = new AndroidDriver(new URL("http://"+username+":"+accessKey+"@"+config.get("server")+"/wd/hub"), capabilities);

        System.out.println("Hi From testCalcOne");
        String sessionId = driver.getSessionId().toString();

        System.out.println("Session Id: " + sessionId);

    }
}
