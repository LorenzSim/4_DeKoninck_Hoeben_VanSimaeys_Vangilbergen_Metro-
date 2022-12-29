package model;

import model.database.MetroCardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

import java.util.*;


public class MetroFacade implements Subject {

    private final MetroCardDatabase metrocardDatabase = new MetroCardDatabase();
    private final MetroStation metroStation = new MetroStation();
    private final Map<MetroEventsEnum, List<Observer>> observers;

    public MetroFacade() {
        observers = new HashMap<>();
        for (MetroEventsEnum event : MetroEventsEnum.values()) {
            observers.put(event, new ArrayList<>());
        }
    }
    @Override
    public void addObserver(MetroEventsEnum eventType, Observer o) {
        observers.get(eventType).add(o);
    }
    @Override
    public void removeObserver(MetroEventsEnum eventType, Observer o) {
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

    public void activate(int gateNumber) {
        metroStation.activate(gateNumber);
        notifyObservers(MetroEventsEnum.ACTIVATE_METROGATE);
    }

    public void scanMetroGate(int metroCardId, int gateNumber) {
        MetroCard metroCard = metrocardDatabase.getMetroCard(metroCardId);
        try {
            metroStation.scanMetroGate(metroCard, gateNumber);
            metroCard.useTicket();
            notifyObservers(MetroEventsEnum.SCAN_METROGATE);
        } catch (IllegalStateException e) {
            notifyObservers(MetroEventsEnum.NEW_ALERT);
        }
    }

    public void walkThroughGate(int gateNumber) {
        try {
            metroStation.walkThroughGate(gateNumber);
        } catch (IllegalStateException e) {
            notifyObservers(MetroEventsEnum.NEW_ALERT);
        }
    }

    public void deactivate(int gateNumber) {
        metroStation.deactivate(gateNumber);
        notifyObservers(MetroEventsEnum.DEACTIVATE_METROGATE);
    }
    public String getLastAction(int gateId) {
        return metroStation.getLastAction(gateId);
    }
    public String getLastAlert() {
        return metroStation.getLastAlert();
    }
    public int getScannedCards(int gateNumber) {
        return metroStation.getScannedTickets(gateNumber);
    }

    public List<String> getMetroTicketDiscountList() {
        return Arrays.asList(Settings.getProperty("PRICEDISCOUNTS").split(","));
    }

}
