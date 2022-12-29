package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Settings {
    private static final Path settingsFile = Paths.get("src/bestanden/settings.properties");
    private static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream is = Files.newInputStream(settingsFile);
            properties.load(is);
            is.close();
        }
        catch (IOException ignored) {}
        return properties;
    }

    private static void saveProperties(Properties properties) {
        try {
            OutputStream os = Files.newOutputStream(settingsFile);
            properties.store(os, null);
            os.close();
        }
        catch (IOException ignored) {}
    }

    public static String getProperty(String name) {
        return loadProperties().getProperty(name);
    }

    public static void setProperty(String name, String value) {
        Properties currentProperties = loadProperties();
        currentProperties.setProperty(name, value);
        saveProperties(currentProperties);
    }

}
