package view.panels;


import controller.SetupPaneController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.TicketPriceDecorator.TicketPriceDiscountEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;

import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;


public class SetupPane extends GridPane{

    private VBox discounts = new VBox();


    public SetupPane(SetupPaneController controller){
        controller.setView(this);
        discounts.setSpacing(10);

        HBox container = new HBox();
        container.setPadding(new Insets(10,0,0,10));
        container.setSpacing(50);

        VBox saveFileTypeBox = new VBox();
        saveFileTypeBox.setSpacing(10);

        Text selectLSS = new Text("Metrocard file type: ");
        ChoiceBox<String> lSSOptions = new ChoiceBox<>();
        initLoadSaveStrategyDropDown(lSSOptions);

        Button saveButton = new Button("Save file type");
        EventHandler<ActionEvent> event = e -> {
            controller.saveLoadSaveStrategy(LoadSaveStrategyEnum.valueOf(lSSOptions.getValue()));
            Text saved = new Text("Settings are saved!");
            saved.setTranslateX(20);
            saved.setTranslateY(150);
            getChildren().add(saved);
        };
        saveButton.setOnAction(event);
        saveFileTypeBox.getChildren().addAll(selectLSS, lSSOptions, saveButton);


        VBox discountBox = new VBox();
        discountBox.setSpacing(10);
        for (TicketPriceDiscountEnum discount : TicketPriceDiscountEnum.values()) {
            CheckBox checkBox = new CheckBox(discount.name());
            discounts.getChildren().add(checkBox);
        }
        Button discountButton = new Button("Save discount");
        EventHandler<ActionEvent> saveDiscounts = e -> {
            ArrayList<String> discounts = new ArrayList<>(this.discounts.getChildren().size());
            for (Node d : this.discounts.getChildren()) {
                if (((CheckBox) d).isSelected()) {
                    discounts.add(((CheckBox) d).getText());
                }
            }
            controller.savePriceDiscounts(discounts);
        };
        discountButton.setOnAction(saveDiscounts);
        discountBox.getChildren().addAll(discounts, discountButton);
        container.getChildren().addAll(saveFileTypeBox, discountBox);
        getChildren().addAll(container);

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
        for (Node discount : this.discounts.getChildren()) {
            if (discounts.contains(((CheckBox) discount).getText())) {
                 ((CheckBox) discount).setSelected(true);
            }
        }
    }
}
