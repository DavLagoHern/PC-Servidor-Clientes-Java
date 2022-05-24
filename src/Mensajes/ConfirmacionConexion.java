package Mensajes;

public class ConfirmacionConexion extends Mensaje {
	
	private static final long serialVersionUID = 1L;

	public ConfirmacionConexion(String tipo, String origen, String destino) {
		super(tipo, origen, destino);
		
	}

	@Override
	public String toString() {
		String mensaje = "(Desde "+ origen + ") Te confirmo que te has conectado " + destino;
		return mensaje;
	}

}
