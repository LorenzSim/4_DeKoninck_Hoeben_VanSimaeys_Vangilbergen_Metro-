package view;

import controller.MetroTicketViewController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;


public class MetroTicketView {
	private final Stage stage = new Stage();
	ChoiceBox<Integer> idSelector;

	public MetroTicketView(MetroTicketViewController controller){
		idSelector = new ChoiceBox<>();

		controller.setView(this);
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		Group root = new Group();
		Button newMetroCardButton = new Button("New metro card");
		TilePane newMetroCardTilePane = new TilePane();
		EventHandler<ActionEvent> event = e -> {
			controller.buyMetroCard();
		};
		newMetroCardButton.setOnAction(event);

		newMetroCardTilePane.getChildren().add(newMetroCardButton);
		newMetroCardTilePane.getChildren().add(idSelector);

		root.getChildren().add(newMetroCardTilePane);
		Scene scene = new Scene(root, 650, 350);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void updateMetroCardIDList(List<Integer> IDs){
		idSelector.setItems(FXCollections.observableArrayList(IDs));
	}

}