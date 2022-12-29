package model.metroGateStates;

import model.MetroCard;
import model.MetroGate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Inactive extends MetroGateState {
    public Inactive(MetroGate metroGate) {
        super(metroGate);
    }

    @Override
    public void scanMetroGate(MetroCard metroCard) {
        throw new IllegalStateException(String.format("%s Unable to scan card %s at gate %d: gate is inactive",LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), metroCard.getId(), metroGate.getGateNumber()));
    }

    @Override
    public void activate() {
        metroGate.setLastAction("activated");
        metroGate.setClosedState();
    }

    @Override
    public void walkThroughGate() {
        metroGate.setLastAction("UNAUTHORIZED PASSAGE");
        throw new IllegalStateException(String.format("%s UNAUTHORIZED PASSAGE GATE %d", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), metroGate.getGateNumber()));
    }

    @Override
    public void deactivate() {
        throw new IllegalStateException(String.format("%s Gate %s is already inactive!",LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), metroGate.getGateNumber()));
    }
}
