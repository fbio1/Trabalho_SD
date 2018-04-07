package udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
	public static void main(String[] args) throws Exception {
		DatagramSocket udpsocket = new DatagramSocket(6789);
		byte[] buffer = new byte[1000];
		while (true) {
			DatagramPacket recebe = new DatagramPacket(buffer, buffer.length);
			String saida = null;
			Double resultado = 0.0;
			for (int i = 0; i < 1000; i++) {	
				udpsocket.receive(recebe);
				byte[] bufferrecebido = recebe.getData();
				InetAddress ip = recebe.getAddress();
				int port = recebe.getPort();
				//System.out.println("Recebeu: " + bufferrecebido);
				saida = new String(bufferrecebido, "UTF-8");
				double valor = Double.parseDouble(saida);
				
				resultado = Math.sqrt(valor);
				DatagramPacket responde = new DatagramPacket(saida.getBytes(), saida.getBytes().length, ip, port);
				udpsocket.send(responde);
			}
			System.out.println("Valor de entrada: " + saida.toString());
			System.out.println("Resultado da raiz: " + resultado.toString());

		}
	}
}