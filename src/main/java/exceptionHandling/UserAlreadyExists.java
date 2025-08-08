package exceptionHandling;

public class UserAlreadyExists extends RuntimeException {
	 public UserAlreadyExists(String message) {
	        super(message);
	    }
}
