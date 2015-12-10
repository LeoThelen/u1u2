package binaryStrategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fpt.com.Product;
import fpt.com.SerializableStrategy;

public class BinaryStrategy implements SerializableStrategy {

	private FileInputStream fis;
	private FileOutputStream fos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	@Override
	public Product readObject() throws IOException {
		Product readObject = null;
		try {
			readObject = (Product)ois.readObject();
			System.out.println(readObject);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		catch (IOException e) {
			return null;
		}
		return readObject;
	}

	@Override
	public void writeObject(Product obj) throws IOException {
		try{
				oos.writeObject(obj); // write Object
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void close() throws IOException {
		if (oos != null)
			oos.close();
		if (ois != null)
			ois.close();
		if (fis != null)
			fis.close();
		if (fos != null)
			fos.close();
	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		if (input != null) {
			fis = (FileInputStream) input;
			ois = new ObjectInputStream(fis);
		}
		if (output != null) {
			fos = (FileOutputStream) output;
			oos = new ObjectOutputStream(fos);
		}
	}

}
