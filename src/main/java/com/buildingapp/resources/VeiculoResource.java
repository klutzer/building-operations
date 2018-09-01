package com.buildingapp.resources;

import static com.buildingapp.RestApplication.get;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.buildingapp.data.Veiculo;
import com.buildingapp.repository.VeiculoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Veículos")
@Path("veiculo")
public class VeiculoResource extends BaseResource {

	private VeiculoRepository repository = get(VeiculoRepository.class);

	@GET
	@ApiOperation("Listar todos os veículos existentes")
	public List<Veiculo> listAll() {
		return repository.listByExample(new Veiculo());
	}

	@POST
	@ApiOperation("Adicionar um veículo")
	public Veiculo add(Veiculo veiculo) {
		return repository.add(veiculo);
	}
}
