package model.metroGateStates;

import model.MetroCard;
import model.MetroGate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Open extends MetroGateState{
    public Open(MetroGate metroGate) {
        super(metroGate);
    }

    @Override
    public void scanMetroGate(MetroCard metroCard) {
        throw new IllegalStateException(String.format("%s Card %d scanned more than once at Gate %d!", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) ,metroCard.getId(),metroGate.getGateNumber()));
    }

    @Override
    public void activate() {
        throw new IllegalStateException(String.format("%s Gate %d is already activated!" , LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), metroGate.getGateNumber()));
    }

    @Override
    public void walkThroughGate() {
        metroGate.setClosedState();
        metroGate.setLastAction("Card passed gate");
    }

    @Override
    public void deactivate() {
        throw new IllegalStateException(String.format("%s Cannot deactivate open gate %d", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), metroGate.getGateNumber()));
    }
}
