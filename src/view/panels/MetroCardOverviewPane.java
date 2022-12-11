package view.panels;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MetroCard;

import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;


public class MetroCardOverviewPane extends GridPane{
	private final MetrocardDatabase metrocardDatabase;
	private final TableView<MetroCard> table;

	public MetroCardOverviewPane() {
		metrocardDatabase = new MetrocardDatabase();
		metrocardDatabase.setLoadSaveStrategy(LoadSaveStrategyEnum.EXCEL);
		table = new TableView<>();

		TableColumn<MetroCard, Integer> month = new TableColumn<>("maand");
		month.setMinWidth(150);
		month.setCellValueFactory(new PropertyValueFactory<>("maand"));

		TableColumn<MetroCard, Integer> year = new TableColumn<>("jaartal");
		year.setMinWidth(150);
		year.setCellValueFactory(new PropertyValueFactory<>("jaartal"));

		TableColumn<MetroCard, Integer> aantalbeschikbaar = new TableColumn<>("Beschikbaar");
		aantalbeschikbaar.setMinWidth(150);
		aantalbeschikbaar.setCellValueFactory(new PropertyValueFactory<>("aantalBeschikbaar"));

		TableColumn<MetroCard, Integer> aantalgebruikt = new TableColumn<>("Gebruikt");
		aantalgebruikt.setMinWidth(150);
		aantalgebruikt.setCellValueFactory(new PropertyValueFactory<>("aantalGebruikt"));

		table.getColumns().addAll(month, year, aantalbeschikbaar, aantalgebruikt);
		getChildren().add(table);
		refresh();
	}

	public void displayMessage(String message){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Information ");
		alert.setContentText(message);
		alert.show();
	}

	public void refresh() {
		ObservableList<MetroCard> metroCards = FXCollections.observableArrayList(metrocardDatabase.getCards());
		table.setItems(metroCards);
		table.refresh();
	}


}
