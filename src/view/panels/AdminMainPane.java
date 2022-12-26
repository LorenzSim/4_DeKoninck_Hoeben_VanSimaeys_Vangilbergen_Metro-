package view.panels;


import controller.ControlCenterPaneController;
import controller.MetroCardOverviewPaneController;
import controller.SetupPaneController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.MetroFacade;
import view.panels.ControlCenterPane;
import view.panels.MetroCardOverviewPane;
import view.panels.SetupPane;

public class AdminMainPane extends BorderPane {
    public AdminMainPane(ControlCenterPaneController controlCenterPaneController, MetroCardOverviewPaneController metroCardOverviewPaneController, SetupPaneController setupPaneController){
	    TabPane tabPane = new TabPane();

        ControlCenterPane controlCenterPane = new ControlCenterPane(controlCenterPaneController);
        SetupPane setupPane = new SetupPane(setupPaneController);
        MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane(metroCardOverviewPaneController);

        Tab metroCardOverviewTab = new Tab("Metro cards overview", metroCardOverviewPane);
        Tab controlCenterTab = new Tab("Control Center", controlCenterPane);
        Tab setupTab = new Tab("Setup", setupPane);

        tabPane.getTabs().add(controlCenterTab);
        tabPane.getTabs().add(metroCardOverviewTab);
        tabPane.getTabs().add(setupTab);

        this.setCenter(tabPane);
	}
}
