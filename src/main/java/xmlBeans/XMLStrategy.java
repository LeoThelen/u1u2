package xmlBeans;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fpt.com.Product;

public class XMLStrategy implements fpt.com.SerializableStrategy {

	private FileInputStream fis;
	private FileOutputStream fos;
	private XMLDecoder decoder;
	private XMLEncoder encoder;

	@Override
	public Product readObject() throws IOException {
		Product readObject = null;
		try {
			readObject = (Product) decoder.readObject();
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}

		return readObject;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		encoder.writeObject(obj);
		encoder.flush();
	}

	@Override
	public void close() throws IOException {
		if (encoder != null)
			encoder.close();
		if (decoder != null)
			decoder.close();
		if (fis != null)
			fis.close();
		if (fos != null)
			fos.close();
	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		if (input != null) {
			fis = (FileInputStream) input;
			decoder = new XMLDecoder(fis);
		}

		if (output != null) {
			fos = (FileOutputStream) output;
			encoder = new XMLEncoder(fos);
		}

	}

}
