package Hilos;

import java.io.*;
import java.net.Socket;

import Informacion.*;
import Mensajes.*;

public class OyenteCliente extends Thread{
	Socket socket;
	Mensaje mensaje;
	ObjectInputStream fIn;
	ObjectOutputStream  fOut;
	Boolean exit;
	ListaConexiones listaConexiones;
	ListaUsuarios listaUsuarios;
	public OyenteCliente(Socket socket, ListaConexiones listaConexiones, ListaUsuarios listaUsuarios) {
		this.socket = socket;
		this.listaConexiones = listaConexiones;
		this.listaUsuarios = listaUsuarios;
		exit = false;
	}
	
	public void run() {
		try {	
			fIn = new ObjectInputStream (socket.getInputStream());
			fOut = new ObjectOutputStream(socket.getOutputStream());
			while(!exit) {
				mensaje = (Mensaje) fIn.readObject();
				if(mensaje !=null) {
					System.out.println(mensaje.toString());
					execute();
				}
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void execute() throws IOException, InterruptedException {
		Mensaje respuesta = null;
		switch(mensaje.getTipo()) {
			case "conexion":
				Conexion conexion = (Conexion) mensaje;
				listaUsuarios.addUser(conexion.getUsuario());
				listaConexiones.addUser(conexion.getUsuario().getNombre(), fOut);
				respuesta = new ConfirmacionConexion("confirmacionConexion", "servidor", conexion.getOrigen());
				fOut.writeObject(respuesta);
				fOut.flush();
				System.out.println("Cliente "+ conexion.getUsuario().getNombre() +" conectado");
				break;
				
			case "pedirListaUsuarios":
				respuesta = new ConfirmacionListaUsuarios("confirmacionListaUsuarios", "servidor", mensaje.getOrigen(), listaUsuarios.getListaUsuarios().toString());
				fOut.writeObject(respuesta);
				fOut.flush();
				break;
				
			case "pedirFichero":
				PedirFichero pedirFichero = (PedirFichero) mensaje;
				ObjectOutputStream fOutCliente2 = listaConexiones.getfOutCliente(pedirFichero.getCliente());
				if(fOutCliente2 != null) {
					respuesta = new EmitirFichero("emitirFichero", "servidor", pedirFichero.getCliente(), pedirFichero.getOrigen(), pedirFichero.getArchivo());
					fOutCliente2.writeObject(respuesta);
					fOutCliente2.flush();
				}
				else {
					respuesta = new UsuarioNoEncontrado("usuarioNoEncontrado", "servidor", pedirFichero.getOrigen(), pedirFichero.getCliente());
					fOut.writeObject(respuesta);
					fOut.flush();
				}
				break;
				
			case "preparadoClienteServidor":
				PreparadoClienteServidor preparadoClienteServidor = (PreparadoClienteServidor) mensaje;
				listaUsuarios.updateUser(preparadoClienteServidor.getClienteDestino(), preparadoClienteServidor.getArchivo()); //Meter el archivo nuevo que ha descargado
				respuesta = new PreparadoServidorCliente("preparadoServidorCliente", "servidor", preparadoClienteServidor.getClienteDestino(), preparadoClienteServidor.getOrigen(), preparadoClienteServidor.getIpCliente());
				ObjectOutputStream fOutCliente1 = listaConexiones.getfOutCliente(preparadoClienteServidor.getClienteDestino());
				if(fOutCliente1 != null) {
					fOutCliente1.writeObject(respuesta);
					fOutCliente1.flush();
				}
				break;
			
			case "desconexion":	
				listaUsuarios.deleteUser(mensaje.getOrigen());
				listaConexiones.deleteUser(mensaje.getOrigen());
				respuesta = new ConfirmacionDesconexion("confirmacionDesconexion", "servidor", mensaje.getOrigen());
				fOut.writeObject(respuesta);
				fOut.flush();
				fIn.close();
				fOut.close();
				socket.close();
				exit = true;
				break;
				
		}

	}

}
