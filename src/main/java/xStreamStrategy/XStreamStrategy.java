package xStreamStrategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import fpt.com.ExternalizableReflectionConverter;
import fpt.com.Product;

public class XStreamStrategy implements fpt.com.SerializableStrategy {
	private XStream xstream = new XStream(new DomDriver());
	int index = 0;
	
    ArrayList<Product> waren;
	private FileInputStream fi;
	private FileOutputStream fo;

	
	@Override
	public Product readObject() throws IOException {
		try{
		return waren.get(index++);
		}catch(IndexOutOfBoundsException e){
			return null;
		}
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		waren.add(obj);
	}

	@Override
	public void close() throws IOException {
		if (fi != null) {
			index = 0;
			fi.close();
		}
		if (fo != null) {
			xstream.toXML(waren,fo);
			fo.close();
		}

	}

	
	@Override
	public void open(InputStream input, OutputStream output) throws IOException {

		xstream.registerConverter(new ExternalizableReflectionConverter(xstream), XStream.PRIORITY_LOW);

		if (input != null) {
			xstream = new XStream(new DomDriver());
			fi = (FileInputStream) input;
			waren = null;
			waren= (ArrayList<Product>) xstream.fromXML(fi);
			
		}

		if (output != null) {
			xstream = new XStream(new DomDriver());
		}
	}

}
