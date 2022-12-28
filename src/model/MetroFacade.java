package model;

import model.database.MetroCardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class MetroFacade implements Subject {

    private final MetroCardDatabase metrocardDatabase = new MetroCardDatabase();
    private final Map<MetroEventsEnum, List<Observer>> observers;

    public MetroFacade() {
        observers = new HashMap<>();
        for (MetroEventsEnum event : MetroEventsEnum.values()) {
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
        LoadSaveStrategy<Integer, MetroCard> strategy = LoadSaveStrategyFactory.getInstance().createLoadSaveStrategy() ;
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

    public MetroCard getMetroCard(int id) {
        return metrocardDatabase.getMetroCard(id);
    }

    public void buyMetroCard() {
        metrocardDatabase.addMetroCard();
        notifyObservers(MetroEventsEnum.BUY_METROCARD);
    }
    public void buyMetroCardTickets(int metroCardId, double totalPrice, int numberOfRides) {
        if (metrocardDatabase.addTicketsToCard(metroCardId, numberOfRides, totalPrice)){
            notifyObservers(MetroEventsEnum.BUY_METROCARDS_TICKETS);
        }
    }
    public int getAmountSold() {
        return metrocardDatabase.getAmountSold();
    }
    public double getTotalPriceSold() {
        return metrocardDatabase.getTotalPriceSold();
    }

    public List<String> getMetroTicketDiscountList() {
        Properties properties = new Properties();
        try {
            InputStream is = Files.newInputStream(Paths.get("src/bestanden/settings.properties"));
            properties.load(is);
            is.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return Arrays.asList(properties.getProperty("PRICEDISCOUNTS").split(","));
    }
}
