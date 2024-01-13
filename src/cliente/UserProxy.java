package cliente;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import models.Serializacao;
import models.User;

public class UserProxy {
	
	UDPClient udpClient = new UDPClient("localhost", 7896);
	Serializacao ser = new Serializacao();
	Operation op = new Operation();

	public String addUser(User user) throws IOException {
		byte[] args = ser.serializarGSON(user).getBytes();
		byte[] bytes = op.doOperation("User", "addUser", args);
		
		return new String(bytes, StandardCharsets.UTF_8);
	}
	
	public String update(User user) throws IOException {
		byte[] args = ser.serializarGSON(user).getBytes();
		byte[] bytes = op.doOperation("User", "updateUser", args);
		return new String(bytes, StandardCharsets.UTF_8);
	}

}
