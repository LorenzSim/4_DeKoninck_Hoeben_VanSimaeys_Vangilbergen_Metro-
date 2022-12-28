package model.TicketPriceDecorator;

public class StudentDiscount extends TicketPriceDiscountDecorator {

    private final TicketPrice wrappedTicketPrice;

    public StudentDiscount(TicketPrice ticketPrice) {
        this.wrappedTicketPrice = ticketPrice;
    }

    @Override
    public String getPriceText() {
        return wrappedTicketPrice.getPriceText() + " - € 0,25 student discount";
    }

    @Override
    public double getPrice() {
        return wrappedTicketPrice.getPrice() - 0.25;
    }
}
