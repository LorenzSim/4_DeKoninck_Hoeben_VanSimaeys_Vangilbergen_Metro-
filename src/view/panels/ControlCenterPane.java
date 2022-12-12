package view.panels;

import controller.ControlCenterPaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControlCenterPane extends GridPane {
    ControlCenterPaneController controlCenterPaneController = new ControlCenterPaneController();

    public ControlCenterPane() {
        Button button = new Button("Open metrostation");
        EventHandler<ActionEvent> event = e -> {
            controlCenterPaneController.openMetroStation();
        };
        button.setOnAction(event);
        getChildren().add(button);
    }
}
