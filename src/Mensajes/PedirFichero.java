package Mensajes;

public class PedirFichero extends Mensaje{

	private static final long serialVersionUID = 1L;
	String cliente;
	String archivo;

	public PedirFichero(String tipo, String origen, String destino, String cliente, String archivo) {
		super(tipo, origen, destino);
		this.cliente = cliente;
		this.archivo = archivo;
	}

	@Override
	public String toString() {
		return ("(Desde " + origen + ") Y quiero un fichero de " + cliente);
	}
	
	public String getCliente(){
		return cliente;
	}
	
	public String getArchivo() {
		return archivo;
	}

}
