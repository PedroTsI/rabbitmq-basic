import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Produtor {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPort(5672);
        try (
                Connection connection = connectionFactory.newConnection();
                Channel canal = connection.createChannel();
        ) {
            String mensagem = "Olá Pedro Paulo Cantalice...";
            String NOME_FILA = "teste";

            //(queue, passive, durable, exclusive, autoDelete, arguments)
            canal.queueDeclare(NOME_FILA, true, false, false, null);

            // ​(exchange, routingKey, mandatory, immediate, props, byte[] body)
            canal.basicPublish("", NOME_FILA, MessageProperties.PERSISTENT_TEXT_PLAIN, mensagem.getBytes());
            System.out.println("Mensagem enviada: " + mensagem);

        }
    }
}


