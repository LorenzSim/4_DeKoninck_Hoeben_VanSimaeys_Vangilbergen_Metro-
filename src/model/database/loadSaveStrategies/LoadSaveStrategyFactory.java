package model.database.loadSaveStrategies;

import model.MetroCard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class LoadSaveStrategyFactory<K, V> {
    public LoadSaveStrategy<K, V> createLoadSaveStrategy() {

        LoadSaveStrategy<K, V> result;
        try {
            Properties properties = new Properties();
            InputStream is = Files.newInputStream(Paths.get("src/bestanden/settings.properties"));
            properties.load(is);
            is.close();
            LoadSaveStrategyEnum strategy = LoadSaveStrategyEnum.valueOf(properties.getProperty("loadSaveStrategy"));

            String className = "model.database.loadSaveStrategies." + strategy;
            Class<?> strategyClass = Class.forName(className);
            Object obj = strategyClass.getConstructor().newInstance();

            result = (LoadSaveStrategy<K, V>) obj;

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
