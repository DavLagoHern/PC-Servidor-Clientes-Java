package Informacion;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nombre;
	String lista[];
	int nArchivos;
	int ip;
	
	public Usuario(String nombre, String lista[], int direccionIpMaquina, int nArchivos) {
		this.nombre = nombre;
		this.lista = lista;
		this.ip = direccionIpMaquina;
		this.nArchivos = nArchivos;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String[] getLista() {
		return lista;
	}

	public void setLista(String[] lista) {
		this.lista = lista;
	}
	
	public void updateLista(String archivo) {
		lista[nArchivos] = archivo;
		nArchivos++;
	}
	
	public String getListaString() {
		String aux = "";
		for(String s : lista) {
			if(!s.equals("vacio"))
				aux += s + " ";
		}
		
		return aux;
	}
	
	public void delete() {
		nombre = "vacio";
		String aux1[] = new String[1];
		aux1[0] = "vacio";
		lista = aux1;
		ip = -1;
	}
	
	public void setIp(int ip) {
		this.ip = ip;
	}
	
	public int getIp() {
		return ip;
	}

	

}
