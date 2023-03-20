package elections.model;

public class RegisteredCitizenException extends Exception {

	public RegisteredCitizenException(String message) {
		super(message);
	}

	public RegisteredCitizenException() {
		super("Citizen is already registered in system");
	}
}
