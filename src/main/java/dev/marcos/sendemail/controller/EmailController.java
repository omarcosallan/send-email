package dev.marcos.sendemail.controller;

import dev.marcos.sendemail.dto.EmailRequest;
import dev.marcos.sendemail.producer.EmailProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailProducer emailProducer;

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody EmailRequest req) {
        emailProducer.sendMessage(req);
        return ResponseEntity.ok("Message sent: " + req.body());
    }
}
