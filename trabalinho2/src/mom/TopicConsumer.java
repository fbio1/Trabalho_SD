package mom;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumer implements Runnable {

	ActiveMQConnectionFactory connectionFactory = null;

	public TopicConsumer(ActiveMQConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void run() {
		try {
			// First create a connection
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Byte[] var = new Byte[63];
			for (int i = 0; i < 63; i++) {
				long tempoInicial = System.nanoTime();
				for (int j = 0; j < 200; j++) {					
					// Enviando
					// Let's create a topic.
					// Create a messages for the current climate
					var[i] = (byte) i;
					Destination destination = session.createTopic("BYTES_RECEIVE");
					// Create a MessageProducer from the Session to the Topic or
					// Queue
					MessageProducer producer = session.createProducer(destination);
					producer.setDeliveryMode(DeliveryMode.PERSISTENT);

					TextMessage messageReceived = session.createTextMessage(var[i].toString());
					// Send the message to topic
					producer.send(messageReceived);

					// Recebimento
					Destination topicDestination = session.createTopic("BYTES_SEND");
					// Create a MessageProducer from the Session to the Topic or
					// Queue
					MessageConsumer messageConsumer = session.createConsumer(topicDestination);
					// Get the message
					Message messageSent = messageConsumer.receive();
					TextMessage textMessage = (TextMessage) messageSent;
					// System.out.println(textMessage.getText() + "Producer");

//					long timeFinal = System.currentTimeMillis();
//					double resultadoFinal = timeFinal - time;
//					// double temp = var * 30;
//					double bandaPassante = (var[i] / resultadoFinal);
					//System.out.println(bandaPassante);
					
					double band = i / ((System.nanoTime() - tempoInicial)/ 10E8);
					System.out.println(band);
				}
			}
			System.out.println("Acabou!");
			session.close();
			connection.close();
		} catch (JMSException jmse) {
			System.out.println("Exception: " + jmse.getMessage());
		}
	}
}