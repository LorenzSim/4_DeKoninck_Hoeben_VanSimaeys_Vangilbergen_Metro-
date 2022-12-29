package view;

import controller.MetroStationViewController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MetroFacade;

import java.util.List;

public class MetroStationView {
	
	private final Stage stage = new Stage();
	private VBox gate1, gate2 ,gate3;

	public MetroStationView(MetroStationViewController controller){
		controller.setView(this);
		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(390);

		String cssLayout = "-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: grey;";

		Group root = new Group();
		HBox gateContainer = new HBox();
		gate1 = createGate(1, controller);
		gate1.setStyle(cssLayout);
		gate2 = createGate(2, controller);
		gate2.setStyle(cssLayout);
		gate3 = createGate(3, controller);
		gate3.setStyle(cssLayout);
		gateContainer.getChildren().addAll(gate1, gate2, gate3);
		gateContainer.setPadding(new Insets(20,30,20,20));
		gateContainer.setSpacing(20);
		root.getChildren().add(gateContainer);
		Scene scene = new Scene(root, 650, 300);			
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	private VBox createGate(int gateNumber, MetroStationViewController controller){
		VBox gate = new VBox();
		Text titel = new Text("GATE " + gateNumber);
		Label metroCardIdLabel = new Label("MetrocardId");
		ChoiceBox<Integer> idSelector = new ChoiceBox<>();
		Button scanMetroCardButton = new Button("Scan Metrocard");
		EventHandler<ActionEvent> scan = e -> {
			try {
				int metroCardId = idSelector.getValue();
				controller.scanMetroCard(metroCardId, gateNumber);
			}
			catch (NullPointerException ignored) {

			}

		};
		scanMetroCardButton.setOnAction(scan);

		Button walkThroughGateButton = new Button("Walk through gate");
		EventHandler<ActionEvent> walkThroughGate = e -> {
			controller.walkThroughGate(gateNumber);
		};
		walkThroughGateButton.setOnAction(walkThroughGate);
		Text lastAction = new Text("inactive");

		gate.getChildren().addAll(titel, metroCardIdLabel, idSelector, scanMetroCardButton, walkThroughGateButton, lastAction);
		return gate;
	}

	public void updateMetroCardIDList(List<Integer> IDs){
		((ChoiceBox<Integer>) gate1.getChildren().get(2)).setItems(FXCollections.observableArrayList(IDs));
		((ChoiceBox<Integer>) gate2.getChildren().get(2)).setItems(FXCollections.observableArrayList(IDs));
		((ChoiceBox<Integer>) gate3.getChildren().get(2)).setItems(FXCollections.observableArrayList(IDs));

	}

	public void setLastAction(int gateNumber, String actionMessage) {
		switch (gateNumber) {
			case 1:
				((Text) gate1.getChildren().get(5)).setText(actionMessage);
				break;
			case 2:
				((Text) gate2.getChildren().get(5)).setText(actionMessage);
				break;
			case 3:
				((Text) gate3.getChildren().get(5)).setText(actionMessage);
				break;
		}
	}

}
