package Informacion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.Semaphore;

public class ListaConexiones implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InfoConexion listaConexiones[];
	int tam;
	Entero nReaders, nWriters, dReaders, dWriters;
	Semaphore mutex, readers, writers;

	public ListaConexiones(int tam) throws IOException {
		listaConexiones = new InfoConexion[5];
		this.tam = tam;
		nReaders = new Entero(0);
		nWriters = new Entero(0);
		dReaders = new Entero(0);
		dWriters = new Entero(0);
		readers = new Semaphore(0);
		writers = new Semaphore(0);
		mutex = new Semaphore(1);
		inicializar();
	}
	
	public void inicializar() throws IOException {
		for(int i = 0;  i< tam; i++) {
			String aux1[] = new String[1];
			aux1[0] = "vacio";
			ObjectOutputStream fOut = null;
			listaConexiones[i] = new InfoConexion("vacio", fOut);
		}
	}
	public void addUser(String nombre, ObjectOutputStream fOut) throws InterruptedException {
		mutex.acquire();
		if(nReaders.getValor() > 0 || nWriters.getValor() > 0) {
			dWriters.aumentar();
			mutex.release();
			writers.acquire();
		}
		nWriters.aumentar();
		signal3();
		for(int i = 0; i < 5; i++) {
			if(listaConexiones[i].getNombre().equals("vacio")) {
				listaConexiones[i].setNombre(nombre);
				listaConexiones[i].setfOut(fOut);
				break;
			}
		}

		mutex.acquire();
		nWriters.decrementar();
		signal4();
	}
	
	public void deleteUser(String nombre) throws InterruptedException {
		mutex.acquire();
		if(nReaders.getValor() > 0 || nWriters.getValor() > 0) {
			dWriters.aumentar();
			mutex.release();
			writers.acquire();
		}
		nWriters.aumentar();
		signal3();
		for(InfoConexion i : listaConexiones) {
			if(i.getNombre().equals(nombre)) {
				i.delete();
				break;
			}
		}

		mutex.acquire();
		nWriters.decrementar();
		signal4();
	}

	public ObjectOutputStream getfOutCliente(String cliente) throws InterruptedException {
		ObjectOutputStream aux = null;
		mutex.acquire();
		if(nWriters.getValor() > 0) {
			dReaders.aumentar();
			mutex.release();
			readers.acquire();
		}
		nReaders.aumentar();
		signal1();
		for(InfoConexion i : listaConexiones) {
			if(i.getNombre().equals(cliente)) {
				aux = i.getfOut();
			}
		}
		mutex.acquire();
		nReaders.decrementar();
		signal2();
		return aux;
	}
	
	public void signal1() {
		if(dReaders.getValor() >0) {
			dReaders.decrementar();
			readers.release();
		}
		else {
			mutex.release();
		}
	}
	
	public void signal2() {
		if(nReaders.getValor() == 0 && dWriters.getValor() >0) {
			dWriters.decrementar();
			writers.release();
		}
		else {
			mutex.release();
		}
	}
	
	public void signal3() {
			mutex.release();
	}
	
	public void signal4() {
		if(dReaders.getValor() >0) {
			dReaders.decrementar();
			readers.release();
		}
		else if(dWriters.getValor() >0 ) {
			dWriters.decrementar();
			writers.release();
		}
		else {
			mutex.release();
		}
	}
	
}
