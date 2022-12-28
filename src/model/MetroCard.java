package model;


import java.time.LocalDate;

public class MetroCard {
    private int id, maand, jaartal, aantalBeschikbaar, aantalGebruikt;

    public MetroCard(int id, int maand, int jaartal, int aantalBeschikbaar, int aantalGebruikt){
        setId(id);
        setMaand(maand);
        setJaartal(jaartal);
        setAantalBeschikbaar(aantalBeschikbaar);
        setAantalGebruikt(aantalGebruikt);
    }

    public boolean addRides(int rides) {
        if (isValid()) {
            aantalBeschikbaar += rides;
            return true;
        }
        return false;
    }

    public boolean isValid() {
        LocalDate now =  LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        return currentYear == jaartal || currentYear - jaartal == 1 && currentMonth <= maand;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getMaand() {
        return maand;
    }

    public void setMaand(int maand){
        this.maand = maand;
    }

    public int getJaartal() {
        return jaartal;
    }

    public void setJaartal(int jaartal){
        this.jaartal = jaartal;
    }

    public int getAantalBeschikbaar() {
        return aantalBeschikbaar;
    }

    public void setAantalBeschikbaar(int aantalBeschikbaar){
        this.aantalBeschikbaar = aantalBeschikbaar;
    }

    public int getAantalGebruikt() {
        return aantalGebruikt;
    }

    public void setAantalGebruikt(int aantalGebruikt){
        this.aantalGebruikt = aantalGebruikt;
    }

    @Override
    public String toString() {
        return "MetroCard{" +
                "id=" + id +
                ", maand=" + maand +
                ", jaartal=" + jaartal +
                ", aantalBeschikbaar=" + aantalBeschikbaar +
                ", aantalGebruikt=" + aantalGebruikt +
                '}';
    }
}
