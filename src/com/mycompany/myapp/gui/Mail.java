/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.gui;


//import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author LENOVO
 */

public class Mail {
    
    public Mail() {
        /*
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.user", "test@esprit.tn");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false"); 
        try {                 
                Session session = Session.getDefaultInstance(props, null);
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                message.setText("You have sent a reclamation");
                message.setSubject("Reclamation");
                message.setFrom(new InternetAddress("firasmm430@gmail.com"));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress("firas.mimoun@esprit.tn"));
                message.saveChanges();
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", "firasmm430@gmail.com", "Firas21132644");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();      
            
        } catch (Exception e) {
            e.printStackTrace();  
        } */
    }
    
        
    
}
