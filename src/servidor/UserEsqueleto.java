package servidor;

import java.io.IOException;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import models.Serializacao;
import models.User;

public class UserEsqueleto {
	
	User user;
	Serializacao ser;

	public UserEsqueleto() {
		user = new User(null, null);
		ser = new Serializacao();
	}

	public byte[] addUser(byte[] args) throws JsonSyntaxException, JsonIOException, IOException {

		user = (User)ser.deserializarGSON(User.class, args);
		String resultado = user.addUser(user);
		return resultado.getBytes();
	}
}
