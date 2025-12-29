package dev.marcos.sendemail.worker;

import dev.marcos.sendemail.dto.EmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailQueueConsumer {

    private final String fromEmail;
    private final JavaMailSender mailSender;

    public EmailQueueConsumer(
            @Value("${spring.mail.properties.mail.smtp.from}") String fromEmail,
            JavaMailSender mailSender
    ) {
        this.fromEmail = fromEmail;
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = "${rabbitmq.email.queue-name}")
    public void handle(EmailRequest req) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(fromEmail);
            message.setTo(req.to());
            message.setSubject(req.subject());
            message.setText(req.body());

            mailSender.send(message);

            log.info("Email enviado com sucesso!");
        } catch (Exception e) {
            log.error("Erro ao enviar email: {}", e.getMessage());
        }
    }
}
