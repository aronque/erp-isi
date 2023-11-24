package com.system.backend.services.impl;

import com.system.backend.entities.Relatorio;
import com.system.backend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender sender;

    public void sendEmail(Relatorio relatorio) {

        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("isimailsender@gmail.com");
            helper.setSubject(relatorio.getNomeRelatorio());
            helper.setTo(relatorio.getEmail());
            helper.setText("Segue em anexo o relatório requisitado.");

            FileSystemResource arquivo = new FileSystemResource(new File(System.getProperty("user.dir") + relatorio.getXslPath()));
            helper.addAttachment(Objects.requireNonNull(arquivo.getFilename()), arquivo);

            sender.send(message);
        } catch(MessagingException e) {

        }

    }
}
