package Mensajes;

public class Desconexion extends Mensaje{

	private static final long serialVersionUID = 1L;

	public Desconexion(String tipo, String origen, String destino) {
		super(tipo, origen, destino);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "(Desde "+origen + ") Me desconecto";
	}

}
