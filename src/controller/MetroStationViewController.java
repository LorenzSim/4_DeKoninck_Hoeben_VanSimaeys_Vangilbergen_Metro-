package controller;

import model.MetroFacade;
import model.Observer;

public class MetroStationViewController implements Observer {
    private final MetroFacade metroFacade = new MetroFacade();

    @Override
    public void update() {

    }
}
