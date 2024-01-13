package cliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import models.Serializacao;
import models.Park;

public class ParkProxy {
	
	UDPClient udpClient = new UDPClient("localhost", 7896);
	Serializacao ser = new Serializacao();
	Operation op = new Operation();

	public List<Park> listForLocal(String local) throws FileNotFoundException, IOException {
		byte[] args = local.getBytes();
		byte[] bytes = op.doOperation("Park", "listForLocal", args);
		List<Park> vagas = (List<Park>)ser.deserializarLista(bytes);
		return vagas;
	}
	
	public List<Park> listParkFree(byte[] args) throws IOException {
		byte[] bytes = op.doOperation("Park", "listParkFree", args);
		List<Park> vagas = (List<Park>)ser.deserializarLista(bytes);
		return vagas;
	}
	
	public Park parking(int parkId) throws IOException {
		byte[] args = ByteBuffer.allocate(4).putInt(parkId).array();
		byte[] bytes = op.doOperation("Park", "parking", args);
		Park park = (Park)ser.deserializarGSON(Park.class, bytes);
		return park;
	}
}
