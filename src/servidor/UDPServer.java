package servidor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import models.Message;
import models.Serializacao;

public class UDPServer {
	
	public static void main(String args[]) throws InterruptedException {
		byte[] requisicao;
		byte[] duplicada = null;

		try {

			int serverPort = 7896;
			DatagramSocket serverSocket = new DatagramSocket(serverPort);
			SurchParkDespachante sdp = new SurchParkDespachante();
			int oldId = -1;
			String oldIp = null;

			while (true) {
				
				Connection c = new Connection(serverSocket);
				requisicao = c.getRequest();
				Message message = c.desempacotaRequisicao(requisicao);
				int requestId = c.getRequestId(message.getId());
				
				String newIp = c.getIp();
				
				if (newIp.equals(oldIp) & requestId == oldId ) {
					System.out.println("\n\nMENSAGEM DUPLICADA!");
					System.out.println("\n--------------");
					System.out.println("\nIP NOVO: " + newIp);
					System.out.println("\nIP ANTIGO: " + oldIp);
					System.out.println("\nID DA QUISICAO: " + requestId);
					System.out.println("\nID ANTIGO: " + oldId);
					c.sendReply(duplicada);
			
				} else {
					byte[] resultado = sdp.selecionaEsqueleto(message);				
					byte[] resposta = c.empacotaResposta(resultado, requestId);
					oldId = requestId;
					oldIp = newIp;
					duplicada = resposta;
					c.sendReply(resposta);
					

				}
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		}
	}
}

class Connection extends Thread {
	DatagramSocket serverSocket;
	DatagramPacket packetIN;
	DatagramPacket packetOUT;
	InetAddress ipClient;
	int portClient;
	
	Serializacao ser = new Serializacao();
	
		
	public Connection(DatagramSocket aServerSocket) throws IOException {
		
		serverSocket = aServerSocket;
		
		System.out.println("CONECTADO a " + serverSocket.getInetAddress()+ ":" + serverSocket.getPort());
		this.start();
	}

	public byte[] getRequest() {
		try {
			byte[] requisicao = new byte[1024];
			packetIN = new DatagramPacket(requisicao, requisicao.length);
			serverSocket.receive(packetIN);
			ipClient = packetIN.getAddress();
			portClient = packetIN.getPort();
			
			return packetIN.getData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getRequestId(int _id) {
		int id = _id;
	 	return id;
	}

	public Message desempacotaRequisicao(byte[] array) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		Message message = (Message)ser.deserializarGSON(Message.class, array);
		return message;
	}

	public byte[] empacotaResposta(byte[] resultado, int requestId) throws IOException {
		
		Message message = new Message();
		message.setType(1);
		message.setId(requestId);
		message.setObjReference("null");
		message.setMethodId("null");
		message.setArguments(resultado);
		
	 	return ser.serializarGSON(message).getBytes();
	 }

	public void sendReply(byte[] resposta) {
		
		try {
			packetOUT = new DatagramPacket(resposta, resposta.length, ipClient, portClient);
			serverSocket.send(packetOUT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getIp() {
		String ip = ipClient.toString();
		System.out.print(ip);
		return ip;
	}

	public void run() {
	}
}
