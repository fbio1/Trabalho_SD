package rmiUtil;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Message extends Remote {
	
	Byte[] printMsg(Byte[] bytes) throws RemoteException;

}
