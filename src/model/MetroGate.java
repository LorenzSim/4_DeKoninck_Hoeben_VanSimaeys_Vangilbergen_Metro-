package model;

import model.metroGateStates.Closed;
import model.metroGateStates.Inactive;
import model.metroGateStates.MetroGateState;
import model.metroGateStates.Open;

public class MetroGate {

    private int scannedCards;
    private final int gateNumber;
    private String lastAction;
    private MetroGateState currentState;
    private final Inactive inactiveState;
    private final Open openState;
    private final Closed closedState;

    public MetroGate(int gateNumber) {
        this.inactiveState = new Inactive(this);
        this.openState = new Open(this);
        this.closedState = new Closed(this);
        this.gateNumber = gateNumber;
        setInactiveState();
        scannedCards = 0;
    }

    public void setCurrentState(MetroGateState currentState) {
        this.currentState = currentState;
    }
    public void setInactiveState() {
        setLastAction("inactive");
        setCurrentState(inactiveState);
    }
    public void setOpenState() {
        setCurrentState(openState);
    }
    public void setClosedState() {
        setCurrentState(closedState);
    }
    public void activate() {
        currentState.activate();
    }
    public void deactivate() {
        currentState.deactivate();
    }
    public void scanMetroGate(MetroCard metroCard) {
        currentState.scanMetroGate(metroCard);
    }
    public void walkThroughGate() {
        currentState.walkThroughGate();
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    public void increaseScannedCards() {
        ++scannedCards;
    }
    public int getScannedCards() {
        return scannedCards;
    }
    public int getGateNumber() {
        return gateNumber;
    }
    public MetroGateState getCurrentState() {
        return currentState;
    }

}
