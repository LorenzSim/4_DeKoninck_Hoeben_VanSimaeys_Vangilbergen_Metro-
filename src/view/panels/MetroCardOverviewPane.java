package view.panels;


import controller.MetroCardOverviewPaneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MetroCard;

import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;


import java.util.List;


public class MetroCardOverviewPane extends GridPane{
	private ObservableList<MetroCard> metroCards;
	private final TableView<MetroCard> table;

	public MetroCardOverviewPane(MetroCardOverviewPaneController controller) {
		controller.setView(this);
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


	public void updateMetroCardList(List<MetroCard> cards) {
		metroCards = FXCollections.observableArrayList(cards);
		refresh();
	}

	private void refresh() {
		table.setItems(metroCards);
		table.refresh();
	}

}
