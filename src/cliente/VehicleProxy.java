package cliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import models.Serializacao;
import models.Vehicle;

public class VehicleProxy {
	
	UDPClient udpClient = new UDPClient("localhost", 7896);
	Serializacao ser = new Serializacao();
	Operation op = new Operation();

	public String addVehicle(Vehicle vehicle) throws FileNotFoundException, IOException {
		byte[] args = ser.serializarGSON(vehicle).getBytes();
		byte[] bytes = op.doOperation("Vehicle", "addVehicle", args);		
			return new String(bytes, StandardCharsets.UTF_8);
	}
	
	public Vehicle listVehicleUser(String ownerId) throws FileNotFoundException, IOException {
		byte[] args = ownerId.getBytes();
		byte[] bytes = op.doOperation("Vehicle", "listVehicleUser", args);		
		Vehicle vehicle = (Vehicle)ser.deserializarGSON(Vehicle.class, bytes);	
			return vehicle;
	}

	public String removeVehicle(Vehicle vehicle) throws FileNotFoundException, IOException {
		byte[] args = ser.serializarGSON(vehicle).getBytes();
		byte[] bytes = op.doOperation("Vehicle", "removeVehicle", args);
			return new String(bytes, StandardCharsets.UTF_8);
	}
	
	public String updateVehicle(Vehicle vehicle) throws IOException {
		byte[] args = ser.serializarGSON(vehicle).getBytes();
		byte[] bytes = op.doOperation("Vehicle", "updateVehicle", args);
		return new String(bytes, StandardCharsets.UTF_8);
	}
}

