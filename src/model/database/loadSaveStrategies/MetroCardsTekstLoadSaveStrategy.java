package model.database.loadSaveStrategies;

import model.MetroCard;
import model.database.utilities.TekstLoadSaveTemplate;

public class MetroCardsTekstLoadSaveStrategy extends TekstLoadSaveTemplate<Integer, MetroCard> implements LoadSaveStrategy<Integer, MetroCard> {


    @Override
    public MetroCard makeObject(String[] inputs) {
        Integer id = getKey(inputs);
        String[] monthYear = inputs[1].split("#");
        int month = Integer.parseInt(monthYear[0]);
        int year = Integer.parseInt(monthYear[1]);
        int aantalBeschikbaar = Integer.parseInt(inputs[2]);
        int aantalGebruikt = Integer.parseInt(inputs[3]);
        return new MetroCard(id, month, year, aantalBeschikbaar, aantalGebruikt);
    }

    @Override
    protected String format(MetroCard card) {
        return String.format("%d;%d#%d;%d;%d", card.getId(), card.getMaand(), card.getJaartal(), card.getAantalBeschikbaar(), card.getAantalGebruikt());
    }

    @Override
    protected String[] splitLine(String line) {
        return line.split(";");
    }
    @Override
    public Integer getKey(String[] tokens) {
        return Integer.parseInt(tokens[0]);
    }

}
