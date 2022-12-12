package model.database.utilities;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public abstract class ExelLoadSaveTemplate<K extends Comparable<K>, V> {
    private static final String FILE_PATH = "src/bestanden/metrocards.xls";

    private final ExcelPlugin excelPlugin = new ExcelPlugin();

    public Map<K, V> load() {
        ArrayList<ArrayList<String>> info;
        Map<K, V> result = new TreeMap<>();
        try {
            info = excelPlugin.read(new File(FILE_PATH));
            for (ArrayList<String> row : info) {
                K key = getKey(row);
                V value = makeObject(row);
                result.put(key, value);
            }

        } catch (BiffException| IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
    public void save(Map<K, V> items) {
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        items.forEach((id, obj) -> rows.add(format(obj)));
        try {
            excelPlugin.write(new File(FILE_PATH), rows);
        } catch (WriteException |IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public abstract K getKey(ArrayList<String> tokens);
    public abstract V makeObject(ArrayList<String> inputs);
    protected abstract ArrayList<String> format(V obj);
}
