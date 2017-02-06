/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email_utility;

import java.util.Properties;
import java.util.Date;
import javax.activation.DataHandler;

import javax.mail.*;

import javax.mail.internet.*;

import javax.activation.DataSource;
import javax.activation.FileDataSource;

/**
 *
 * @author John
 */
public class EmailUtil {

    private String sender, senderPassword;

    public EmailUtil(String sender, String senderPassword) {
        this.sender = sender;
        this.senderPassword = senderPassword;
    }

    public void sendEmail(String receiver, String subject, String message) throws AddressException, MessagingException {
        Properties props = System.getProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.live.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));;
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(receiver, false));
        msg.setSubject(subject);
        msg.setText(message);
        msg.setSentDate(new Date());
        
        Transport t = session.getTransport("smtp");
        t.connect("smtp.live.com", sender, senderPassword);
        t.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Response: " + t);
        t.close();
    }
    
    public void sendEmail(String receiver, String subject, String message, String file) throws AddressException, MessagingException {
                Properties props = System.getProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.live.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));;
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(receiver, false));
        msg.setSubject(subject);
        
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(message);
        
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        
        //Add The file attachment
        messageBodyPart = new MimeBodyPart();
        String filename = file;
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        
        msg.setContent(multipart);
        msg.setSentDate(new Date());
        
        Transport t = session.getTransport("smtp");
        t.connect("smtp.live.com", sender, senderPassword);
        t.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Response: " + t);
        t.close();
    }
}
