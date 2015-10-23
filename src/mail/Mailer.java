package mail;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Mailer {
    public void sendEmail(String body, String subject, String recipient) throws MessagingException, UnsupportedEncodingException {
    Properties mailProps = new Properties();
    mailProps.put("mail.smtp.from", "martinogpeter@gmail.com");
    mailProps.put("mail.smtp.host", "smtp.gmail.com");
    mailProps.put("mail.smtp.port", 587);
    mailProps.put("mail.smtp.auth", true);
    mailProps.put("mail.smtp.socketFactory.port", 465);
    mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    mailProps.put("mail.smtp.socketFactory.fallback", "false");
    mailProps.put("mail.smtp.starttls.enable", "true");
    
    Session mailSession = Session.getDefaultInstance(mailProps, new Authenticator() {
    
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("martinogpeter@gmail.com", "Balle123");
        }
    
    });
    
    MimeMessage message = new MimeMessage(mailSession);
    message.setFrom(new InternetAddress("martinandpeter@hotels.com"));
    String[] emails = { recipient };
    InternetAddress dests[] = new InternetAddress[emails.length];
    for (int i = 0; i < emails.length; i++) {
        dests[i] = new InternetAddress(emails[i].trim().toLowerCase());
    }
    message.setRecipients(Message.RecipientType.TO, dests);
    message.setSubject(subject, "UTF-8");
    Multipart mp = new MimeMultipart();
    MimeBodyPart mbp = new MimeBodyPart();
    mbp.setContent(body, "text/html;charset=utf-8");
    mp.addBodyPart(mbp);
    message.setContent(mp);
    message.setSentDate(new java.util.Date());
    
    Transport.send(message);
    }
}
