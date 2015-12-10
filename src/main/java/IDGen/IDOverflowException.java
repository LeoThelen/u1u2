package IDGen;

@SuppressWarnings("serial")
public class IDOverflowException extends Exception {
	public IDOverflowException(){
		super("All ID's are in use. You have to delete another product to create a new one.");
	}
}