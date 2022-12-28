package view.panels;


import controller.SetupPaneController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.TicketPriceDecorator.TicketPriceDiscountEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;

import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;


public class SetupPane extends GridPane{

    private VBox discountVbox = new VBox();


    public SetupPane(SetupPaneController controller){
        controller.setView(this);

        Text selectLSS = new Text("Metrocard file type: ");
        selectLSS.setTranslateX(20);

        ChoiceBox<String> lSSOptions = new ChoiceBox<>();
        lSSOptions.setTranslateX(20);
        lSSOptions.setTranslateY(50);

        initLoadSaveStrategyDropDown(lSSOptions);

        Button saveButton = new Button("Save file type");
        saveButton.setTranslateX(20);
        saveButton.setTranslateY(100);

        EventHandler<ActionEvent> event = e -> {
            controller.saveLoadSaveStrategy(LoadSaveStrategyEnum.valueOf(lSSOptions.getValue()));
            Text saved = new Text("Settings are saved!");
            saved.setTranslateX(20);
            saved.setTranslateY(150);
            getChildren().add(saved);
        };
        saveButton.setOnAction(event);

        for (TicketPriceDiscountEnum discount : TicketPriceDiscountEnum.values()) {
            CheckBox checkBox = new CheckBox(discount.name());
            discountVbox.getChildren().add(checkBox);
        }

        discountVbox.setTranslateX(20);
        discountVbox.setTranslateY(220);

        Button discountButton = new Button("Save discount");
        discountButton.setTranslateX(20);
        discountButton.setTranslateY(discountVbox.getTranslateY() + 50);

        EventHandler<ActionEvent> saveDiscounts = e -> {
            ArrayList<String> discounts = new ArrayList<>(discountVbox.getChildren().size());
            for (Node d : discountVbox.getChildren()) {
                if (((CheckBox) d).isSelected()) {
                    discounts.add(((CheckBox) d).getText());
                }
            }
            controller.savePriceDiscounts(discounts);
        };

        discountButton.setOnAction(saveDiscounts);
        getChildren().addAll(selectLSS, lSSOptions, saveButton, discountVbox, discountButton);

    }

    private static void initLoadSaveStrategyDropDown(ChoiceBox<String> lSSOptions) {
        LoadSaveStrategyEnum[] strategyEnums = LoadSaveStrategyEnum.values();
        ArrayList<String> strategyStrings = new ArrayList<>(strategyEnums.length);
        for (LoadSaveStrategyEnum strategyEnum : strategyEnums) {
            strategyStrings.add(strategyEnum.toString());
        }
        lSSOptions.getItems().setAll(strategyStrings);
    }

    public void initDiscounts(List<String> discounts){
        for (Node discount : discountVbox.getChildren()) {
            if (discounts.contains(((CheckBox) discount).getText())) {
                 ((CheckBox) discount).setSelected(true);
            }
        }
    }
}
