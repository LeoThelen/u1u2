package model;

import java.util.ArrayList;
import fpt.com.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

@SuppressWarnings("serial")
public class Order extends ArrayList<Product> implements fpt.com.Order {
	private SimpleIntegerProperty quantity;
	private SimpleDoubleProperty sum;

	@Override
	public boolean add(Product e) {
		if (findProductById(e.getId()) != null) {
			// add quantity if prodct exist
			findProductById(e.getId()).setQuantity(findProductById(e.getId()).getQuantity() + e.getQuantity());
		}
		this.add(e);
		quantity.set(getQuantity() + e.getQuantity());
		return false;
	}

	@Override
	public boolean delete(Product p) {
		if (null != this.findProductByName(p.getName())) {
			this.delete(p);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Product findProductById(long id) {
		for (int i = 0; i < size(); i++) {
			if (get(i).getId() == id)
				return get(i);
		}
		return null;
	}

	@Override
	public Product findProductByName(String name) {
		for (int i = 0; i < size(); i++) {
			if (get(i).getName() == name)
				return get(i);
		}
		return null;
	}

	@Override
	public double getSum() {
		return sum.get();
	}

	@Override
	public int getQuantity() {
		return quantity.get();
	}

}
