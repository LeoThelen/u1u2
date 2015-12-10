package xStreamStrategy;

import javafx.beans.property.SimpleLongProperty;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class SingleValueID implements SingleValueConverter {

	// Noch 000000 hinzufügen
	@Override
	public boolean canConvert(Class type) {
		return SimpleLongProperty.class.equals(type);
	}

	@Override
	public Object fromString(String arg0) {
		return (Object) new Long(arg0);
		
	}

	@Override
	public String toString(Object arg0) {
	return SimpleLongProperty.class.toString();
		
		
	}

}
