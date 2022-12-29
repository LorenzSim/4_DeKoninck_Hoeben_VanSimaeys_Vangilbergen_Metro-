package model.database.loadSaveStrategies;

public enum LoadSaveStrategyEnum {
    TEKST("model.database.loadSaveStrategies.MetroCardsTekstLoadSaveStrategy"), EXCEL("model.database.loadSaveStrategies.MetroCardsExcelLoadSaveStrategy")
    ;
    final String classFileName;
    LoadSaveStrategyEnum(String classFileName) {
        this.classFileName = classFileName;
    }

    public String getClassFileName() {
        return classFileName;
    }
}
