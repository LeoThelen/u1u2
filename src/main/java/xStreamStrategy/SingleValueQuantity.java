package xStreamStrategy;



import javafx.beans.property.SimpleIntegerProperty;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class SingleValueQuantity implements SingleValueConverter{

	@Override
	public boolean canConvert(Class arg0) {
		return SimpleIntegerProperty.class.equals(arg0);
	}

	@Override
	public Object fromString(String arg0) {
		//return (Object) new SimpleIntegerProperty().getValue();
		return (Object) new SimpleIntegerProperty(Integer.parseInt(arg0));
	}

	@Override
	public String toString(Object arg0) {
		return SimpleIntegerProperty.class.toString();
	}

}
