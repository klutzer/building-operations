package com.buildingapp.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.buildingapp.resources.BasicResponse;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		return Response.serverError()
				.entity(BasicResponse.withMessage(exception.getMessage()))
				.build();
	}

}
