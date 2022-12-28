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
        if (!metroCard.isExpired() && metroCard.getAantalBeschikbaar() > 0){
            metroGate.increaseScannedCards();
            metroGate.setOpenState();
        }

    }

    @Override
    public void activate() {
        throw new IllegalStateException(String.format("Gate %d is already active!", metroGate.getGateNumber()));
    }

    @Override
    public void walkThroughGate() {
        throw new IllegalStateException(String.format("%s UNAUTHORIZED PASSAGE GATE %d", LocalTime.now(), metroGate.getGateNumber()));
    }

    @Override
    public void deactivate() {
        metroGate.setInactiveState();
    }
}
