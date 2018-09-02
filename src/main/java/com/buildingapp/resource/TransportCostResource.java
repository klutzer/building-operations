package com.buildingapp.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.buildingapp.data.TransportCost;
import com.buildingapp.resource.entity.ResultResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Custo do transporte")
@Path("custoTransporte")
public class TransportCostResource extends JsonResource {

	@POST
	@ApiOperation("Calcular custo do transporte")
	public ResultResponse calculateCost(TransportCost calcData) {
		return new ResultResponse(calcData.calculate());
	}
}
