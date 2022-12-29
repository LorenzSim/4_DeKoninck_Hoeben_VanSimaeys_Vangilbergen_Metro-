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
        metroFacade.addObserver(MetroEventsEnum.BUY_METROCARDS_TICKETS, this);
        metroFacade.addObserver(MetroEventsEnum.SCAN_METROGATE, this);
        metroFacade.addObserver(MetroEventsEnum.NEW_ALERT, this);
        metroFacade.addObserver(MetroEventsEnum.CLOSE_METROSTATION, this);
    }

    public void setView(ControlCenterPane controlCenterPane) {
        this.controlCenterPane = controlCenterPane;
    }

    @Override
    public void update() {
        if (!metroFacade.isStationIsOpen()){
            controlCenterPane.deactivateGate(1);
            controlCenterPane.deactivateGate(2);
            controlCenterPane.deactivateGate(3);
            return;
        }

        controlCenterPane.upDateTotalPriceSold(metroFacade.getTotalPriceSold());
        controlCenterPane.updateNumberOfSoldTickets(metroFacade.getAmountSold());
        updateScannedTickets();
        String lastAlert = metroFacade.getLastAlert();
        if (lastAlert != null) {
            createAlert(lastAlert);
        }
    }

    public void activate(int gateNumber) {
        metroFacade.activate(gateNumber);
    }

    public void deactivate(int gateNumber) {
        metroFacade.deactivate(gateNumber);
        controlCenterPane.deactivateGate(gateNumber);
    }

    private void updateScannedTickets() {
        controlCenterPane.updateScannedTickets(1, metroFacade.getScannedCards(1));
        controlCenterPane.updateScannedTickets(2, metroFacade.getScannedCards(2));
        controlCenterPane.updateScannedTickets(3, metroFacade.getScannedCards(3));
    }



    private void createAlert(String alertMessage) {
        controlCenterPane.addAlert(alertMessage);
    }

    public void openMetroStation() {
        metroFacade.openMetroStation();
    }

    public void closeMetroStation() {
        metroFacade.closeMetroStation();
    }
}
