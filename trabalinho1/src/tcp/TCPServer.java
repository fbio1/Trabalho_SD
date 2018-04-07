package tcp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String argv[]) throws Exception {
		String dado;
		String novaPalavra;
		ServerSocket socket = new ServerSocket(6543);
		while (true) {
			Socket conexao = socket.accept();
			DataInputStream recebe = new DataInputStream(conexao.getInputStream());
			DataOutputStream responde = new DataOutputStream(conexao.getOutputStream());
			Double numero = null, resultado = null;
			for (int i = 0; i < 1000; i++) {
				dado = recebe.readUTF();
				numero = Double.parseDouble(dado);
				resultado = Math.sqrt(numero);
				responde.writeUTF(resultado.toString());
			}
			System.out.println("Raiz quadrado: " + resultado.toString());
		}
	}
}
