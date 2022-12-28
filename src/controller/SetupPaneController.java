package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import model.TicketPriceDecorator.TicketPriceDiscountEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import view.panels.SetupPane;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SetupPaneController implements Observer {

    private final MetroFacade metroFacade;
    private SetupPane setupPane;

    public SetupPaneController(MetroFacade metroFacade) {
        this.metroFacade = metroFacade;
        this.metroFacade.attach(MetroEventsEnum.OPEN_METROSTATION, this);
    }

    public void setView(SetupPane view) {
        setupPane = view;
    }

    public void saveLoadSaveStrategy(LoadSaveStrategyEnum loadSaveStrategy) {
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

    public void savePriceDiscounts(ArrayList<String> priceDiscounts) {
        Properties properties = new Properties();
        try {
            Path path = Paths.get("src/bestanden/settings.properties");

            InputStream is = Files.newInputStream(path);
            properties.load( is);
            is.close();

            properties.setProperty("PRICEDISCOUNTS", String.join(",", priceDiscounts));

            OutputStream os = Files.newOutputStream(path);
            properties.store(os, null);
            os.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        setupPane.initDiscounts(metroFacade.getMetroTicketDiscountList());
    }
}
