package controller;

import model.MetroFacade;
import model.Observer;

public class MetroTicketViewController implements Observer {
    private final MetroFacade metroFacade = new MetroFacade();

    @Override
    public void update() {

    }
}
