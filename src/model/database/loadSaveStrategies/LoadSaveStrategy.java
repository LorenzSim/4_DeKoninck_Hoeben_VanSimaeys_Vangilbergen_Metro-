package model.database.loadSaveStrategies;

import java.util.Comparator;
import java.util.Map;

public interface LoadSaveStrategy<K, V> {
    Map<K, V> load();
    void save(Map<K, V> items);
}
