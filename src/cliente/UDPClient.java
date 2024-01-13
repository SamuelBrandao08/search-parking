package cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.naming.TimeLimitExceededException;

public class UDPClient {

	DatagramSocket socket = null;
	DatagramPacket packetIN = null;
	DatagramPacket packetOUT = null;
	InetAddress ip = null;
	private int port;

	public UDPClient(String serverIP, int port) {
		try {
			socket = new DatagramSocket();
			ip = InetAddress.getByName(serverIP);
			this.port = port;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendRequest(byte[] requisicao) {
		try {
			packetOUT = new DatagramPacket(requisicao, requisicao.length, ip, port);
			socket.send(packetOUT);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] getReply() throws TimeLimitExceededException, IOException, SocketTimeoutException{	
		socket.setSoTimeout(1000);
		byte[] resposta = new byte[4096];
		
		packetIN = new DatagramPacket(resposta, resposta.length);
		socket.receive(packetIN);
		return packetIN.getData();
	}

	public void finaliza() throws IOException {
		socket.close();
	}

}
