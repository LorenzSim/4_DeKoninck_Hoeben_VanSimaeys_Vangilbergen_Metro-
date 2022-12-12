package controller;

import model.MetroFacade;
import model.Observer;
import view.panels.MetroCardOverviewPane;

public class MetroCardOverviewPaneController implements Observer {
    private final MetroFacade metroFacade = new MetroFacade();
    MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();
    @Override
    public void update() {

    }


}
