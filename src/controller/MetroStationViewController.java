package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.MetroGate;
import model.Observer;
import view.MetroStationView;

import java.util.List;

public class MetroStationViewController implements Observer {
    private final MetroFacade metroFacade;
    private MetroStationView metroStationView;

    public MetroStationViewController(MetroFacade metroFacade){
        this.metroFacade = metroFacade;
        this.metroFacade.attach(MetroEventsEnum.OPEN_METROSTATION, this);
        metroFacade.attach(MetroEventsEnum.BUY_METROCARD, this);
    }

    public void setView(MetroStationView view) {
        metroStationView = view;
    }

    @Override
    public void update() {
        List<Integer> IDs = metroFacade.getMetroCardIDList();
        metroStationView.updateMetroCardIDList(IDs);
    }

    public void scanMetroCard(int metroCardId, int gateId) {
        try {
            metroFacade.scanMetroGate(metroCardId, gateId);
            updateLastAction(gateId, metroFacade.getLastAction(gateId));

        } catch (IllegalStateException e) {
            // TODO show alert
        }
        finally {
            updateLastAction(gateId, metroFacade.getLastAction(gateId));
        }
    }

    public void walkThroughGate(int gateId) {
        try {
            metroFacade.walkThroughGate(gateId);
            updateLastAction(gateId, metroFacade.getLastAction(gateId));
        } catch (IllegalStateException e) {
            // TODO show alert
        }
        finally {
            updateLastAction(gateId, metroFacade.getLastAction(gateId));
        }
    }

    public void updateLastAction(int gateNumber, String lastAction) {
        metroStationView.setLastAction(gateNumber, lastAction);
    }
}
