package model.TicketPriceDecorator;

public class Age64PlusDiscount extends TicketPriceDiscountDecorator {
    private final TicketPrice wrappedTicketPrice;

    public Age64PlusDiscount(TicketPrice ticketPrice) {
        this.wrappedTicketPrice = ticketPrice;
    }

    @Override
    public String getPriceText() {
        return wrappedTicketPrice.getPriceText() + " - € 0,15 64+ age discount";
    }

    @Override
    public double getPrice() {
        return wrappedTicketPrice.getPrice() - 0.15;
    }

}
