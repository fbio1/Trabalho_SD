package udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.out.println("Uso correto UDPClient <servidor> <porta> <dados>");
			System.exit(0);
		}
		DatagramSocket udpclient = new DatagramSocket();
		InetAddress server = InetAddress.getByName(args[0]);
		int porta = Integer.parseInt(args[1]);
		byte[] dados = args[2].getBytes();
		DatagramPacket pedido = new DatagramPacket(dados, dados.length, server, porta);

		byte[] buffer = new byte[1000];
		DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);

		String sdados = new String(resposta.getData(), "UTF-8");
		System.out.println("Nova String:" + sdados);

		for (int i = 0; i < 1000; i++) {
			long tempo = System.currentTimeMillis();
			udpclient.send(pedido);
			udpclient.receive(resposta);
			long tempoFinal = System.currentTimeMillis();

			long resultado = tempoFinal - tempo;
			System.out.println(resultado);
		}
		//java BLABLA 127.0.0.1 6789 36 > saida.txt 
		udpclient.close();
	}
}