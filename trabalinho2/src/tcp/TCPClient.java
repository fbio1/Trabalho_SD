package tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TCPClient {
	private static final int port = 6789;
	private static final String host = "127.0.0.1";

	public static void main(String[] args) throws Exception {
		Socket clientSocket = new Socket(host, port);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		PrintStream out = new PrintStream(clientSocket.getOutputStream());

		for (int i = 0; i <= 63; i++) {
			long tempoInicial = System.nanoTime();
			//int var = i * 1024;
			for (int j = 0; j < 200; j++) {
				out.println(new byte[i * 1024]);
				in.readLine();
			}
	
			double band = i / ((System.nanoTime() - tempoInicial)/ 10E8);
			System.out.println(band);
		}
		System.out.println("acabou");
		clientSocket.close();
	}
}