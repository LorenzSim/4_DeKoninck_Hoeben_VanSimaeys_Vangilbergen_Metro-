package view;

import controller.MetroStationViewController;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MetroFacade;

import java.util.List;

public class MetroStationView {
	
	private final Stage stage = new Stage();
	ChoiceBox<Integer> idSelector;


	public MetroStationView(MetroStationViewController controller){
		idSelector = new ChoiceBox<>();

		controller.setView(this);
		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(390);
		Group root = new Group();
		root.getChildren().add(idSelector);

		Scene scene = new Scene(root, 650, 300);			
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void updateMetroCardIDList(List<Integer> IDs){
		idSelector.setItems(FXCollections.observableArrayList(IDs));
	}
}
