package view.panels;

import controller.ControlCenterPaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ControlCenterPane extends GridPane {

    private Text amountOfSoldTickets = new Text("0"), totalPriceSold = new Text("0,0");

    public ControlCenterPane(ControlCenterPaneController controller) {
        controller.setView(this);

        Label amountOfTicketsSold = new Label("Number of sold tickets:");
        Label totalPriceSoldLabel = new Label("Total € amount of sold tickets:");

        Button openMetrostationButton = new Button("Open metrostation");
        EventHandler<ActionEvent> event = e -> {
            controller.openMetroStation();
            getChildren().remove(openMetrostationButton);
            getChildren().addAll(amountOfTicketsSold, amountOfSoldTickets, totalPriceSoldLabel, totalPriceSold);
        };

        amountOfTicketsSold.setTranslateX(20);
        amountOfSoldTickets.setTranslateX(500);

        totalPriceSoldLabel.setTranslateX(20);
        totalPriceSoldLabel.setTranslateY(50);
        totalPriceSold.setTranslateX(500);
        totalPriceSold.setTranslateY(50);

        openMetrostationButton.setOnAction(event);
        getChildren().addAll(openMetrostationButton);
    }

    public void updateNumberOfSoldTickets(int amount) {
        amountOfSoldTickets.setText(String.valueOf(amount));
    }
    public void upDateTotalPriceSold(double totalPrice){
        totalPriceSold.setText(String.format("%3.2f", totalPrice));
    }
}
