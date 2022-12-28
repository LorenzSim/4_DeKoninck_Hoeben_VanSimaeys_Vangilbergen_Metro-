package model.metroGateStates;

import model.MetroCard;
import model.MetroGate;

public abstract class MetroGateState {
    protected MetroGate metroGate;
    public MetroGateState(MetroGate metroGate) {
        this.metroGate = metroGate;
    }
    public abstract void scanMetroGate(MetroCard metroCard);
    public abstract void activate();
    public abstract void walkThroughGate();
    public abstract void deactivate();

}
