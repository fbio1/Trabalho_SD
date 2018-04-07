package tcp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCPClient {
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println("Uso correto: TCPClient <host> <post> <message>");
			System.exit(0);
		}
		String host = args[0];
		String port = args[1];
		String message = args[2];
		String modifiedMessage = null;
		Socket clientSocket = new Socket(host, Integer.parseInt(port));
		DataInputStream in = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
		for (int i = 0; i < 1000; i++) {
			long tempo = System.currentTimeMillis();
			out.writeUTF(message);
			modifiedMessage = in.readUTF();
			long tempoFinal = System.currentTimeMillis();
			long resultado = tempoFinal - tempo;
			System.out.println(resultado);
		}
		// out.flush();
		System.out.println("Nova palavra: " + modifiedMessage);
		clientSocket.close();
	}
}