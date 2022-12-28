package model.TicketPriceDecorator;

public class FrequentTravellerDiscount extends TicketPriceDiscountDecorator {


    public FrequentTravellerDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public String getPriceText() {
        String result = wrappedTicketPrice.getPriceText();
        if (getMetroCard().getAantalGebruikt() > 50) {
            result += " - € 0,20 frequent traveler discount";
        }
        return result;
    }

    @Override
    public double getPrice() {
        double result = wrappedTicketPrice.getPrice();
        if (getMetroCard().getAantalGebruikt() > 50) {
            result -= 0.20;
        }
        return result;
    }
}