package com.topshow.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
    public static void sendMail(String email, String emailMsg)
            throws AddressException, MessagingException, GeneralSecurityException {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.host", "smtp.sina.com");
        props.setProperty("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yang_bin_0619@sina.com", "THEA2018###");
            }
        };
        Session session = Session.getInstance(props, auth);

        MimeMessage mimeMessage = new MimeMessage(session);

        mimeMessage.setFrom(new InternetAddress("yang_bin_0619@sina.com"));

        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));

        mimeMessage.setSubject("用户激活");

        mimeMessage.setContent(emailMsg, "text/html;charset=utf-8");

        Transport.send(mimeMessage);
    }
}
