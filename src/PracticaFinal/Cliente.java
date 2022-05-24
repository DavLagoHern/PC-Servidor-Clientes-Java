package PracticaFinal;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import Controlador.Controlador;
import Informacion.*;
import Hilos.*;
import Mensajes.*;


/*NOMBRES:
 * 
 * DAVID LAGO HERNANDEZ
 * IGNACIO URRETAVIZCAYA TATO
 * 
 */

public class Cliente {
	private static final int numMaxClientes = 5;
	private static String nombreUsuario;
	private static int direccionIpMaquina;
	private static LockRompeEmpate consola;
	public static final int idControlador = 0;
	public static final int idOyente = 1;
	

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		String archivos[] = new String[numMaxClientes*2];
		
		consola = new LockRompeEmpate(2);
		try {
			System.out.println("Introduce el nombre de Usuario: ");
			nombreUsuario = scanner.nextLine();
			archivos = getArchivos();
			System.out.println("Introduce la dirección ip de la máquina: ");
			direccionIpMaquina = Integer.parseInt(scanner.nextLine());
			ServerSocket p2p = new ServerSocket(direccionIpMaquina);
			Usuario usuario = new Usuario(nombreUsuario, archivos, direccionIpMaquina, 2);

			Socket socket = new Socket("localhost", 999);
			
			ObjectOutputStream fOut = new ObjectOutputStream(socket.getOutputStream());
			Conexion conexion = new Conexion("conexion", nombreUsuario, "servidor", usuario);
			OyenteServidor oyenteServidor = new OyenteServidor(socket, fOut, usuario, consola, p2p);
			
			oyenteServidor.start();

			consola.takeLock(idOyente);
			fOut.writeObject(conexion);
			fOut.flush();
			
			
			Controlador controller = new Controlador(nombreUsuario, scanner, socket, fOut, consola);
			controller.run();
			
			


			scanner.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}
	public static String[] getArchivos() {
		String archivos[] = new String[numMaxClientes*2];
		switch(nombreUsuario) {
			case "a":
				archivos[0] = "10.txt";
				archivos[1] = "11.txt";
				break;
			case "b":
				archivos[0] = "20.txt";
				archivos[1] = "21.txt";
				break;
			case "c":
				archivos[0] = "30.txt";
				archivos[1] = "31.txt";
				break;
			case "d":
				archivos[0] = "40.txt";
				archivos[1] = "41.txt";
				break;
		}
		for(int i = 2; i < numMaxClientes*2; i++) {
			archivos[i] = "vacio";
		}
		return archivos;
	}

}
