package model.TicketPriceDecorator;

import model.MetroCard;

public abstract class TicketPrice {
    private boolean is24Min;
    private boolean is64Plus;
    private boolean isStudent;
    private MetroCard metroCard;

    public boolean is24Min() {
        return is24Min;
    }

    public void setIs24Min(boolean is24Min) {
        this.is24Min = is24Min;
    }

    public boolean is64Plus() {
        return is64Plus;
    }

    public void setIs64Plus(boolean is64Plus) {
        this.is64Plus = is64Plus;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public MetroCard getMetroCard() {
        return metroCard;
    }

    public void setMetroCard(MetroCard metroCard) {
        this.metroCard = metroCard;
    }

    public abstract String getPriceText();
    public abstract double getPrice();

}
