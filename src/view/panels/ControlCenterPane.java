package view.panels;

import controller.ControlCenterPaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ControlCenterPane extends GridPane {

    private final Text amountOfSoldTickets = new Text("0"),totalPriceSold = new Text("0,0");
    private final VBox gate1, gate2, gate3;
    private final TextArea alerts;
    private final Background orangeBackground = new Background(new BackgroundFill(Color.ORANGE, null, null));
    private final Background whiteBackground = new Background(new BackgroundFill(Color.WHITE, null, null));
    Button closeMetrostationButton;


    public ControlCenterPane(ControlCenterPaneController controller) {
        controller.setView(this);

        VBox container = new VBox();


        Button openMetrostationButton = new Button("Open metrostation");
        EventHandler<ActionEvent> event = e -> {
            controller.openMetroStation();
            getChildren().remove(openMetrostationButton);
            getChildren().addAll(container);
        };
        openMetrostationButton.setOnAction(event);

        // blok 1
        VBox ticketMonitoring = new VBox();
        HBox soldTicketsBox = new HBox();
        soldTicketsBox.setSpacing(10);
        Label amountOfTicketsSoldLabel = new Label("Number of sold tickets:");
        soldTicketsBox.getChildren().addAll(amountOfTicketsSoldLabel, amountOfSoldTickets);

        HBox totalPriceTicketsBox = new HBox();
        totalPriceTicketsBox.setSpacing(10);
        Label totalPriceSoldLabel = new Label("Total € amount of sold tickets:");
        totalPriceTicketsBox.getChildren().addAll(totalPriceSoldLabel, totalPriceSold);
        ticketMonitoring.getChildren().addAll(soldTicketsBox, totalPriceTicketsBox);
        String cssLayout = "-fx-padding: 10;" +
        "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: grey;";
        ticketMonitoring.setStyle(cssLayout);

        // blok 2
        HBox gateMonitoring = new HBox();
        gate1 = createGate(1, controller);
        gate1.setStyle(cssLayout);
        gate2 = createGate(2, controller);
        gate2.setStyle(cssLayout);
        gate3 = createGate(3, controller);
        gate3.setStyle(cssLayout);
        gateMonitoring.getChildren().addAll(gate1, gate2, gate3);
        gateMonitoring.setStyle(cssLayout);

        // blok 3
        Label AlertText = new Label("Alerts");
        AlertText.setFont(new Font("Arial", 24));
        AlertText.setPadding(new Insets(0,20,0,30));
        HBox alertsBox = new HBox();
        alertsBox.setPadding(new Insets(10,20,10,20));
        alerts = new TextArea();
        alerts.setEditable(false);
        alerts.setScrollTop(Double.MAX_VALUE);
        alertsBox.getChildren().addAll(alerts);

        container.getChildren().addAll(ticketMonitoring, gateMonitoring,AlertText, alertsBox, closeMetrostationButton);
        getChildren().addAll(openMetrostationButton);
    }

    private VBox createGate(int gateNumber , ControlCenterPaneController controller) {
        VBox gate = new VBox();
        gate.setBackground(orangeBackground);
        Text title = new Text(String.format("GATE %d / INACTIVE", gateNumber));

        Button activateButton = new Button("Activate");
        EventHandler<ActionEvent> activateGate = event -> {
            controller.activate(gateNumber);
            gate.setBackground(whiteBackground);
            title.setText(String.format("GATE %d / ACTIVE", gateNumber));
        };
        activateButton.setOnAction(activateGate);

        Button deactivateButton =new Button("Deactivate");
        EventHandler<ActionEvent> deactivateGate = event -> controller.deactivate(gateNumber);
        deactivateButton.setOnAction(deactivateGate);

        Text scannedCardText = new Text("# scanned cards");
        Text scannedCards = new Text("0");
        gate.getChildren().addAll(title, activateButton, deactivateButton, scannedCardText,scannedCards);
        return gate;
    }


    public void updateScannedTickets(int gateNumber, int scannedTickets) {
        switch (gateNumber) {
            case 1:
                ((Text) gate1.getChildren().get(4)).setText(String.valueOf(scannedTickets));
                break;
            case 2:
                ((Text) gate2.getChildren().get(4)).setText(String.valueOf(scannedTickets));
                break;
            case 3:
                ((Text) gate3.getChildren().get(4)).setText(String.valueOf(scannedTickets));
                break;
        }
    }

    public void deactivateGate(int gate) {
        switch (gate) {
            case 1:
                gate1.setBackground(orangeBackground);
                ((Text) gate1.getChildren().get(0)).setText(String.format("GATE %d / INACTIVE", 1));
                break;
            case 2:
                gate2.setBackground(orangeBackground);
                ((Text) gate2.getChildren().get(0)).setText(String.format("GATE %d / INACTIVE", 1));
                break;
            case 3:
                gate3.setBackground(orangeBackground);
                ((Text) gate3.getChildren().get(0)).setText(String.format("GATE %d / INACTIVE", 1));
                break;
        }

    }

    public void updateNumberOfSoldTickets(int amount) {
        amountOfSoldTickets.setText(String.valueOf(amount));
    }
    public void upDateTotalPriceSold(double totalPrice){
        totalPriceSold.setText(String.format("%3.2f", totalPrice));
    }

    public void addAlert(String alert) {
        alerts.setText(alert + "\n" + alerts.getText());
    }
}
