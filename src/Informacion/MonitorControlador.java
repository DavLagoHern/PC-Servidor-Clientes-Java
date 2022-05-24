package Informacion;

import java.io.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MonitorControlador implements Serializable{
	private static final long serialVersionUID = 1L;

	Entero nReader, nWriter;
	
	private final Lock l = new ReentrantLock(true);
	private final Condition condWrite = l.newCondition();
	private final Condition condRead = l.newCondition();
	
	
	int tamLista;
	public MonitorControlador() throws IOException {
        nReader = new Entero(0);
        nWriter = new Entero(0);
	}
	
	public void request_read() throws InterruptedException {
		l.lock();
		while(nWriter.getValor() > 0)
			condRead.await();
		nReader.aumentar();
		l.unlock();

	}
	
	
	public void release_read() {
		l.lock();
		nReader.decrementar();
		if(nReader.getValor() == 0) condWrite.signal();
		l.unlock();
	}
	
	public void request_write() throws InterruptedException {
		l.lock();
		while(nWriter.getValor() > 0 || nReader.getValor() >0) condWrite.await();
		nWriter.aumentar();
		l.unlock();

	}
	
	public void release_write() {
		l.lock();
		nWriter.decrementar();
		condWrite.signal();
		condRead.signalAll();
		l.unlock();
	}

	
}
