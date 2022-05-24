package Mensajes;

public class EmitirFichero extends Mensaje{

	private static final long serialVersionUID = 1L;

	String clienteOrigen;
	String archivo;
	
	public EmitirFichero(String tipo, String origen, String destino, String clienteOrigen, String archivo) {
		super(tipo, origen, destino);
		this.clienteOrigen = clienteOrigen;
		this.archivo = archivo;
	}

	@Override
	public String toString() {
		return "(Desde " + origen + ") Cliente " + clienteOrigen + " quiere un fichero";
	}
	
	public String getClienteOrigen() {
		return clienteOrigen;
	}
	
	public String getArchivo() {
		return archivo;
	}

}
