package model.database.loadSaveStrategies;

import model.MetroCard;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class LoadSaveStrategyFactory {

    private static LoadSaveStrategyFactory factoryInstance;
    private LoadSaveStrategyFactory() {}
    public static LoadSaveStrategyFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new LoadSaveStrategyFactory();
        }
        return factoryInstance;
    }

    public LoadSaveStrategy<Integer, MetroCard> createLoadSaveStrategy() {

        LoadSaveStrategy<Integer, MetroCard> result;
        try {
            Properties properties = new Properties();
            InputStream is = Files.newInputStream(Paths.get("src/bestanden/settings.properties"));
            properties.load(is);
            is.close();
            LoadSaveStrategyEnum strategy = LoadSaveStrategyEnum.valueOf(properties.getProperty("loadSaveStrategy"));

            String className = "model.database.loadSaveStrategies." + strategy;
            Class<?> strategyClass = Class.forName(className);
            Object obj = strategyClass.getConstructor().newInstance();

            result = (LoadSaveStrategy<Integer, MetroCard>) obj;

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
