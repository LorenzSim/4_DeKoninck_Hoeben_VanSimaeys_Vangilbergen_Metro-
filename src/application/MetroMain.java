package application;
	
import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MetroFacade;
import view.AdminView;
import view.MetroStationView;
import view.MetroTicketView;


public class MetroMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		MetroFacade metroFacade = new MetroFacade();

		ControlCenterPaneController controlCenterPaneController = new ControlCenterPaneController(metroFacade);
		MetroCardOverviewPaneController metroCardOverviewPaneController = new MetroCardOverviewPaneController(metroFacade);
		MetroStationViewController metroStationViewController = new MetroStationViewController(metroFacade);
		MetroTicketViewController metroTicketViewController = new MetroTicketViewController(metroFacade);
		SetupPaneController setupPaneController = new SetupPaneController(metroFacade);

		AdminView adminView = new AdminView(controlCenterPaneController, metroCardOverviewPaneController, setupPaneController);
		MetroTicketView metroTicketView = new MetroTicketView(metroTicketViewController);
		MetroStationView metroStationView = new MetroStationView(metroStationViewController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
