package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.panels.ControlCenterPane;

public class ControlCenterPaneController implements Observer {
    private final MetroFacade metroFacade;
    private ControlCenterPane controlCenterPane;

    public ControlCenterPaneController(MetroFacade metroFacade) {
        this.metroFacade = metroFacade;
        metroFacade.attach(MetroEventsEnum.BUY_METROCARDS_TICKETS, this);
    }

    public void setView(ControlCenterPane controlCenterPane) {
        this.controlCenterPane = controlCenterPane;
    }

    @Override
    public void update() {

    }

    public void openMetroStation() {
        metroFacade.openMetroStation();
    }
}
