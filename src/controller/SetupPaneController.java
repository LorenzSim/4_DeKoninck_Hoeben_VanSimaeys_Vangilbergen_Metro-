package controller;

import model.MetroFacade;
import model.Observer;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import view.panels.SetupPane;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class SetupPaneController {

    private final MetroFacade metroFacade;
    private SetupPane setupPane;

    public SetupPaneController(MetroFacade metroFacade) {
        this.metroFacade = metroFacade;
    }

    public void setView(SetupPane view) {
        setupPane = view;
    }

    public void saveSettings(LoadSaveStrategyEnum loadSaveStrategy) {
        Properties properties = new Properties();
        try {
            Path path = Paths.get("src/bestanden/settings.properties");
            InputStream is = Files.newInputStream(path);
            properties.load( is);
            is.close();

            properties.setProperty("loadSaveStrategy", loadSaveStrategy.toString());

            OutputStream os = Files.newOutputStream(path);
            properties.store(os, null);
            os.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
