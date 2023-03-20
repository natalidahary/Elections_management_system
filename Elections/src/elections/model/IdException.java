package elections.model;

public class IdException extends Exception {

	public IdException(String message) {
		super(message);
	}

	public IdException() {
		super("ID has to contain 9 digits");
	}
	
}
