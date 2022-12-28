package view;

import controller.MetroTicketViewController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;


public class MetroTicketView {
	private final Stage stage = new Stage();
 	private final ChoiceBox<Integer> idSelector = new ChoiceBox<>();
 	private final TextField numberOfRidesField = new TextField();
	private Text newMetroCardMessage = new Text(), totalPrice = new Text(), discountMessage = new Text();
	private ToggleGroup ageSelectorGroup = new ToggleGroup();
	private RadioButton min26Button = new RadioButton("Younger than 26 years");
	private RadioButton plus64Button = new RadioButton("Older than 64 years");
	private RadioButton age26To64Button = new RadioButton("between 26 and 64 years");
 	private CheckBox studentCheckBox = new CheckBox("higher education student?");


	public MetroTicketView(MetroTicketViewController controller){
		controller.setView(this);
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);

		VBox container = new VBox();
		container.setPadding(new Insets(0,10,0,10));
		container.setSpacing(10);

		Button newMetroCardButton = new Button("New metro card");
		EventHandler<ActionEvent> event = e -> {
			controller.buyMetroCard();
			newMetroCardMessage.setText("Metro card price is € 15 - 2 free rides included");
		};
		newMetroCardButton.setOnAction(event);

		HBox selectMetroCardBox = new HBox();
		Label selectMetroCartText = new Label("Select metro card: ");
		selectMetroCardBox.getChildren().addAll(selectMetroCartText, idSelector);

		HBox numberOfRidesBox = new HBox();
		Label numberOfRidesText = new Label("Number of rides: ");
		numberOfRidesBox.getChildren().addAll(numberOfRidesText, numberOfRidesField);

		HBox selectAgeBox = new HBox();
		selectAgeBox.setSpacing(10);
		min26Button.setToggleGroup(ageSelectorGroup);
		plus64Button.setToggleGroup(ageSelectorGroup);
		age26To64Button.setToggleGroup(ageSelectorGroup);
		age26To64Button.setSelected(true);

		selectAgeBox.getChildren().addAll(min26Button, plus64Button, age26To64Button);

		Button addRidesButton = new Button("Add rides to cart");
		EventHandler<ActionEvent> addRidesEvent = e -> {
			int metroCardID = idSelector.getValue();
			int numberOfRides = Integer.parseInt(numberOfRidesField.getText());
			boolean isStudent = studentCheckBox.isSelected();
			boolean isYoungerThan26 = min26Button.isSelected();
			boolean isOlderThan64 = plus64Button.isSelected();
			controller.addRidesToCart(metroCardID, numberOfRides, isYoungerThan26, isStudent, isOlderThan64);
		};
		addRidesButton.setOnAction(addRidesEvent);

		HBox totalPriceBox =new HBox();
		Label totalPriceLabel = new Label("Total price: ");
		totalPriceBox.getChildren().addAll(totalPriceLabel, totalPrice);

		HBox requestBox =new HBox();
		Button confirmRequestButton = new Button("Confirm request");
		EventHandler<ActionEvent> confirmRequestEvent = e -> {
			int metroCardId = idSelector.getValue();
			int numberOfRides = Integer.parseInt(numberOfRidesField.getText());
			double price = Double.parseDouble(totalPrice.getText().split(" ")[1].replace(',', '.'));
			controller.buyMetroCardTickets(metroCardId, price, numberOfRides);
			resetView();
		};
		confirmRequestButton.setOnAction(confirmRequestEvent);
		Button cancelRequestButton = new Button("Cancel request");
		EventHandler<ActionEvent> cancelRequestEvent = e -> {
			resetView();
		};
		cancelRequestButton.setOnAction(cancelRequestEvent);
		requestBox.getChildren().addAll(confirmRequestButton, cancelRequestButton);

		container.getChildren().addAll(newMetroCardButton, newMetroCardMessage, selectMetroCardBox, numberOfRidesBox, studentCheckBox, selectAgeBox, addRidesButton, totalPriceBox, discountMessage, requestBox);

		Scene scene = new Scene(container, 650, 350);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		resetView();
	}

	public void updateMetroCardIDList(List<Integer> IDs){
		idSelector.setItems(FXCollections.observableArrayList(IDs));
	}
	public void setTotalPrice(String price) {
		totalPrice.setText(price);
	}
	public void setDiscountText(String priceText) {
		discountMessage.setText(priceText);
	}

	public void resetView() {
		newMetroCardMessage.setText("");
		discountMessage.setText("");
		idSelector.setValue(null);
		numberOfRidesField.setText("0");
		studentCheckBox.setSelected(false);
		min26Button.setSelected(false);
		plus64Button.setSelected(false);
		age26To64Button.setSelected(true);
		setTotalPrice("0");
	}

}