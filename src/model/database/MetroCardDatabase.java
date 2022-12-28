package model.database;

import model.MetroCard;
import model.database.loadSaveStrategies.LoadSaveStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class MetroCardDatabase {

    private Map<Integer, MetroCard> cards;
    private LoadSaveStrategy<Integer, MetroCard> loadSaveStrategy;
    private int amountSold;
    private double totalPriceSold;

    public MetroCardDatabase() {
        amountSold = 0;
        totalPriceSold = 0;
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

    public void addMetroCard() {
        LocalDate currentDate = LocalDate.now();
        int id = generateId();
        MetroCard metroCard = new MetroCard(id, currentDate.getMonthValue(), currentDate.getYear(), 2, 0);
        cards.put(id, metroCard);
    }

    public boolean addTicketsToCard(int metroCardId, int amount, double price) {
        boolean ridesAreAddedToCard = cards.get(metroCardId).addRides(amount);
        if (ridesAreAddedToCard) {
            this.amountSold += amount;
            this.totalPriceSold += price;
        }
        return ridesAreAddedToCard;
    }

    public MetroCard getMetroCard(int id) {
        return cards.get(id);
    }
    public int getAmountSold() {
        return amountSold;
    }

    public double getTotalPriceSold() {
        return totalPriceSold;
    }

    private int generateId() {
        int result = -1;
         for (Integer id : cards.keySet()) {
             if (id > result) {
                 result = id;
             }
         }
         return result + 1;
     }
}
