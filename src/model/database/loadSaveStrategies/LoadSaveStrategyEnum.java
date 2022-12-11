package model.database.loadSaveStrategies;

public enum LoadSaveStrategyEnum {
    TEKST("MetroCardsTekstLoadSaveStrategy"), EXCEL("MetroCardsExcelLoadSaveStrategy")
    ;
    final String stringValue;
    LoadSaveStrategyEnum(String loadSaveStrategy) {
        this.stringValue = loadSaveStrategy;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
