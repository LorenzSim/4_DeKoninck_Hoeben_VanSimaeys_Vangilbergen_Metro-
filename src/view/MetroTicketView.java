package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MetroTicketView {
	private Stage stage = new Stage();

	public MetroTicketView(){
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		Group root = new Group();
		Button newMetroCardButton = new Button("New metro card");
		TilePane newMetroCardTilePane = new TilePane();
		EventHandler<ActionEvent> event = e -> {

		};
		newMetroCardButton.setOnAction(event);
		newMetroCardTilePane.getChildren().add(newMetroCardButton);
		root.getChildren().add(newMetroCardTilePane);
		Scene scene = new Scene(root, 650, 350);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

}