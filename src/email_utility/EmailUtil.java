/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email_utility;

import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Date;

import javax.mail.*;

import javax.mail.internet.*;

import com.sun.mail.smtp.*;

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
}
