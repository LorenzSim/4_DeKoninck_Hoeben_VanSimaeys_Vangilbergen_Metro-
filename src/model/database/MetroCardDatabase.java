package model.database;

import model.MetroCard;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MetroCardDatabase {

    private final Map<Integer, MetroCard> cards;
    public MetroCardDatabase(String filePath) {
        cards = new HashMap<>();
        load(filePath);
    }

    private void load (String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            Iterator<String> it = lines.iterator();
            String[] currentLine;
            while (it.hasNext()) {
                currentLine = it.next().split(";");
                Integer key = getKey(currentLine);
                MetroCard value = makeObject(currentLine);
                System.out.println(value);
                cards.put(key, value);
            }

        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public ArrayList<MetroCard> getCards() {
        return new ArrayList<>(cards.values());
    }

    public MetroCard makeObject(String[] inputs) {
        Integer id = getKey(inputs);
        String[] monthYear = inputs[1].split("#");
        int month = Integer.parseInt(monthYear[0]);
        int year = Integer.parseInt(monthYear[1]);
        int aantalBeschikbaar = Integer.parseInt(inputs[2]);
        int aantalGebruikt = Integer.parseInt(inputs[3]);
        return new MetroCard(id, month, year, aantalBeschikbaar, aantalGebruikt);
    }

    public Integer getKey(String[] tokens) {
        return Integer.parseInt(tokens[0]);
    }

}
