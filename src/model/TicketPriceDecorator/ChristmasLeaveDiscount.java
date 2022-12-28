package model.TicketPriceDecorator;

public class ChristmasLeaveDiscount extends TicketPriceDiscountDecorator {
    public ChristmasLeaveDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public double getPrice() {
        return this.wrappedTicketPrice.getPrice() - 0.10;
    }

    @Override
    public String getPriceText() {
        return this.wrappedTicketPrice.getPriceText() + " - €0,10 Christmas leave discount";
    }
}

