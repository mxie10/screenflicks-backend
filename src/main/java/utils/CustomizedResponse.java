package utils;

public class CustomizedResponse<T> {

	private String message;
	private T response;
	
	public CustomizedResponse(String message, T response) {
		super();
		this.message = message;
		this.response = response;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	
}
