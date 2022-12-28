package model.TicketPriceDecorator;

public class FrequentTravellerDiscount extends TicketPriceDiscountDecorator {

    private final TicketPrice wrappedTicketPrice;

    public FrequentTravellerDiscount(TicketPrice ticketPrice) {
        this.wrappedTicketPrice = ticketPrice;
    }

    @Override
    public String getPriceText() {
        return wrappedTicketPrice.getPriceText() + " - € 0,20 frequent traveler discount";
    }

    @Override
    public double getPrice() {
        return wrappedTicketPrice.getPrice() - 0.20;
    }
}