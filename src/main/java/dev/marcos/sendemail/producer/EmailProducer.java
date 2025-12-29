package dev.marcos.sendemail.producer;

import dev.marcos.sendemail.dto.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducer {

    @Value("${rabbitmq.email.exchange-name}")
    private String EXCHANGE_NAME;

    @Value("${rabbitmq.email.routing-key}")
    private String ROUTING_KEY;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(EmailRequest req) {
        rabbitTemplate.convertAndSend(
                EXCHANGE_NAME,
                ROUTING_KEY,
                req
        );
    }
}
