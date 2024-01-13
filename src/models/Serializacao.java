package models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Serializacao {
	
	Gson gson = new Gson();
	
	public String serializarGSON(Object obj) throws IOException {
		String json;
		gson = new GsonBuilder().setPrettyPrinting().create();
		json = gson.toJson(obj);
		
 		return json;
	}
	
	public Object deserializarGSON(Class<?> classe, byte[] message) throws FileNotFoundException {
		try {
			String json = new String(message, StandardCharsets.UTF_8);
			JsonReader reader = new JsonReader(new StringReader(json));
			reader.setLenient(true);
			return gson.fromJson(reader, classe);
		
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String serializarLista(List<Park> obj) {
	    String json = gson.toJson(obj);
		return json;
	}
	

	public List<Park> deserializarLista(byte[] message) {
		String json = new String(message, StandardCharsets.UTF_8);
		JsonReader reader = new JsonReader(new StringReader(json));
		reader.setLenient(true);

		List<Park> recvVagas = gson.fromJson(reader, new TypeToken<List<Park>>(){}.getType());
		
		return recvVagas;
	}

}
