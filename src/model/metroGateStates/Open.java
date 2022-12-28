package model.metroGateStates;

import model.MetroCard;
import model.MetroGate;

public class Open extends MetroGateState{
    public Open(MetroGate metroGate) {
        super(metroGate);
    }

    @Override
    public void scanMetroGate(MetroCard metroCard) {
        throw new IllegalStateException(String.format("Gate %d is already open!", metroGate.getGateNumber()));
    }

    @Override
    public void activate() {
        throw new IllegalStateException(String.format("Gate %d is already activated!", metroGate.getGateNumber()));
    }

    @Override
    public void walkThroughGate() {
        metroGate.setClosedState();
        metroGate.setLastAction("Card passed gate");
    }

    @Override
    public void deactivate() {
        throw new IllegalStateException(String.format("Cannot deactivate open gate %d", metroGate.getGateNumber()));
    }
}
