package com.loginCadastro.usuario.Services;

import com.loginCadastro.usuario.EmailEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender jms;

    public EmailService(JavaMailSender jms) {
        this.jms = jms;
    }

    public void EnviarEmail(EmailEnt email){
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom("noreply@verificar.com");
        smm.setTo(email.to());
        smm.setText(email.body());
        this.jms.send(smm);
    }
}
