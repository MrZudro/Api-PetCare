package edu.sena.petcare.services;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
