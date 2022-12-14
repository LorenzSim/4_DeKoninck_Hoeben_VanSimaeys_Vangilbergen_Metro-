package model;

import model.database.MetroCardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MetroFacade implements Subject {

    private final MetroCardDatabase metrocardDatabase = new MetroCardDatabase();
    private final LoadSaveStrategyFactory<Integer, MetroCard> loadSaveStrategyFactory = new LoadSaveStrategyFactory<>();
    private final Map<MetroEventsEnum, List<Observer>> observers;

    public MetroFacade() {
        MetroEventsEnum[] metroEventsEnums = MetroEventsEnum.values();
        observers = new HashMap<>(metroEventsEnums.length);

        for (MetroEventsEnum event : metroEventsEnums) {
            observers.put(event, new ArrayList<>());
        }
    }

    @Override
    public void attach(MetroEventsEnum eventType,  Observer o) {
        observers.get(eventType).add(o);
    }
    @Override
    public void detach(MetroEventsEnum eventType, Observer o) {
        observers.get(eventType).remove(o);
    }
    @Override
    public void notifyObservers(MetroEventsEnum eventType) {
        observers.get(eventType).forEach(Observer::update);
    }

    public void openMetroStation() {
        LoadSaveStrategy<Integer, MetroCard> strategy = loadSaveStrategyFactory.createLoadSaveStrategy();
        metrocardDatabase.setLoadSaveStrategy(strategy);
        metrocardDatabase.load();
        notifyObservers(MetroEventsEnum.OPEN_METROSTATION);
    }

    public List<MetroCard> getMetroCardList() {
        return metrocardDatabase.getMetroCardList();
    }
    public List<Integer> getMetroCardIDList() {
        return metrocardDatabase.getMetroCardIDList();
    }

    public void buyMetroCard() {
        metrocardDatabase.addMetroCard();
        notifyObservers(MetroEventsEnum.BUY_METROCARD);
    }
}
