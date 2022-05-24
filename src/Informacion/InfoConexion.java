package Informacion;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class InfoConexion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nombre;
	ObjectOutputStream fOut;
	
	public InfoConexion(String nombre, ObjectOutputStream fOut) {
		this.nombre = nombre;
		this.fOut = fOut;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ObjectOutputStream getfOut() {
		return fOut;
	}

	public void setfOut(ObjectOutputStream fOut) {
		this.fOut = fOut;
	}
	
	public void delete() {
		nombre = "vacio";
		fOut = null;
	}
	

	

}
