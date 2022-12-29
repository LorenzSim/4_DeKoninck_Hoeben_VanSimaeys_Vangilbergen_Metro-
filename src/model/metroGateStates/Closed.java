package model.metroGateStates;

import model.MetroCard;
import model.MetroGate;

import java.time.LocalTime;

public class Closed extends MetroGateState{
    public Closed(MetroGate metroGate) {
        super(metroGate);
    }

    @Override
    public void scanMetroGate(MetroCard metroCard) {
        if (metroCard.isExpired()) {
            metroGate.setLastAction(String.format("Card %s is expired", metroCard.getId())); return;
        }
        if (metroCard.getAantalBeschikbaar() == 0){
            metroGate.setLastAction(String.format("Card %s has no tickets", metroCard.getId())); return;
        }
        metroCard.useTicket();
        metroGate.increaseScannedCards();
        metroGate.setOpenState();
        metroGate.setLastAction(String.format("Card %s scanned", metroCard.getId()));
    }

    @Override
    public void activate() {
        throw new IllegalStateException(String.format("Gate %d is already activated!", metroGate.getGateNumber()));
    }

    @Override
    public void walkThroughGate() {
        metroGate.setLastAction("UNAUTHORIZED PASSAGE");
        throw new IllegalStateException(String.format("%s UNAUTHORIZED PASSAGE GATE %d", LocalTime.now(), metroGate.getGateNumber()));
    }

    @Override
    public void deactivate() {
        metroGate.setInactiveState();
    }
}
