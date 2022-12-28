package model.TicketPriceDecorator;

public class BasicTicketPrice extends TicketPrice {

    @Override
    public String getPriceText() {
        return "Basic price of a ride is €2,10";
    }

    @Override
    public double getPrice() {
        return 2.10;
    }
}
