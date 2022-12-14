package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.MetroTicketView;

import java.util.List;

public class MetroTicketViewController implements Observer {
    private final MetroFacade metroFacade;
    private MetroTicketView metroTicketView;
    public MetroTicketViewController(MetroFacade metroFacade) {
        this.metroFacade = metroFacade;
        metroFacade.attach(MetroEventsEnum.OPEN_METROSTATION, this);
        metroFacade.attach(MetroEventsEnum.BUY_METROCARD, this);
    }
    public void setView(MetroTicketView view) {
        metroTicketView = view;
    }

    public void buyMetroCard(){
        metroFacade.buyMetroCard();
    }

    @Override
    public void update() {
        List<Integer> metroCardIdList = metroFacade.getMetroCardIDList();
        metroTicketView.updateMetroCardIDList(metroCardIdList);
    }
}
