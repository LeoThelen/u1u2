package view;

import fpt.com.Product;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.ModelShop;

public class ViewCustomer extends BorderPane {

	private Button bBuy = new Button("Buy");

	private HBox hbox = new HBox();

	private TableView<Product> table = new TableView<Product>();

	TableColumn<fpt.com.Product, String> ColName = new TableColumn<fpt.com.Product, String>("Name");
	TableColumn<fpt.com.Product, Number> ColPrice = new TableColumn<fpt.com.Product, Number>("Price");
	TableColumn<fpt.com.Product, Number> ColQuantity = new TableColumn<fpt.com.Product, Number>("Quantity");

	private ListView<String> infolist = new ListView<String>();

	@SuppressWarnings("unchecked")
	public ViewCustomer() {
		ColName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		ColPrice.setCellValueFactory(new PropertyValueFactory<Product, Number>("price"));
		ColQuantity.setCellValueFactory(new PropertyValueFactory<Product, Number>("quantity"));

		table.getColumns().addAll(ColName, ColPrice, ColQuantity);
		hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.getChildren().add(bBuy);

		setLeft(infolist);
		setCenter(table);
		setBottom(hbox);
	}

	public void bindDataCustomer(ModelShop m) {
		table.setItems(m);
	}

	public Product getSelected() {
		if (table.getSelectionModel().getSelectedItem() != null)
			return table.getSelectionModel().getSelectedItem();
		return null;
	}
}
