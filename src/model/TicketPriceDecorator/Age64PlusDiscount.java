package model.TicketPriceDecorator;

public class Age64PlusDiscount extends TicketPriceDiscountDecorator {

    public Age64PlusDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public String getPriceText() {
        String result = wrappedTicketPrice.getPriceText();
        if (is64Plus()){
            result += " - € 0,15 64+ age discount";
        }
        return result;
    }

    @Override
    public double getPrice() {
        double result = wrappedTicketPrice.getPrice();
        if (is64Plus()) {
            result -= 0.15;
        }
        return result;
    }

}
