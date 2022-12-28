package model.TicketPriceDecorator;

public abstract class TicketPriceDiscountDecorator extends TicketPrice{
    protected TicketPrice wrappedTicketPrice;
    public TicketPriceDiscountDecorator(TicketPrice ticketPrice) {
        this.wrappedTicketPrice = ticketPrice;
        setIs24Min(wrappedTicketPrice.is24Min());
        setMetroCard(wrappedTicketPrice.getMetroCard());
        setStudent(wrappedTicketPrice.isStudent());
        setIs64Plus(wrappedTicketPrice.is64Plus());
    }

    public abstract double getPrice() ;

    public abstract String getPriceText();
}
