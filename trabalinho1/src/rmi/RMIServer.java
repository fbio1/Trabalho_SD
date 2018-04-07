package rmi;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import message.Message;

public class RMIServer implements Message {

	public RMIServer() {
	}

	public static void main(String args[]) {
		try {
			RMIServer obj = new RMIServer();
			// for (int i = 0; i < 1000; i++) {
			Message stub = (Message) UnicastRemoteObject.exportObject(obj, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("SERVIDOR", stub);
			System.err.println("Server ready / " + stub.toString());
			// }
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public String printMsg(Double valor) throws RemoteException {
		Double resultado = Math.sqrt(valor);
		return resultado.toString();
	}

}
