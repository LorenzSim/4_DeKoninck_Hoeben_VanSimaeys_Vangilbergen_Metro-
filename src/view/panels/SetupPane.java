package view.panels;


import controller.SetupPaneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;

import javafx.event.EventHandler;



public class SetupPane extends GridPane{

    public SetupPane(SetupPaneController controller){

        Text selectLSS = new Text("Metrocard file type: ");
        selectLSS.setTranslateX(20);

        ChoiceBox<String> lSSOptions = new ChoiceBox<>();
        lSSOptions.setTranslateX(20);
        lSSOptions.setTranslateY(50);
        lSSOptions.getItems().setAll("EXCEL", "TEKST");

        Button saveButton = new Button("Save");
        saveButton.setTranslateX(20);
        saveButton.setTranslateY(100);

        EventHandler<ActionEvent> event = e -> {
            controller.saveSettings(LoadSaveStrategyEnum.valueOf(lSSOptions.getValue()));
            Text saved = new Text("Settings are saved!");
            saved.setTranslateX(20);
            saved.setTranslateY(150);
            getChildren().add(saved);
        };
        saveButton.setOnAction(event);

        getChildren().addAll(selectLSS, lSSOptions, saveButton);

    }
}
