package Hilos;

import java.io.*;
import Informacion.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Semaphore;

import Mensajes.*;
import PracticaFinal.Cliente;

public class OyenteServidor extends Thread{

	Socket socket;
	Mensaje mensaje;
	ObjectOutputStream fOut;
	ObjectInputStream fIn;
	Usuario usuario;
	Boolean exit;
	LockRompeEmpate consola;
	ServerSocket p2p;
	
	public OyenteServidor(Socket socket, ObjectOutputStream fOut, Usuario usuario, LockRompeEmpate consola, ServerSocket p2p) {
		this.socket = socket;
		this.fOut = fOut;
		this.usuario = usuario;
		this.consola = consola;
		this.p2p = p2p;
		exit = false;
	}
	
	public void run() {
		try {	
		    fIn = new ObjectInputStream(socket.getInputStream());
			while(!exit) {
				mensaje = (Mensaje) fIn.readObject();
				if(mensaje != null)
					execute();
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void execute() throws IOException {
		Mensaje respuesta = null;
		switch(mensaje.getTipo()) {
			case "confirmacionConexion":
				System.out.println(mensaje.toString());
				consola.releaseLock(Cliente.idOyente);
				break;
				
			case "confirmacionListaUsuarios":
				System.out.println(mensaje.toString());
				consola.releaseLock(Cliente.idOyente);
				break;
				
			case "emitirFichero":
				EmitirFichero mensajeEmitir = (EmitirFichero) mensaje;
				System.out.println(mensajeEmitir.toString());
				Emisor emisor = new Emisor(mensajeEmitir.getArchivo(), p2p);
				emisor.start();
				respuesta = new PreparadoClienteServidor("preparadoClienteServidor", usuario.getNombre(), "servidor", mensajeEmitir.getClienteOrigen(), usuario.getIp(), mensajeEmitir.getArchivo());
				break;
				
			case "preparadoServidorCliente":
				PreparadoServidorCliente preparadoServidorCliente = (PreparadoServidorCliente) mensaje;
				System.out.println(preparadoServidorCliente.toString());
				Receptor receptor = new Receptor(preparadoServidorCliente.getIpClienteEmisor());
				receptor.start();
				consola.releaseLock(Cliente.idOyente);
				break;
			
			case "usuarioNoEncontrado":
				System.out.println(mensaje.toString());
				consola.releaseLock(Cliente.idOyente);
				break;
				
			case "confirmacionDesconexion":
				System.out.println(mensaje.toString());
				consola.releaseLock(Cliente.idOyente);
				fOut.close();
				fIn.close();
				socket.close();
				exit = true;
				break;
		}
		if(respuesta != null){
			fOut.writeObject(respuesta);
			fOut.flush();
		}
	}
}
