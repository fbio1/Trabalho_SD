package rmi;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmiUtil.Message;

public class RMIClient {

	public static void main(String[] args) {
		try {
			Message stub = null;

			for (int i = 0; i <= 63; i++) {
				long tempoInicial = System.nanoTime();
				//Byte[] var = new Byte[i*1024];
				for (int j = 0; j < 200 ; j++) {
					Registry registry = LocateRegistry.getRegistry(null);
					stub = (Message) registry.lookup("SERVIDOR");
					stub.printMsg(new Byte[i*1024]);
				}

				double band = i / ((System.nanoTime() - tempoInicial)/ 10E8);
				System.out.println(band);
			}			
			System.out.println("acabou");

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}