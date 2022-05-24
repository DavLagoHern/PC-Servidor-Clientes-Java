package Mensajes;

public class PreparadoServidorCliente extends Mensaje{

	private static final long serialVersionUID = 1L;
	int ipClienteEmisor;
	String clienteEmisor;

	public PreparadoServidorCliente(String tipo, String origen, String destino, String clienteEmisor, int ipClienteEmisor) {
		super(tipo, origen, destino);
		this.ipClienteEmisor = ipClienteEmisor;
		this.clienteEmisor = clienteEmisor;
	}

	@Override
	public String toString() {
		return  "(Desde "+origen+") " + clienteEmisor + " esta preparado para conectar";
	}
	
	public String getClienteEmisor() {
		return clienteEmisor;
	}
	
	public int getIpClienteEmisor() {
		return ipClienteEmisor;
	}

}
