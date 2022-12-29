package model.database.loadSaveStrategies;

import model.MetroCard;
import model.Settings;

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

            LoadSaveStrategyEnum strategy = LoadSaveStrategyEnum.valueOf(Settings.getProperty("loadSaveStrategy"));

            Class<?> strategyClass = Class.forName(strategy.getClassFileName());
            Object obj = strategyClass.getConstructor().newInstance();

            result = (LoadSaveStrategy<Integer, MetroCard>) obj;

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
