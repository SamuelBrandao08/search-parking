package cliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import javax.naming.TimeLimitExceededException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import models.Message;
import models.Serializacao;

public class Operation {
	static int requestId = 0;

	UDPClient udpClient = new UDPClient("localhost", 7896);
	Serializacao ser = new Serializacao();
	
	public byte[] doOperation(String objectRef, String method, byte[] args) throws IOException{
		Message resposta;
		int count = 3;
		

		byte[] data = empacotaMensagem(objectRef, method, args);
		udpClient.sendRequest(data);

		while (count >= 0) {
			try {

				resposta = desempacotaMensagem(udpClient.getReply());		
				return resposta.getArguments();

			} catch (SocketTimeoutException | TimeLimitExceededException e) {
				
				if (count != 0) {
					// reenvio

					udpClient.sendRequest(data);
					System.out.println("\n\nRETRANSMISSAO: " + count);
					count--;
					
				} else {
					System.out.print("SERVIDOR INDISPONIVEL");					
					udpClient.finaliza();
				}
			}	
		}
		return null;		
	}

	public void finaliza() {
		try {
			udpClient.finaliza();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] empacotaMensagem(String objectRef, String method, byte[] args) {

		requestId ++;
		System.out.print("ID DA MENSAGEM : " + requestId+ "\n");
		Message message = new Message();
		message.setType(0);
		message.setId(requestId);
		message.setObjReference(objectRef);
		message.setMethodId(method);
		message.setArguments(args);
		
			
		try {
			return ser.serializarGSON(message).getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Message desempacotaMensagem(byte[] resposta) throws JsonSyntaxException, JsonIOException {
		try {
			Message message = (Message)ser.deserializarGSON(Message.class, resposta);
			return message;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
