/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email_utility;

import javax.mail.MessagingException;

/**
 *
 * @author John
 */
public class EmailTest {

    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException {
        EmailUtil util = new EmailUtil("fluttershy1337@hotmail.com", "Klippe93");
        
        String receiver = "jonathan.henriksen@yandex.com";
        String subject = "Final Test From Java";
        String message = "This is so fucking cool, it is actually working like it is supposed to";
        
        util.sendEmail(receiver, subject, message);
    }
    
}
