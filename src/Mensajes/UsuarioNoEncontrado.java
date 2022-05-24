package Mensajes;

public class UsuarioNoEncontrado extends Mensaje {
	private static final long serialVersionUID = 1L;
	String archivos[];
	String clienteNoEncontrado;
	public UsuarioNoEncontrado(String tipo, String origen, String destino, String clienteNoEncontrado) {
		super(tipo, origen, destino);		
		this.clienteNoEncontrado = clienteNoEncontrado;
	}
	
	public String[] getLista() {
		return archivos;
	}

	@Override
	public String toString() {
		return "(Desde "+ origen + ") El usuario " + clienteNoEncontrado + " no se ha encontrado";
	}

}
