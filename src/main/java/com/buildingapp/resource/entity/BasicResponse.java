package com.buildingapp.resource.entity;

/**
 * @author erico.lutzer
 *
 */
public class BasicResponse {

	private String message;

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
