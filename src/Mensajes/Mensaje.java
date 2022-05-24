package Mensajes;

import java.io.Serializable;

public abstract class Mensaje  implements Serializable{

	private static final long serialVersionUID = 1L;
	String tipo;
	String origen;
	String destino;
	
	public Mensaje(String tipo, String origen, String destino) {
		this.tipo = tipo;
		this.origen = origen;
		this.destino = destino;
	}
	public String getTipo() {
		return this.tipo;
	}
	public String getOrigen() {
		return this.origen;
	}
	public String getDestino() {
		return this.destino;
	}
	
	public abstract String toString();
}
