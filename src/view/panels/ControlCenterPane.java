package view.panels;

import controller.ControlCenterPaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.MetroFacade;

public class ControlCenterPane extends GridPane {

    public ControlCenterPane(ControlCenterPaneController controller) {

        Button button = new Button("Open metrostation");
        EventHandler<ActionEvent> event = e -> {
            controller.openMetroStation();
        };
        button.setOnAction(event);
        getChildren().add(button);
    }
}
