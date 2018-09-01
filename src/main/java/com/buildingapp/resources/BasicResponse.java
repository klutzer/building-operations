package com.buildingapp.resources;

/**
 * @author erico.lutzer
 *
 */
public class BasicResponse {

	private String message;

	public BasicResponse() {
	}

	public static BasicResponse withMessage(String message) {
		BasicResponse response = new BasicResponse();
		response.setMessage(message);
		return response;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
