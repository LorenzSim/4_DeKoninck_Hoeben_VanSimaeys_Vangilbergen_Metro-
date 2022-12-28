package model;

import java.util.Map;
import java.util.TreeMap;

public class MetroStation {

    private final Map<Integer, MetroGate> metroGates;

    public MetroStation() {
        this(3);
    }

    public MetroStation(int gateAmount) {
        metroGates = new TreeMap<>();
        for (int i = 1; i <= gateAmount; i++) {
            metroGates.put(i, new MetroGate(i));
        }
    }

    public String getLastAction(int gateNumber) {
        return metroGates.get(gateNumber).getLastAction();
    }
    public int getScannedTickets(int gateNumber) {
        return metroGates.get(gateNumber).getScannedCards();
    }

    public void activate(int metroGateNumber){
        metroGates.get(metroGateNumber).activate();
    }

    public void scanMetroGate(MetroCard metroCard, int metroGateNumber) {
        metroGates.get(metroGateNumber).scanMetroGate(metroCard);
    }

    public void walkThroughGate(int metroGateNumber) {
        metroGates.get(metroGateNumber).walkThroughGate();
    }

    public void deactivate(int metroGateNumber) {
        metroGates.get(metroGateNumber).deactivate();
    }


}
