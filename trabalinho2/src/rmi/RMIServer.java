package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmiUtil.Message;

public class RMIServer implements Message {

	public RMIServer() {
	}

	public static void main(String args[]) {
		try {
			RMIServer obj = new RMIServer();

			for (int i = 0; i <= 63; i++) {
				for (int j = 0; j < 200; j++) {
					Message stub = (Message) UnicastRemoteObject.exportObject(obj, 0);
					Registry registry = LocateRegistry.getRegistry();
					registry.rebind("SERVIDOR", stub);
					System.out.println("Server ready / " + stub.toString());
				}
			}
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public Byte[] printMsg(Byte[] bytes) throws RemoteException {

		return bytes;
	}

}
