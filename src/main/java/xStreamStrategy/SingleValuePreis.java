package xStreamStrategy;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class SingleValuePreis implements SingleValueConverter{

	//Noch Nachkommastelle
	@Override
	public boolean canConvert(Class arg0) {
		return SimpleDoubleProperty.class.equals(arg0);
	}

	@Override
	public Object fromString(String arg0) {
		//return (Object) new SimpleDoubleProperty().getValue();
		return new SimpleDoubleProperty(Double.parseDouble(arg0)); //Aus dem Stirng einen Double
	}

	@Override
	public String toString(Object arg0) {
		//return SimpleDoubleProperty.class.toString();
		double number = ((SimpleDoubleProperty)arg0).getValue();
	
		String temp = String.format("%3.2f", number); //2 Nachkommasellen
		return temp;
	}

}
