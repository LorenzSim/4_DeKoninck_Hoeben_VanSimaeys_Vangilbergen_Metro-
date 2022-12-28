package model.TicketPriceDecorator;

public class StudentDiscount extends TicketPriceDiscountDecorator {


    public StudentDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public String getPriceText() {
        String result = wrappedTicketPrice.getPriceText();
        if (isStudent()){
            result +=  " - € 0,25 student discount";
        }
        return result;
    }

    @Override
    public double getPrice() {
        double result = wrappedTicketPrice.getPrice();
        if (isStudent()) {
            result -= 0.25;
        }
        return result;
    }
}
