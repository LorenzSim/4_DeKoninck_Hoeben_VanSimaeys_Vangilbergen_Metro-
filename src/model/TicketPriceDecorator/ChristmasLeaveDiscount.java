package model.TicketPriceDecorator;

public class ChristmasLeaveDiscount extends TicketPriceDiscountDecorator {
    private final TicketPrice wrappedTicketPrice;

    public ChristmasLeaveDiscount(TicketPrice ticketPrice) {
        this.wrappedTicketPrice = ticketPrice;
    }

    @Override
    public double getPrice() {
        return wrappedTicketPrice.getPrice() - 0.10;
    }

    @Override
    public String getPriceText() {
        return wrappedTicketPrice.getPriceText() + " - €0,10 Christmas leave discount";
    }
}

