package dev.marcos.sendemail.exception;

import org.springframework.amqp.AmqpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MailException.class,
            AmqpException.class,
            Exception.class
    })
    public ResponseEntity<Map<String, Object>> handleGenericException(final Exception e) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", e.getMessage());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
