package Informacion;

import java.io.IOException;
import java.io.Serializable;

public class ListaUsuarios implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Usuario listaUsuarios[];
	MonitorControlador monitorControlador;
	int numMaxClientes;


	public ListaUsuarios(int numMaxClientes) throws IOException {
		monitorControlador = new MonitorControlador();
		listaUsuarios = new Usuario[numMaxClientes];
		this.numMaxClientes = numMaxClientes;
		inicializar();
	}
	
	public void inicializar() throws IOException {
		for(int i = 0;  i< numMaxClientes; i++) {
			String aux1[] = new String[1];
			aux1[0] = "vacio";
			listaUsuarios[i] = new Usuario("vacio", aux1, -1, 2);
		}
	}
	public void addUser(Usuario usuario) throws InterruptedException {
		monitorControlador.request_write();
		for(int i = 0; i < numMaxClientes; i++) {
			if(listaUsuarios[i].getNombre().equals("vacio")) {
				listaUsuarios[i] = usuario;
				break;
			}
		}
		monitorControlador.release_write();
	}
	
	public void deleteUser(String nombre) throws InterruptedException {
		monitorControlador.request_write();
		for(Usuario i : listaUsuarios) {
			if(i.getNombre().equals(nombre)) {
				i.delete();
				break;
			}
		}
		monitorControlador.release_write();
	}
	
	public String toString(){
		String aux = "";
		try {
			monitorControlador.request_read();
			aux = "Lista de usuarios: \n";
			for(Usuario i : listaUsuarios) {
				if(!i.getNombre().equals("vacio")){
					aux += "-" + i.getNombre() + ": \n" + "   " +i.getListaString() + "\n \n";		
				}
			}
			return aux;	
		} 
		catch (InterruptedException e) {			
			e.printStackTrace();
		}
		finally {
			monitorControlador.release_read();
		}
		return aux;
	}

	public ListaUsuarios getListaUsuarios() {
		return this;
	}

	public void updateUser(String nombre, String archivo) throws InterruptedException {
		monitorControlador.request_write();
		for(Usuario i : listaUsuarios) {
			if(i.getNombre().equals(nombre)) {
				i.updateLista(archivo);
				break;
			}
		}
		monitorControlador.release_write();
	}
	
	

	
}
