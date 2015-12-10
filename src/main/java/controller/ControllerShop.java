package controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import IDGen.IDGenerator;
import IDGen.IDOverflowException;
import binaryStrategy.BinaryStrategy;
import fpt.com.SerializableStrategy;
import model.ModelShop;
import model.Product;
import view.ViewCustomer;
import view.ViewShop;
import xStreamStrategy.XStreamStrategy;
import xmlBeans.XMLStrategy;

public class ControllerShop {

	private ModelShop model;
	private ViewShop view;
	private ViewCustomer viewC;

	String path = "";
	private SerializableStrategy strategy;

	public void link(ModelShop model, ViewShop view, ViewCustomer viewC) {

		this.model = model;
		this.view = view;
		this.viewC = viewC;
		this.view.bindData(this.model);
		this.viewC.bindDataCustomer(this.model);
		view.getList().setItems(model);
		addListener();

	}

	private void addListener() {
		view.addbAdd(() -> {
			this.add();
		});
		view.addbDelete(() -> {
			this.delete();
		});
		view.addbSave(() -> {
			this.save();
		});
		view.addbLoad(() -> {
			this.load();
		});

	}

	private void load() {
		model.clear();
		strategy = getStrategy();
		if (strategy == null)
			return;
		this.loadingStrategy(path);
	}

	private void save() {
		strategy = getStrategy();
		if (strategy == null)
			return;
		this.savingStrategy(path);
	}

	public void add() {
		try {
			String name = view.getName();
			double price = view.getPrice();
			int quantity = view.getQuantity();

			if (price > 0) {
				IDGenerator idGen = new IDGenerator();
				long id = idGen.createID(model.getList());

				model.add(new Product(name, price, quantity, id));

			} else if (price == 0)
				view.alertNoPrice();
		} catch (IDOverflowException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		model.delete(view.getList().getSelectionModel().getSelectedItem());
	}

	private SerializableStrategy getStrategy() throws NullPointerException {
		try {
			if (view.getStrategy().equals("Binary")) {
				path = "products.ser";
				return strategy = new BinaryStrategy();
			}
			if (view.getStrategy().equals("xStream")) {
				path = "xproducts.xml";
				return strategy = new XStreamStrategy();
			}
			if (view.getStrategy().equals("XML/Beans")) {
				path = "products.xml";
				return strategy = new XMLStrategy();

			}
		} catch (NullPointerException e) {
			view.alertNoStrategy();
		}
		return null;
	}

	private void savingStrategy(String path) {
		try {
			strategy.open(null, new FileOutputStream(path));
			for (int i = 0; i < model.getList().size(); i++) {
				strategy.writeObject(model.getList().get(i));
			}
		} catch (EOFException e) {
		} catch (IOException e) {

			e.printStackTrace();
		}

		finally {
			try {
				strategy.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadingStrategy(String path) {
		try {
			strategy.open(new FileInputStream(path), null);
			Product nextObject = null;
			while ((nextObject = (Product) strategy.readObject()) != null) {
				model.add(nextObject);
			}
		} catch (IOException e) {
			view.alertLoading();
		} finally {
			try {
				strategy.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
