package view.panels;

import controller.ControlCenterPaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ControlCenterPane extends GridPane {

    private Text numerOfSoldTickets = new Text("0"), totalAmount = new Text("0,0");

    public ControlCenterPane(ControlCenterPaneController controller) {
        controller.setView(this);

        Label numberOfSoldTicketsLabel = new Label("Number of sold tickets:");
        Label totalAmountLabel = new Label("Total € amount of sold tickets:");

        Button openMetrostationButton = new Button("Open metrostation");
        EventHandler<ActionEvent> event = e -> {
            controller.openMetroStation();
            getChildren().remove(openMetrostationButton);
            getChildren().addAll(numberOfSoldTicketsLabel, numerOfSoldTickets, totalAmountLabel, totalAmount);
        };

        numberOfSoldTicketsLabel.setTranslateX(20);
        numerOfSoldTickets.setTranslateX(500);

        totalAmountLabel.setTranslateX(20);
        totalAmountLabel.setTranslateY(50);
        totalAmount.setTranslateX(500);
        totalAmount.setTranslateY(50);

        openMetrostationButton.setOnAction(event);
        getChildren().addAll(openMetrostationButton);
    }

    public void updateNumberOfSoldTickets(int tickets) {
        int currentAmount = Integer.parseInt(numerOfSoldTickets.getText());
        currentAmount+=tickets;
        numerOfSoldTickets.setText(String.valueOf(currentAmount));
    }
    public void upDateTotalAmount(double amount){
        double currentAmount = Double.parseDouble(totalAmount.getText().replace(',','.'));
        currentAmount += amount;
        totalAmount.setText(String.format("%3.2f", currentAmount));
    }
}
