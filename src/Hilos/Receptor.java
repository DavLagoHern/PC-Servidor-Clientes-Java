package Hilos;

import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import Informacion.LockRompeEmpate;
import PracticaFinal.Cliente;

public class Receptor extends Thread{

	int ipEmisor;
	
	public Receptor(int i) {
		this.ipEmisor = i;

	}
	
	public void run() {
		try {
			int puerto = ipEmisor;
			Socket s = new Socket("localhost", puerto);
			System.out.println("** Cliente conectado a cliente con ip " + ipEmisor + "**");
			BufferedReader fIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter fOut = new PrintWriter(s.getOutputStream());

            String mensaje = fIn.readLine();;
            while(!mensaje.equals("** (Desde emisor) Fichero acabado **")) {     	
            	System.out.println(mensaje);
            	mensaje = fIn.readLine();
            }
            fOut.println("** (Desde receptor) Fichero recibido correctamente **");
            fOut.flush();
            System.out.println(mensaje);
            fIn.close();
            fOut.close();
            s.close();
            System.out.println( "** Cliente desconectado del puerto " + ipEmisor + " **");
            
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
