package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;

public class ControlCenterPaneController implements Observer {
    private final MetroFacade metroFacade;

    public ControlCenterPaneController(MetroFacade metroFacade) {
        this.metroFacade = metroFacade;
        metroFacade.attach(MetroEventsEnum.BUY_METROCARDS_TICKETS, this);
    }

    @Override
    public void update() {

    }

    public void openMetroStation() {
        metroFacade.openMetroStation();
    }
}
