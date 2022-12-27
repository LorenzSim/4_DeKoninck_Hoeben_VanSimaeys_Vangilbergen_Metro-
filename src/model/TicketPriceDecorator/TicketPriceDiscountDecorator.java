package model.TicketPriceDecorator;

public abstract class TicketPriceDiscountDecorator extends TicketPrice{

    public abstract double getPrice() ;

    public abstract String getPriceText();
}
