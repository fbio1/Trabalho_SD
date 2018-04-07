package rmi;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import message.Message;

public class RMIClient {

	public static void main(String[] args) {
		try {
			Double numero = 36.0;
			Message stub = null;
			for (int i = 0; i < 1000; i++) {
				long time = System.currentTimeMillis();
				Registry registry = LocateRegistry.getRegistry(null);
				stub = (Message) registry.lookup("SERVIDOR");
				stub.printMsg(numero);
				long timeFinal = System.currentTimeMillis();
				Long resultadoFinal = timeFinal - time;
				System.out.println(resultadoFinal);
			}

			System.out.println(stub.printMsg(numero));

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
