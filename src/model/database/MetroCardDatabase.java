package model.database;

import model.MetroCard;
import model.database.loadSaveStrategies.LoadSaveStrategy;

import java.util.ArrayList;
import java.util.Map;

public class MetroCardDatabase {

    private Map<Integer, MetroCard> cards;

    private LoadSaveStrategy<Integer, MetroCard> loadSaveStrategy;

    public MetroCardDatabase() {

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
    public ArrayList<MetroCard> getMetroCardList() {
        return new ArrayList<>(cards.values());
    }
    public ArrayList<Integer> getMetroCardIDList() {
        return new ArrayList<>(cards.keySet());
    }
}
