package model;

import javafx.collections.ModifiableObservableListBase;
import fpt.com.Product;

@SuppressWarnings("serial")
public class ModelShop extends ModifiableObservableListBase<Product> implements fpt.com.ProductList {

	private ProductList productList;

	public ModelShop(ProductList productList) {
		this.productList = productList;
	}

	@Override
	protected void doAdd(int arg0, Product product) {
		productList.add(product);
	}

	@Override
	protected Product doSet(int index, Product element) {
		return productList.set(index, element);
	}

	@Override
	protected Product doRemove(int index) {
		return productList.remove(index);
	}

	@Override
	public Product findProductById(long id) {
		return productList.findProductById(id);
	}

	@Override
	public Product findProductByName(String name) {
		return productList.findProductByName(name);
	}

	@Override
	public Product get(int index) {
		return productList.get(index);
	}

	@Override
	public int size() {
		return productList.size();
	}

	@Override
	public boolean delete(Product product) {
		return remove(product);
	}

	public void addString(Product p) {
		this.add(p);
	}

	public ProductList getList() {
		return productList;
	}

}
