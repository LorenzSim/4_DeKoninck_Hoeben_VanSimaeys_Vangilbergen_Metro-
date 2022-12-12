package model.database;

import model.MetroCard;
import model.database.loadSaveStrategies.LoadSaveStrategy;

import java.util.ArrayList;
import java.util.Map;

public class MetroCardOverviewPane {

    private Map<Integer, MetroCard> cards;

    private LoadSaveStrategy<Integer, MetroCard> loadSaveStrategy;

    public MetroCardOverviewPane() {

    }
    public void load() {
        cards = loadSaveStrategy.load();
    }
    public void save() {
        loadSaveStrategy.save(cards);
    }

    public void setLoadSaveStrategy(LoadSaveStrategy<Integer, MetroCard> strategy) {
        loadSaveStrategy = strategy;
    }
    public ArrayList<MetroCard> getCards() {
        return new ArrayList<>(cards.values());
    }
}
