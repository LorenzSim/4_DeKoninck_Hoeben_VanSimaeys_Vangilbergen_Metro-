package model.database;

import model.MetroCard;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.util.ArrayList;
import java.util.Map;

public class MetrocardDatabase {

    private Map<Integer, MetroCard> cards;
    private final LoadSaveStrategyFactory<Integer, MetroCard> loadSaveStrategyFactory;

    private LoadSaveStrategy<Integer, MetroCard> loadSaveStrategy;

    public MetrocardDatabase() {
        loadSaveStrategyFactory = new LoadSaveStrategyFactory<>();
    }
    public void load() {
        cards = loadSaveStrategy.load();
    }
    public void save() {
        loadSaveStrategy.save(cards);
    }

    public void setLoadSaveStrategy(LoadSaveStrategyEnum strategy) {
        loadSaveStrategy = loadSaveStrategyFactory.createLoadSaveStrategy(strategy);
    }
    public ArrayList<MetroCard> getCards() {
        load();
        return new ArrayList<>(cards.values());
    }
}
