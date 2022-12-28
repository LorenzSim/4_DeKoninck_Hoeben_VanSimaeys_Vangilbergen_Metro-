package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import model.TicketPriceDecorator.TicketPrice;
import model.TicketPriceDecorator.TicketPriceFactory;
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

    public void addRidesToCart(int metroCardID, int numberOfRides, boolean is24Min, boolean isStudent, boolean isOlderThan64) {
        TicketPrice ticketPrice = TicketPriceFactory.getInstance().createTicketPrice(is24Min, isOlderThan64, isStudent, metroFacade.getMetroCard(metroCardID));
        metroTicketView.setTotalPrice(String.format("€ %2.2f", ticketPrice.getPrice() * numberOfRides));
        metroTicketView.setDiscountText(ticketPrice.getPriceText());
    }

    public void buyMetroCardTickets(int metroCardId, double totalPrice, int numberOfRides) {
        metroFacade.buyMetroCardTickets(metroCardId, totalPrice, numberOfRides);
    }
}
