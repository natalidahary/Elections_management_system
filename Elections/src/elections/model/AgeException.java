package elections.model;

public class AgeException extends Exception{
	
	public AgeException(String message) {
		super(message);
	}

	public AgeException() {
		super("Must be at least 18 years old in order to vote");
	}
}
