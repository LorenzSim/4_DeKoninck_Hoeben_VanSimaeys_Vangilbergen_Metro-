package model.database.loadSaveStrategies;

import model.MetroCard;

import java.lang.reflect.InvocationTargetException;

public class LoadSaveStrategyFactory<K, V> {
    public LoadSaveStrategy<K, V> createLoadSaveStrategy(LoadSaveStrategyEnum loadSaveStrategyEnum) {
        LoadSaveStrategy<K, V> result;
        try {
            String className = "model.database.loadSaveStrategies." + loadSaveStrategyEnum.toString();
            Class<?> strategyClass = Class.forName(className);
            Object obj = strategyClass.getConstructor().newInstance();

            result = (LoadSaveStrategy<K, V>) obj;

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
