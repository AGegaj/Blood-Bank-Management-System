package org.fiek.bloodmanagementsystem.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(MailTemplate mailTemplate) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailTemplate.getFrom());
        simpleMailMessage.setTo(mailTemplate.getTo());
        simpleMailMessage.setSubject(mailTemplate.getSubject());
        simpleMailMessage.setText(mailTemplate.getText());

        mailSender.send(simpleMailMessage);
    }
}
