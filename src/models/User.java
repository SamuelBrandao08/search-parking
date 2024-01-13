package models;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	String ownerId;
	String name;
  
	public User(String ownerId, String name){
		this.ownerId = ownerId;
		this.name = name;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
  
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String addUser(User user) throws IOException {
		final AtomicInteger count = new AtomicInteger(0); 
		String id = Integer.toString(count.incrementAndGet());
		setOwnerId(id);	
		setName(name);
		String resultado = user.getOwnerId();
		
		
		Writer writer = new FileWriter( "gson_user.json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
		String jsonUser = gson.toJson(user);
		writer.append(jsonUser);
		writer.flush();
		return resultado;
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		} catch (IOException e) {
			System.out.println("Erro ao criar o arquivo!");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.out.println("Erro ao criar o arquivo!");
				}
			}
		}
		return null;
	}

}
