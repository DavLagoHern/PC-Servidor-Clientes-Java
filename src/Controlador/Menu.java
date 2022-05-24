package Controlador;

import java.util.Scanner;

public class Menu {

	private String name;
	private Scanner s;
	
	public Menu(String name, Scanner s) { 
		this.name = name;
		this.s = s;
	}
	
	public int show() {
		int opcion = 4;
		while(opcion <= 0 || opcion > 3) {
			System.out.println("Usuario " + name + " selecciona una opcion");
			System.out.println("1: Lista de Usuarios");
			System.out.println("2: Pedir Fichero");
			System.out.println("3: Salir/Desconectar");
			System.out.println(">");
			opcion = Integer.parseInt(s.nextLine());
		}
		return opcion;
	}
		
}
