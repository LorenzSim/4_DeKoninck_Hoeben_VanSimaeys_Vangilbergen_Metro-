package controller;

import model.MetroCard;
import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.panels.MetroCardOverviewPane;

import java.util.List;

public class MetroCardOverviewPaneController implements Observer {
    private final MetroFacade metroFacade;
    private MetroCardOverviewPane metroCardOverviewPane;
    public MetroCardOverviewPaneController(MetroFacade metroFacade) {
        this.metroFacade = metroFacade;
        metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
        metroFacade.addObserver(MetroEventsEnum.BUY_METROCARD, this);
        metroFacade.addObserver(MetroEventsEnum.BUY_METROCARDS_TICKETS, this);
        metroFacade.addObserver(MetroEventsEnum.SCAN_METROGATE, this);
    }

    public void setView(MetroCardOverviewPane view) {
        metroCardOverviewPane = view;
    }
    @Override
    public void update() {
        List<MetroCard> cards = metroFacade.getMetroCardList();
        metroCardOverviewPane.updateMetroCardList(cards);
    }


}
