package Utilis;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }


    public static String get(String key) {
        return props.getProperty(key);
    }

    public static int getInt(String key, int defaultVal) {
        try {
            return Integer.parseInt(get(key));
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static boolean getBoolean(String key, boolean defaultVal) {
        try {
            return Boolean.parseBoolean(get(key));
        } catch (Exception e) {
            return defaultVal;
        }
    }
}