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

public class TopicProducer implements Runnable {
	ActiveMQConnectionFactory connectionFactory = null;

	public TopicProducer(ActiveMQConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void run() {
		try {
			// First create a connection
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			for (int i = 0; i <= 63; i++) {
				for (int j = 0; j < 200; j++) {
					// Let's create a topic. If the topic exist, it will return
					// that
					// Recebimento
					// Get the message
					Destination topicDestination = session.createTopic("BYTES_RECEIVE");
					// Create a MessageProducer from the Session to the Topic or
					// Queue
					MessageConsumer messageConsumer = session.createConsumer(topicDestination);
					Message messageSent = messageConsumer.receive();
					TextMessage textMessage = (TextMessage) messageSent;
					// System.out.println(textMessage.getText() + "Consumer");

					// Enviamento
					Destination destination = session.createTopic("BYTES_SEND");
					// Create a MessageProducer from the Session to the Topic or
					// Queue
					MessageProducer producer = session.createProducer(destination);
					producer.setDeliveryMode(DeliveryMode.PERSISTENT);
					// Create a messages for the current climate

					TextMessage message = session.createTextMessage(textMessage.toString());
					// Send the message to topic
					producer.send(message);

				}
			}
			session.close();
			connection.close();
		} catch (JMSException jmse) {
			System.out.println("Exception: " + jmse.getMessage());
		}
	}

}