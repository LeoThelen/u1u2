package main;

import controller.ControllerShop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelShop;
import model.ProductList;
import view.ViewCustomer;
import view.ViewShop;

/**
 * contains main method to initialize program
 */
public class Main extends Application {

	public static void main(String[] args) {
		// launchs start (just java things)
		launch(args);

	}

	@Override
	public void start(Stage sellersStage) {
		Stage customersStage = new Stage();

		ViewShop view = new ViewShop();
		ViewCustomer viewC = new ViewCustomer();

		ProductList p = new ProductList();
		ModelShop model = new ModelShop(p);

		ControllerShop cs = new ControllerShop();

		cs.link(model, view, viewC);

		Scene s1 = new Scene(view);
		Scene s2 = new Scene(viewC);

		sellersStage.setTitle("SellersView"); // Titelleisten
		customersStage.setTitle("CustomerView");

		sellersStage.setY(100); // Fensterpositionen
		sellersStage.setX(200);
		customersStage.setY(100);
		customersStage.setX(720);

		sellersStage.setScene(s1);
		customersStage.setScene(s2);

		sellersStage.show();
		customersStage.show();
	}

}