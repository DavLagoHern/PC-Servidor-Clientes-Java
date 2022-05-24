package Informacion;

import java.io.Serializable;

public class Entero implements Serializable{
	private int valor;
	public Entero(int a) {
		valor = a;
	}
	
	public int getValor() {
		return valor;
	}
	public void aumentar() {
		this.valor++;
	}
	
	public void decrementar() {
		this.valor--;
	}

	public void aumentarMod(int tam) {
		valor  = (valor+1)%tam;
	}
		
	
	
}
