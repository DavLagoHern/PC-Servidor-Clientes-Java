package Mensajes;

import Informacion.*;

public class Conexion extends Mensaje {
	private static final long serialVersionUID = 1L;
	
	Usuario usuario;

	public Conexion(String tipo, String origen, String destino, Usuario usuario) {
		super(tipo, origen, destino);
		this.usuario = usuario;
		
	}


	@Override
	public String toString() {
		return "(Desde "+ origen + ") Me quiero conectar a " + destino;

	}
	
	public Usuario getUsuario() {
		return usuario;
	}

}
