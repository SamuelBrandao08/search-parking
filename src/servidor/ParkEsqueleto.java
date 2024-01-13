package servidor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import models.Serializacao;
import models.Park;

public class ParkEsqueleto {
	
	Park park;
	Serializacao ser;

	public ParkEsqueleto() {
		park = new Park(0, null);
		ser = new Serializacao();
	}

	public byte[] listParkFree(byte[] args) throws IOException {
		List<Park> vagas = park.listParkFree();
		byte[] bytes = ser.serializarLista(vagas).getBytes();
		return bytes;
	}

	public byte[] listForLocal(byte[] args) throws IOException {
		String local = new String(args, StandardCharsets.UTF_8);
		System.out.println("LOCAL : " + local);
		List<Park> vagas = park.listForLocal(local);
		System.out.println("VAGA ENCONTRADAS  " + vagas);
		byte[] bytes = ser.serializarGSON(vagas).getBytes();
		return bytes;
	}
	
	public byte[] parking(byte[] args) throws IOException {
		int parkId = ByteBuffer.wrap(args).getInt();
		park = park.parking(parkId);
		byte[] bytes = ser.serializarGSON(park).getBytes();
		return bytes;
	}
}