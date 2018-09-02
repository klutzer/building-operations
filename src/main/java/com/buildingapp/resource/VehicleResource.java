package com.buildingapp.resource;

import static com.buildingapp.RestApplication.get;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.buildingapp.data.Vehicle;
import com.buildingapp.repository.VehicleRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Veículos")
@Path("veiculo")
public class VehicleResource extends JsonResource {

	private VehicleRepository repository = get(VehicleRepository.class);

	@GET
	@ApiOperation("Listar todos os veículos existentes")
	public List<Vehicle> listAll() {
		return repository.listByExample(new Vehicle());
	}

	@POST
	@ApiOperation("Adicionar um veículo")
	public Vehicle add(Vehicle vehicle) {
		return repository.add(vehicle);
	}

	@PUT
	@ApiOperation("Altera um veículo")
	public Vehicle edit(Vehicle vehicle) {
		if (repository.update(vehicle) != null) {
			return repository.getById(vehicle);			
		}
		return null;
	}
}
