package cz.prf.uai.tomsovsky.chat.configuration;

import java.util.ArrayList;
import java.util.List;

public class ChatValidationError {
	private List<ChatFieldError> fieldErrors = new ArrayList<ChatFieldError>();
	
	public ChatValidationError() {}
	
	public void addFieldError(String field, String message) {
		ChatFieldError error = new ChatFieldError(field, message);
		fieldErrors.add(error);
	}

	public List<ChatFieldError> getFieldErrors() {
		return fieldErrors;
	}
	public void setFieldErrors(List<ChatFieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
