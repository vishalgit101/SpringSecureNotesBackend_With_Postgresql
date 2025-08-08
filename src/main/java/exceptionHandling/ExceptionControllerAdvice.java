package exceptionHandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import model.ErrorDetails;

@RestControllerAdvice // for global exception handling
public class ExceptionControllerAdvice {

	@ExceptionHandler(UserAlreadyExists.class)
	public ResponseEntity<ErrorDetails> userAlreadyExistHandler(UserAlreadyExists ex){
		// can modify it to add some params for letting spiring AOP control and pass more information
		// to the method, like who the user was; see 265 on book
		System.out.println("Exception Controller Advice");
		return ResponseEntity.badRequest().body(new ErrorDetails(ex.getMessage()));
	}
	
}
