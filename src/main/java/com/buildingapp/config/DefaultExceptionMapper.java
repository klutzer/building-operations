package com.buildingapp.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.buildingapp.resource.entity.BasicResponse;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

	@Override
	public Response toResponse(IllegalArgumentException exception) {
		return Response.status(Status.BAD_REQUEST)
				.entity(BasicResponse.withMessage(exception.getMessage()))
				.build();
	}

}
