package Mensajes;

public class PreparadoClienteServidor extends Mensaje{

	private static final long serialVersionUID = 1L;
	int direccionIpMaquina;
	String clienteDestino;
	String archivo;
	
	public PreparadoClienteServidor(String tipo, String origen, String destino, String clienteDestino, int i, String archivo) {
		super(tipo, origen, destino);
		this.direccionIpMaquina = i;
		this.clienteDestino = clienteDestino;
		this.archivo = archivo;
	}

	@Override
	public String toString() {
		return "(Desde "+origen+") Estoy preparado para "+ clienteDestino;
	}
	
	public String getClienteDestino() {
		return clienteDestino;
	}
	
	public int getIpCliente() {
		return direccionIpMaquina;
	}
	
	public String getArchivo() {
		return archivo;
	}

}
