package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String argv[]) throws Exception {
		
		ServerSocket socket = new ServerSocket(6789);
		Socket conexao = socket.accept();
		BufferedReader recebe = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		PrintStream responde = new PrintStream(conexao.getOutputStream());

		for (int i = 0; i <= 63; i++) 
			for (int j = 0; j < 200; j++) 			
				responde.println(recebe.readLine());			
		
		conexao.close();
		socket.close();		

	}
}
