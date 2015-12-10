package model;

import java.util.ArrayList;
import fpt.com.Product;

@SuppressWarnings("serial")
public class ProductList extends ArrayList<Product> implements fpt.com.ProductList {
	// TODO variables ProductList

	@Override
	public boolean add(Product product) {
		return super.add(product);
	}

	@Override
	public boolean delete(fpt.com.Product product) {
		return super.remove(product);
	}

	@Override
	public int size() {
		return super.size();
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

}
