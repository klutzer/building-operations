package com.buildingapp.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.buildingapp.data.CustoTransporte;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Custo do transporte")
@Path("custoTransporte")
public class CustoTransporteResource {

	@Context
	private UriInfo info;

	@POST
	@ApiOperation("Calcular custo do transporte")
	public ResultadoResponse calcularCusto(CustoTransporte dadosCalculo) {
		return new ResultadoResponse(dadosCalculo.calcular());
	}
}
