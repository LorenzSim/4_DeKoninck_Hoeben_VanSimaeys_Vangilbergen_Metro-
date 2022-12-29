package model.metroGateStates;

import model.MetroCard;
import model.MetroGate;

import java.time.LocalTime;

public class Inactive extends MetroGateState {
    public Inactive(MetroGate metroGate) {
        super(metroGate);
    }

    @Override
    public void scanMetroGate(MetroCard metroCard) {
        throw new IllegalStateException("Gate is inactive");
    }

    @Override
    public void activate() {
        metroGate.setLastAction("activated");
        metroGate.setClosedState();
    }

    @Override
    public void walkThroughGate() {
        metroGate.setLastAction("UNAUTHORIZED PASSAGE");
        throw new IllegalStateException(String.format("%s UNAUTHORIZED PASSAGE GATE %d", LocalTime.now(), metroGate.getGateNumber()));
    }

    @Override
    public void deactivate() {
        throw new IllegalStateException(String.format("Gate %s is already inactive!", metroGate.getGateNumber()));
    }
}
