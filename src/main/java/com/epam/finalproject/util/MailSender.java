package com.epam.finalproject.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    private static final Logger LOGGER = LogManager.getLogger(MailSender.class);
    private static final String ENCODING = "utf-8";
    private final String sendToEmail;
    private final String mailSubject;
    private final String mailText;
    private final Properties properties;
    private MimeMessage message;

    public MailSender(String sendToEmail, String mailSubject, String mailText,
                      Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    public void sendMail() {
        try {
            initMessage();
            Transport.send(message);
        } catch (AddressException e) {
            LOGGER.error("Invalid email address: ", e);
        } catch (MessagingException e) {
            LOGGER.error("Error generating or sending message: ", e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession;
        mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setText(mailText, ENCODING);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }
}
