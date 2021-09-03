package util;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class ConnectionNetWork {

    private ConnectionNetWork() {
    }

    public static void disConnectInternet(WebDriver driver) throws IOException {
        Map map = new HashMap();
        map.put("offline", true);
        map.put("latency", 5);
        map.put("download_throughput", 500);
        map.put("upload_throughput", 1024);
        CommandExecutor executor = ((ChromeDriver) driver).getCommandExecutor();
        executor
                .execute(new Command(((ChromeDriver) driver)
                        .getSessionId(), "setNetworkConditions", ImmutableMap
                        .of("network_conditions", ImmutableMap.copyOf(map))));
    }
}
