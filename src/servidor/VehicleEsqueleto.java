package servidor;

import java.io.IOException;

import models.Serializacao;
import models.Vehicle;

public class VehicleEsqueleto {
	
	Vehicle vehicle;
	Serializacao ser;

	public VehicleEsqueleto() {
		vehicle = new Vehicle(null, null, null, null, null, null);
		ser = new Serializacao();
		
	}

	public byte[] addVehicle(byte[] args) throws IOException {
		vehicle = (Vehicle)ser.deserializarGSON(Vehicle.class, args);
		String resultado = vehicle.addVehicle(vehicle);
		
		return resultado.getBytes();
	}
	
	
}
