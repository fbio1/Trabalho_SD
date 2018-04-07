package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class UDPServer {
	public static void main(String[] args) throws Exception {
		DatagramSocket udpsocket = new DatagramSocket(6789);

		for (int i = 0; i <= 63; i++) {
			for (int j = 0; j < 200; j++) {
				DatagramPacket recebe = new DatagramPacket(new byte[i * 1024], i * 1024);
				udpsocket.receive(recebe);
				InetAddress ip = recebe.getAddress();
				int port = recebe.getPort();
				byte[] saida = recebe.getData();
				DatagramPacket responde = new DatagramPacket(saida, saida.length, ip, port);
				udpsocket.send(responde);
			}
		}
		udpsocket.close();
	}
}