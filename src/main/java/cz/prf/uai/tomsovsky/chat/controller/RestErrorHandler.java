package cz.prf.uai.tomsovsky.chat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cz.prf.uai.tomsovsky.chat.configuration.ChatValidationError;

@ControllerAdvice
public class RestErrorHandler {
	@ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ChatValidationError processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }
	
	private ChatValidationError processFieldErrors(List<FieldError> fieldErrors) {
		ChatValidationError chatValidationError = new ChatValidationError();
		
		for (FieldError fieldError : fieldErrors) {
			chatValidationError.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return chatValidationError;
	}
}
