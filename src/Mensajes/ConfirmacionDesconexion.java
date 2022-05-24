package Mensajes;

public class ConfirmacionDesconexion extends Mensaje{

	private static final long serialVersionUID = 1L;

	public ConfirmacionDesconexion(String tipo, String origen, String destino) {
		super(tipo, origen, destino);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "(Desde " + origen + ") Te has desconectado correctamente";
	}

}
