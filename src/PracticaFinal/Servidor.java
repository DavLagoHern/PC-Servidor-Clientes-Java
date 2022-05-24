package PracticaFinal;

import java.net.*;

/*NOMBRES:
 * 
 * DAVID LAGO HERNANDEZ
 * IGNACIO URRETAVIZCAYA TATO
 * 
 */

import Informacion.*;
import Hilos.*;

public class Servidor {
	private static int numMaxClientes = 5;

    public static void main(String[] args) {
        try {
            ServerSocket listen = new ServerSocket(999);
            
            ListaUsuarios listaUsuarios  = new ListaUsuarios(numMaxClientes);
            ListaConexiones listaConexiones  = new ListaConexiones(numMaxClientes);
            System.out.println("Servidor iniciado en 999");
            while(true) {
                Socket socket = listen.accept();
               
                OyenteCliente oyenteCliente = new OyenteCliente(socket, listaConexiones, listaUsuarios);
                oyenteCliente.start();
            }
            
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

}