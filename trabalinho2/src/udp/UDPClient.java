package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	private static final int port = 6789;
	private static final String host = "127.0.0.1";

	public static void main(String[] args) throws Exception {

		DatagramSocket udpclient = new DatagramSocket();
		long resultado = 0;
		for (int i = 0; i <= 63; i++) {
			long tempoInicial = System.nanoTime();
			//int var = i * 1024;
			for (int j = 0; j < 200; j++) {
				DatagramPacket pedido = new DatagramPacket(new byte[i*1024], i, InetAddress.getByName(host), port);
				udpclient.send(pedido);

				DatagramPacket resposta = new DatagramPacket(new byte[i*1024], i);
				udpclient.receive(resposta);
			}				
			
			double band = i / ((System.nanoTime() - tempoInicial)/ 10E8);
			System.out.println(band);
		}
		System.out.println("acabou");
		udpclient.close();
	}
}