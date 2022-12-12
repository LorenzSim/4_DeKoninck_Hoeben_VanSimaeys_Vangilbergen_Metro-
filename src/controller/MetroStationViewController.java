package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.MetroStationView;

import java.util.List;

public class MetroStationViewController implements Observer {
    private final MetroFacade metroFacade;
    private MetroStationView metroStationView;

    public MetroStationViewController(MetroFacade metroFacade){
        this.metroFacade = metroFacade;
        this.metroFacade.attach(MetroEventsEnum.OPEN_METROSTATION, this);
    }

    public void setView(MetroStationView view) {
        metroStationView = view;
    }

    @Override
    public void update() {
        List<Integer> IDs = metroFacade.getMetroCardIDList();
        metroStationView.updateMetroCardIDList(IDs);
    }
}
