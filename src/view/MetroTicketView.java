package view;

import com.sun.javafx.scene.control.skin.IntegerFieldSkin;
import controller.MetroTicketViewController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.TicketPriceDecorator.TicketPrice;

import java.util.List;


public class MetroTicketView {
	private final Stage stage = new Stage();
	ChoiceBox<Integer> idSelector;
	private Text totalPrice, newMetroCardMessage, totalPriceMessage;

	public MetroTicketView(MetroTicketViewController controller){
		idSelector = new ChoiceBox<>();
		totalPrice = new Text();
		newMetroCardMessage = new Text();
		totalPriceMessage = new Text();

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
			newMetroCardMessage.setText("Metro card price is € 15 - 2 free rides included");
		};

		newMetroCardButton.setOnAction(event);
		Label selectMetroCartText = new Label("Select metro card: ");

		Label numberOfRidesText = new Label("Number of rides: ");
		TextField numberOfRidesField = new TextField();

		numberOfRidesText.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				numberOfRidesText.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});

		CheckBox studentCheckBox = new CheckBox("higher education student?");

		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton youngerThan26RadioButton = new RadioButton("younger than 26 years");
		youngerThan26RadioButton.setToggleGroup(toggleGroup);
		youngerThan26RadioButton.setSelected(true);
		RadioButton olderThan64RadioButton = new RadioButton("older than 64 years");
		olderThan64RadioButton.setToggleGroup(toggleGroup);
		RadioButton between26And64RadioButton = new RadioButton("between 26 and 64 years");
		between26And64RadioButton.setToggleGroup(toggleGroup);

		Button addRidesButton = new Button("Add rides to cart");
		Label totalPriceLabel = new Label("Total price: ");
		EventHandler<ActionEvent> addRidesEvent = e -> {
			int metroCardID = idSelector.getValue();
			int numberOfRides = Integer.parseInt(numberOfRidesField.getText());
			boolean isStudent = studentCheckBox.isSelected();
			boolean isYoungerThan26 = youngerThan26RadioButton.isSelected();
			boolean isOlderThan64 = olderThan64RadioButton.isSelected();
		 	TicketPrice price = controller.calculateTotalPrice(metroCardID, numberOfRides, isYoungerThan26, isStudent, isOlderThan64);
			setTotalPrice(String.format("€ %2.2f", price.getPrice() * numberOfRides));
			totalPriceMessage.setText(price.getPriceText());
		};
		addRidesButton.setOnAction(addRidesEvent);


		Button confirmRequest = new Button("Confirm request");
		Button cancelRequest = new Button("Cancel request");

		newMetroCardTilePane.getChildren().addAll(newMetroCardButton, newMetroCardMessage, selectMetroCartText, idSelector, numberOfRidesText, numberOfRidesField, studentCheckBox, youngerThan26RadioButton, olderThan64RadioButton, between26And64RadioButton, addRidesButton, totalPriceLabel, totalPrice, totalPriceMessage, confirmRequest, cancelRequest);
		newMetroCardTilePane.setTileAlignment(Pos.TOP_LEFT);
		newMetroCardTilePane.setOrientation(Orientation.VERTICAL);
		newMetroCardTilePane.setHgap(10);
		newMetroCardTilePane.setVgap(10);

		root.getChildren().addAll(newMetroCardTilePane);
		Scene scene = new Scene(root, 650, 350);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void updateMetroCardIDList(List<Integer> IDs){
		idSelector.setItems(FXCollections.observableArrayList(IDs));
	}
	public void setTotalPrice(String price) {
		totalPrice.setText(price);
	}

}