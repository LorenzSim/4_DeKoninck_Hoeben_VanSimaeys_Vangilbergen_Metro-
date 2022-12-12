package model.database.utilities;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class TekstLoadSaveTemplate<K extends Comparable<K>,V>  {
    private static final String FILE_PATH = "src/bestanden/metrocards.txt";

    public Map<K, V> load () {
        Map<K, V> result = new TreeMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            String[] currentLine;

            for (String line : lines) {
                currentLine = splitLine(line);
                K key = getKey(currentLine);
                V value = makeObject(currentLine);
                result.put(key, value);
            }

        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    public void save (Map<K, V> items) {
        try {
            FileWriter fileWriter= new FileWriter(FILE_PATH);

            for(V object: items.values()){
                fileWriter.write(format(object)+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    protected abstract String[] splitLine(String line);
    public abstract K getKey(String[] tokens);
    public abstract V makeObject(String[] inputs);
    protected abstract String format(V obj);


}

