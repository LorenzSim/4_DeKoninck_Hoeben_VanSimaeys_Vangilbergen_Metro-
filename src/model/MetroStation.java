package model;

import model.metroGateStates.Inactive;
import model.metroGateStates.MetroGateState;

import java.util.Map;
import java.util.TreeMap;

public class MetroStation {

    private final Map<Integer, MetroGate> metroGates;
    private String lastAlert;


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
        try {
            metroGates.get(metroGateNumber).activate();
        } catch (IllegalStateException e) {
            setLastAlert(e.getMessage());
            throw e;
        }
    }

    public void scanMetroGate(MetroCard metroCard, int metroGateNumber) {
        try {
            metroGates.get(metroGateNumber).scanMetroGate(metroCard);
        } catch (IllegalStateException e) {
            setLastAlert(e.getMessage());
            throw e;
        }
    }

    public void walkThroughGate(int metroGateNumber) {
        try {
            metroGates.get(metroGateNumber).walkThroughGate();
        } catch (IllegalStateException e) {
            setLastAlert(e.getMessage());
            throw e;
        }
    }

    public void deactivate(int metroGateNumber) {
        try {
            metroGates.get(metroGateNumber).deactivate();
        } catch (IllegalStateException e) {
            setLastAlert(e.getMessage());
            throw e;
        }
    }

    public String getLastAlert() {
        String result = lastAlert;
        lastAlert = null;
        return result;
    }

    public void closeStation() {
        metroGates.forEach((id, gate) -> gate.setInactiveState());
    }

    public void setLastAlert(String lastAlert) {
        this.lastAlert = lastAlert;
    }
    public boolean isGateActive(int gateNumber) {
        return metroGates.get(gateNumber).isActive();
    }
}
