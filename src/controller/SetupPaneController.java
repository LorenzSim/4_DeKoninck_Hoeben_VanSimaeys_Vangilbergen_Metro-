package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import model.Settings;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import view.panels.SetupPane;

import java.util.ArrayList;


public class SetupPaneController implements Observer {

    private final MetroFacade metroFacade;
    private SetupPane setupPane;

    public SetupPaneController(MetroFacade metroFacade) {
        this.metroFacade = metroFacade;
        this.metroFacade.addObserver(MetroEventsEnum.OPEN_METROSTATION, this);
    }

    public void setView(SetupPane view) {
        setupPane = view;
    }

    public void saveLoadSaveStrategy(LoadSaveStrategyEnum loadSaveStrategy) {
        Settings.setProperty("loadSaveStrategy", loadSaveStrategy.toString());
    }

    public void savePriceDiscounts(ArrayList<String> priceDiscounts) {
        Settings.setProperty("PRICEDISCOUNTS", String.join(",", priceDiscounts));
    }

    @Override
    public void update() {
        setupPane.initDiscounts(metroFacade.getMetroTicketDiscountList());
    }
}
