package IDGen;

import model.ProductList;

public class IDGenerator {
	public long createID(ProductList productlist) throws IDOverflowException {
		for (long id = 1; id < 1000000; id++) {
			if (productlist.findProductById(id) == null)
				return id;
		}
		throw new IDOverflowException();
	}
}