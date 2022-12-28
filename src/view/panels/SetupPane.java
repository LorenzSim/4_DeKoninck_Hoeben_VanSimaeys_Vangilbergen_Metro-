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
import model.TicketPriceDecorator.TicketPriceDiscountEnum;

import javafx.event.EventHandler;

import java.util.ArrayList;


public class SetupPane extends GridPane{

    public SetupPane(SetupPaneController controller){

        Text selectLSS = new Text("Metrocard file type: ");
        selectLSS.setTranslateX(20);

        ChoiceBox<String> lSSOptions = new ChoiceBox<>();
        lSSOptions.setValue("EXCEL");
        lSSOptions.setTranslateX(20);
        lSSOptions.setTranslateY(50);
        lSSOptions.getItems().setAll("EXCEL", "TEKST");

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

        VBox vbox = new VBox();

        for (TicketPriceDiscountEnum discount : TicketPriceDiscountEnum.values()) {
            CheckBox checkBox = new CheckBox(discount.name());
            vbox.getChildren().add(checkBox);
        }
        vbox.setTranslateX(20);
        vbox.setTranslateY(220);

        Button discountButton = new Button("Save discount");
        discountButton.setTranslateX(20);
        discountButton.setTranslateY(vbox.getTranslateY() + 50);

        EventHandler<ActionEvent> saveDiscounts = e -> {
            ArrayList<String> discounts = new ArrayList<>(vbox.getChildren().size());
            for (Node d : vbox.getChildren()) {
                if (((CheckBox) d).isSelected()) {
                    discounts.add(((CheckBox) d).getText());
                }
            }
            controller.savePriceDiscounts(discounts);
        };

        discountButton.setOnAction(saveDiscounts);
        getChildren().addAll(selectLSS, lSSOptions, saveButton, vbox, discountButton);

    }
}
