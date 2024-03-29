package view;

import controller.ControlCenterPaneController;
import controller.MetroCardOverviewPaneController;
import controller.SetupPaneController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.panels.AdminMainPane;

public class AdminView {
	private Stage stage = new Stage();		
		
	public AdminView(ControlCenterPaneController controlCenterPaneController, MetroCardOverviewPaneController metroCardOverviewPaneController, SetupPaneController setupPaneController) {
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(660);
		stage.setY(5);
		Group root = new Group();
		Scene scene = new Scene(root, 690, 680);
		BorderPane borderPane = new AdminMainPane(controlCenterPaneController, metroCardOverviewPaneController, setupPaneController);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
