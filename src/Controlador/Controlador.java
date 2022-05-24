package Controlador;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

import Informacion.LockRompeEmpate;
import Mensajes.*;
import PracticaFinal.Cliente;

public class Controlador {
	String name;
	Scanner scanner;
	Menu menu;
	Socket s;
	ObjectOutputStream fOut;
	LockRompeEmpate consola;
	
	
	public Controlador(String name, Scanner scanner, Socket s, ObjectOutputStream fOut, LockRompeEmpate consola) {
		this.name = name;
		this.scanner = scanner;
		this.s = s;
		this.fOut = fOut;
		this.consola = consola;
		menu = new Menu(name, scanner);
	}
	
	public void run(){
		int accion = 0;
		try {
			Mensaje mensaje;
			while(accion != 3) {
				consola.takeLock(Cliente.idControlador);
				accion = menu.show();
				mensaje = null;
				switch(accion) {
					case 1:
						mensaje = new PedirListaUsuarios("pedirListaUsuarios", name, "servidor");
						break;
					case 2:
						System.out.println("Dime de que cliente es el archivo.");
						String cliente = scanner.nextLine();
						System.out.println("Dime que archivo quieres");
						String archivo = scanner.nextLine();
						mensaje = new PedirFichero("pedirFichero", name, "servidor", cliente, archivo);
						break;
				}
				consola.releaseLock(Cliente.idControlador);
				if(mensaje !=null) {
					consola.takeLock(Cliente.idOyente);
					fOut.writeObject(mensaje);
					fOut.flush();
				}
			}
			mensaje = new Desconexion("desconexion", name, "servidor");
			fOut.writeObject(mensaje);
			fOut.flush();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
