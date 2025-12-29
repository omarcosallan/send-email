package dev.marcos.sendemail.dto;

public record EmailRequest(
        String to,
        String subject,
        String body
) {
}
