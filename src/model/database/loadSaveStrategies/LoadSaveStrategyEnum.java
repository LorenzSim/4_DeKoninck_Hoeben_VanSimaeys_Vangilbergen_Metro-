package model.database.loadSaveStrategies;

public enum LoadSaveStrategyEnum {
    TEKST("MetroCardsTekstLoadSaveStrategy"), EXCEL("MetroCardsExcelLoadSaveStrategy")
    ;
    final String stringValue;
    LoadSaveStrategyEnum(String loadSaveStrategy) {
        this.stringValue = loadSaveStrategy;
    }

    public String getStringValue() {
        return stringValue;
    }
}
