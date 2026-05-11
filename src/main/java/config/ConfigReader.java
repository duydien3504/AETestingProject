package config;

import org.opentest4j.FileInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static Properties initProp() {
        properties = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/main/resources/config.properties");
            properties.load(ip);
        } catch (IOException e) {
            System.out.println("Khong tim thay file cau hinh");
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperty(String key) {
        if(properties == null) {
            initProp();
        }
        return properties.getProperty(key);
    }

    public static String getBrowser() {
        return getProperty("BROWSER");
    }

    public static String getBaseUrl() {
        return getProperty("BASE_URL");
    }

    public static String getHeadLess() {
        return getProperty("UI_Headless");
    }
}
