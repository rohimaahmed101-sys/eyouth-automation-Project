package com.eyouth.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads configuration values from config.properties.
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_PATH =
            "src/test/resources/config.properties";

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value.trim();
    }

    public static String getBaseUrl()           { return get("base.url"); }
    public static String getValidUsername()     { return get("valid.username"); }
    public static String getValidPassword()     { return get("valid.password"); }
    public static String getInvalidUsername()   { return get("invalid.username"); }
    public static String getInvalidPassword()   { return get("invalid.password"); }
    public static String getSearchKeyword()     { return get("search.keyword"); }
    public static String getRegEmail()          { return get("reg.email"); }
    public static String getRegPassword()       { return get("reg.password"); }
    public static String getRegConfirmPassword(){ return get("reg.confirm.password"); }
    public static String getBrowser()           { return get("browser"); }
    public static boolean isHeadless()          { return Boolean.parseBoolean(get("headless")); }
    public static int getExplicitWait()         { return Integer.parseInt(get("explicit.wait")); }
    public static int getPageLoadTimeout()      { return Integer.parseInt(get("page.load.timeout")); }
}
