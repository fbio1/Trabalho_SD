package message;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Message extends Remote {
	/**Calcula Raiz Quadrada*/
	public String printMsg(Double valor) throws RemoteException;

}
