package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import models.Park;
import models.User;
import models.Vehicle;

public class SurchParkClient {
	UserProxy userProxy;
	ParkProxy parkProxy;
	VehicleProxy vehicleProxy;
	Operation op;
	Scanner scanner = new Scanner(System.in);

	List<Park> vagas = new ArrayList<Park>();
	String text = "NULL MESSAGE";
	byte[] teste = text.getBytes();

	public SurchParkClient() {
		userProxy = new UserProxy();
		parkProxy = new ParkProxy();
		vehicleProxy = new VehicleProxy();
		op = new Operation();
	}

	public int selecionaOperacao() throws IOException {

		int operacao = 0;

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String opt = null;

		do {
			
			opt = scanner.nextLine();

		} while (opt.equals("\n") || opt.equals("") || opt.isEmpty());

		operacao = Integer.parseInt(opt);

		Park park = new Park(0, null);
		Vehicle vehicle = new Vehicle(null, null, null, null, null, null);	

		switch (operacao) {

		case 1:
			
			//ARGUMENTO VAZIO PARA PASSAR NO REFLECTION
			vagas = parkProxy.listParkFree(teste);
			
			
			System.out.println("VAGAS DISPONÍVEIS :\n");
			for (Park p : vagas) {
				System.out.print(p.getId() + " | " + p.getLocal() + " | ");
				if(p.getOcuped()){
					System.out.println("ocupada");
				} else {
					System.out.println("livre");
				}
			}
			break;

		case 2:
			System.out.println("DIGITE O NOME DO LOCAL DA VAGA:");
			String local = scanner.nextLine();
			vagas = parkProxy.listForLocal(local);
			
			System.out.println(vagas);

			if(!vagas.isEmpty()){
				System.out.println("VAGAS DISPONÍVEIS :\n");
				for (Park p : vagas) {
					System.out.print(p.getId() + " | " + p.getLocal() + " | ");
					if(p.getOcuped()){
						System.out.println("ocupada");
					} else {
						System.out.println("livre");
					}
				}	
			} else {
				System.out.println("\nVAGA NAO ENCONTRADA!");
			}
			
			break;


		case 3:
			System.out.println("Digite o id da vaga: ");
			int parkId = scanner.nextInt();
			System.out.println("VAGA DESEJADA: " + parkId);
			park = parkProxy.parking(parkId);
			System.out.println("\n\n\n");
			System.out.println("Carro estacionado na vaga " + park.getId() + ", rua " + park.getLocal());
			break;
		
		case 4:
			final AtomicInteger count = new AtomicInteger(0);
			String id = Integer.toString(count.incrementAndGet());
			
			System.out.println("Id do veículo: ");
			vehicle.setId(id);
			System.out.println("Modelo do veículo: ");
			vehicle.setModel(stdin.readLine());
			System.out.println("Marca do veículo: ");
			vehicle.setBrand(stdin.readLine());
			System.out.println("Cor do veículo: ");
			vehicle.setColor(stdin.readLine());
			System.out.println("Ano do veículo: ");
			vehicle.setYear(stdin.readLine());
			String resultado = vehicleProxy.addVehicle(vehicle);
			System.out.println("\n\n" + resultado);
			break;	

		case 0:
			op.finaliza();
			
			break;

		default:
			System.out.println("Opera��o invalida, tente outra.");
			break;
		}
		return operacao;
	}
	
	public String menuUser() throws IOException {
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			User user = new User("1", null);
			System.out.println("Digite o nome de usuario: ");
			user.setName(stdin.readLine());
			System.out.println("\n\n\n");

			String resultado = userProxy.addUser(user);

			if (resultado != null) {
				System.out.print("\nUsuario cadastrado com Sucesso!");
				System.out.print("\nCod: " + resultado);
				System.out.println("\n\n\n");
				System.out.println("Bem vindo!   " + user.getName());
				return resultado;	 
			} else {
				System.out.print("Não foi possivel realizar a operacao!");
			}
			return null;
	}
	

	public void printMenu() {
		System.out.println("\nDigite o número da operação que deseja executar: ");
		System.out.println("1 - Listar vagas livres");
		System.out.println("2 - Listar vagas por local");
		System.out.println("3 - Estacionar");
		System.out.println("4 - Cadastrar veículo");
		System.out.println("0 - Sair\n");
	}

	public static void main(String[] args) throws IOException {
		SurchParkClient client = new SurchParkClient();
		int operacao = -1;
		client.menuUser();
		do {
			client.printMenu();
			try {
				operacao = client.selecionaOperacao();
			} catch (IOException ex) {
				System.out.println("Escolha uma das operações pelo número");
			}
		} while (operacao != 0);
	}
}
