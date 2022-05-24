package Mensajes;

import java.io.Serializable;
import Informacion.*;

public class ConfirmacionListaUsuarios extends Mensaje implements Serializable{
	private static final long serialVersionUID = 1L;

	String lista;
	
	public ConfirmacionListaUsuarios(String tipo, String origen, String destino, String lista) {
		super(tipo, origen, destino);
		this.lista = lista;
	}

	@Override
	public String toString() {
		return lista;

	}

}
