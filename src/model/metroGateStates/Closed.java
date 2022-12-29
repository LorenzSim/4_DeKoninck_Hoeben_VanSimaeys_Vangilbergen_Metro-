package model.metroGateStates;

import model.MetroCard;
import model.MetroGate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Closed extends MetroGateState{
    public Closed(MetroGate metroGate) {
        super(metroGate);
    }

    @Override
    public void scanMetroGate(MetroCard metroCard) {
        if (metroCard.isExpired()) {
            metroGate.setLastAction(String.format("Card %s is expired", metroCard.getId()));
            throw new IllegalStateException(String.format("%s Unable to scan expired card %d at gate %d", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), metroCard.getId(), metroGate.getGateNumber()));
        }
        if (metroCard.getAantalBeschikbaar() == 0){
            metroGate.setLastAction(String.format("Card %s has no tickets", metroCard.getId()));
            throw new IllegalStateException(String.format("%s Unable to scan card %s without ticket at gate %d", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), metroCard.getId(), metroGate.getGateNumber()));
        }
        metroGate.increaseScannedCards();
        metroGate.setOpenState();
        metroGate.setLastAction(String.format("Card %s scanned", metroCard.getId()));
    }

    @Override
    public void activate() {
        throw new IllegalStateException(String.format("%s Gate %d is already activated!", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), metroGate.getGateNumber()));
    }

    @Override
    public void walkThroughGate() {
        metroGate.setLastAction("UNAUTHORIZED PASSAGE");
        throw new IllegalStateException(String.format("%s UNAUTHORIZED PASSAGE GATE %d", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), metroGate.getGateNumber()));
    }

    @Override
    public void deactivate() {
        metroGate.setInactiveState();
    }
}
