package Hilos;

import java.io.*;
import Informacion.*;
import PracticaFinal.Cliente;

import java.net.*;

public class Emisor extends Thread{
	
	String archivo;
	ServerSocket p2p;
	
	public Emisor(String archivo, ServerSocket p2p) {
		this.archivo = archivo;
		this.p2p = p2p;
	}
	
	
	public void run() {
		try {
			Socket s = p2p.accept();
			System.out.println( "** Alguien se ha conectado **");
			BufferedReader fIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter fOut = new PrintWriter(s.getOutputStream());
            File ficheroTexto = new File(archivo);
            if(!ficheroTexto.exists()) { //Si no existe
                fOut.println("** No se puede abrir el archivo " + archivo+" **");
                fOut.flush();
                fOut.close();
                fIn.close();
                s.close();
            }
            else {
                System.out.println("** Leyendo archivo " + archivo +" **");
				BufferedReader archivo = new BufferedReader(new FileReader(ficheroTexto));
                String linea = "";
                while(linea != null) {
                	linea = archivo.readLine();
                	if(linea != null) {
                        fOut.println("** "+linea +" **");
                        fOut.flush();
                	}
                }
                fOut.println("** (Desde emisor) Fichero acabado **");
                fOut.flush();
                String mensaje = fIn.readLine();
                System.out.println(mensaje);

                fIn.close();
                fOut.close();
                s.close();
                System.out.println("** Alguien se ha desconectado **");
            }
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
