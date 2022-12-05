package view;

import application.MetroMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MetroCard;
import model.database.MetroCardDatabase;

public class MetroTicketView {
	private Stage stage = new Stage();
	private TableView<MetroCard> table;
	private ObservableList<MetroCard> cards;
	private MetroCardDatabase metroCardDatabase;
		
	public MetroTicketView(){
		table = new TableView<MetroCard>();
		refresh();
		TableColumn<MetroCard, Integer> month = new TableColumn<MetroCard, Integer>("maand");
		month.setMinWidth(150);
		month.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("maand"));
		TableColumn<MetroCard, Integer> year = new TableColumn<MetroCard, Integer>("jaartal");
		year.setMinWidth(150);
		year.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("jaartal"));
		TableColumn<MetroCard, Integer> aantalbeschikbaar = new TableColumn<MetroCard, Integer>("aantalBeschikbaar");
		aantalbeschikbaar.setMinWidth(150);
		aantalbeschikbaar.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("aantalBeschikbaar"));
		TableColumn<MetroCard, Integer> aantalgebruikt = new TableColumn<MetroCard, Integer>("aantalGebruikt");
		aantalgebruikt.setMinWidth(150);
		aantalgebruikt.setCellValueFactory(new PropertyValueFactory<MetroCard, Integer>("aantalGebruikt"));
		table.getColumns().addAll(month, year, aantalbeschikbaar, aantalgebruikt);



		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		Group root = new Group();
		root.getChildren().addAll(table);
		Scene scene = new Scene(root, 650, 350);			
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
	}

	public void refresh(){
		MetroCardDatabase metroCardDatabase = new MetroCardDatabase("/Users/lorenzvansimaeys/Dev/metro_ooo_pe/src/bestanden/metrocards.txt");
		cards = FXCollections.observableArrayList(metroCardDatabase.getCards());
		table.setItems(cards);
		table.refresh();
	}
}
