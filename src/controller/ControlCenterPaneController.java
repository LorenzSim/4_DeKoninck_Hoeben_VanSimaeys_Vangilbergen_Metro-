package controller;

import model.MetroFacade;
import model.Observer;

public class ControlCenterPaneController implements Observer {
    private final MetroFacade metroFacade;

    public ControlCenterPaneController(MetroFacade metroFacade) {
        this.metroFacade = metroFacade;
    }

    @Override
    public void update() {

    }

    public void openMetroStation() {
        metroFacade.openMetroStation();
    }
}
