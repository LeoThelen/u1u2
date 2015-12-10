package view;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ModelShop;
import fpt.com.Product;

/**
 * contains GUI
 */
public class ViewShop extends BorderPane {

	private TextField tfName = new TextField();
	private TextField tfPrice = new TextField();
	private TextField tfQuantity = new TextField();

	private Button bAdd = new Button("Add");
	private Button bDelete = new Button("Delete");
	private Button bSave = new Button("Save");
	private Button bLoad = new Button("Load");

	private Label lName = new Label("Name:");
	private Label lPrice = new Label("Price:");
	private Label lCount = new Label("Quantity:");

	ListView<fpt.com.Product> list = new ListView<>();

	private HBox strategyBox = new HBox();
	private HBox hBRightBottom = new HBox();
	private VBox vBox = new VBox();
	private HBox hboxRechts = new HBox();
	
	private ChoiceBox<String> strategyChoice = new ChoiceBox<String>();

	public ViewShop() {
		strategyBox.getChildren().addAll(strategyChoice,bLoad,bSave);
		hboxRechts.getChildren().addAll(vBox);

		vBox.getChildren().addAll(lName, tfName, lPrice, tfPrice, lCount, tfQuantity, hBRightBottom);

		hBRightBottom.getChildren().addAll(bAdd, bDelete);

		setRight(hboxRechts);
		setLeft(list);
		setBottom(strategyBox);

		strategyChoice.setTooltip(new Tooltip("Select the strategy."));
		strategyChoice.getItems().addAll("Binary","XML/Beans","XStreamStrategy");
		
		list.setCellFactory(e -> {
			ListCell<Product> cell = new ListCell<Product>() {
				@Override
				protected void updateItem(Product myObject, boolean b) {
					super.updateItem(myObject, myObject == null || b);
					if (myObject != null) {
						setText(myObject.getName() + " | " + myObject.getPrice() + " €  | " + myObject.getQuantity());
					} else { // wichtig da sonst der text stehen bleibt!
						setText("");
					}
				}
			};
			return cell;
		});
	}

	/**
	 * binds ModelShop data to ListView list
	 */
	public void bindData(ModelShop m) {
		list.setItems(m);
	}

	/**
	 * returns the listView
	 */
	public ListView<fpt.com.Product> getList() {
		return list;
	}
	
	public String getStrategy(){					//StrategyChoice
		return strategyChoice.getValue();
	}

	/**
	 * returns input of TextField tfName
	 */
	public String getName() {
		return tfName.getText();
	}

	/**
	 * returns input of TextField tfPrice
	 */
	public Double getPrice() {
		try {
			return Double.parseDouble(tfPrice.getText());
		} catch (java.lang.NumberFormatException e) {
			alertNumberFormat();
			// return -1 to differ from 0 because of NoQuantity or noPrice
			return -1d;
		}
	}

	/**
	 * returns input of TextField tfCount
	 */
	public int getQuantity() {
		try {
			return Integer.parseInt(tfQuantity.getText());
		} catch (java.lang.NumberFormatException e) {
			alertNumberFormat();
			// return -1 to differ from 0 because of NoQuantity or noPrice
			return -1;
		}
	}

	/**
	 * Opens alertDialog for NumberFormatError
	 */
	public void alertNumberFormat() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Wrong Number Format");
		alert.setHeaderText(null);
		alert.setContentText("Pleas insert a number as price and quantity");

		alert.showAndWait();
	}

	/**
	 * opens alertDialog for no Quantity or no Price
	 */
	public void alertNoPrice() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("No Quantity or Price");
		alert.setHeaderText(null);
		alert.setContentText("Please chose Price and Quantity");

		alert.showAndWait();
	}
	
	public void alertLoading() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("Error while loading");

		alert.showAndWait();
	}

	public void addbAdd(MyEventHandler eh) {
		bAdd.setOnAction(e -> {eh.doEvent();});
	}
	public void addbDelete(MyEventHandler eh) {
		bDelete.setOnAction(e -> {eh.doEvent();});
	}
	public void addbSave(MyEventHandler eh) {
		bSave.setOnAction(e -> {eh.doEvent();});
	}
	public void addbLoad(MyEventHandler eh) {
		bLoad.setOnAction(e -> {eh.doEvent();});
	}

	public void alertNoStrategy() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("No Strategy chosen");

		alert.showAndWait();
	}
	

	

}
