package Mensajes;

public class PedirListaUsuarios extends Mensaje {

	private static final long serialVersionUID = 1L;

	public PedirListaUsuarios(String tipo, String origen, String destino) {
		super(tipo, origen, destino);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "(Desde " + origen +") Dime la lista de usuario";
	}

}
