package model.database.loadSaveStrategies;

import model.MetroCard;
import model.database.utilities.ExelLoadSaveTemplate;

import java.util.ArrayList;


public class MetroCardsExcelLoadSaveStrategy extends ExelLoadSaveTemplate<Integer, MetroCard> implements LoadSaveStrategy<Integer, MetroCard> {


    @Override
    public Integer getKey(ArrayList<String> tokens) {
        return Integer.parseInt(tokens.get(0));
    }

    @Override
    public MetroCard makeObject(ArrayList<String> inputs) {
        Integer id = getKey(inputs);
        String[] monthYear = inputs.get(1).split("#");
        int month = Integer.parseInt(monthYear[0]);
        int year = Integer.parseInt(monthYear[1]);
        int aantalBeschikbaar = Integer.parseInt(inputs.get(2));
        int aantalGebruikt = Integer.parseInt(inputs.get(3));
        return new MetroCard(id, month, year, aantalBeschikbaar, aantalGebruikt);

    }

    @Override
    protected ArrayList<String> format(MetroCard obj) {
        ArrayList<String> result = new ArrayList<>(4);
        result.add(Integer.toString(obj.getId()));
        result.add(String.format("%d#%d", obj.getMaand(), obj.getJaartal()));
        result.add(Integer.toString(obj.getAantalBeschikbaar()));
        result.add(Integer.toString(obj.getAantalGebruikt()));
        return result;
    }
}
