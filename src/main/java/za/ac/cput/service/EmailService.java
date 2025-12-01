package za.ac.cput.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
