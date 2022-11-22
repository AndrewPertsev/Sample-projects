package by.epam.heritage.ap.service.Email;

import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class EmailSender {
    private final static Logger logger = LogManager.getLogger();
    private static final String PATH_MAIL_CONFIG = "mail.properties";
    private static final String USER = "mail.user";
    private static final String PASSWORD = "mail.password";

    /**
     * Sends message
     *
     * @return boolean true if the message is sent, else false
     */
    public boolean send(String emailAddress, String subject, String messageToSend) throws ServiceException {
        boolean isSent;
        if (emailAddress == null ||  subject==null || messageToSend == null) {
            return false;
        }
        Properties properties = loadProperties(PATH_MAIL_CONFIG);
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);

        // authenticator = new Authenticator() {
        //                @Override
        //                protected PasswordAuthentication getPasswordAuthentication() {
        //                    return new PasswordAuthentication(properties.getProperty(USER),
        //                            properties.getProperty(PASSWORD));}};

        try {
            message.setFrom(new InternetAddress(properties.getProperty(USER)));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
            message.setSubject(subject);
            message.setText(messageToSend);

            Transport transport = mailSession.getTransport();
            transport.connect(properties.getProperty(USER), properties.getProperty(PASSWORD));
            transport.send(message);
            isSent = true;
            transport.close();

        } catch (MessagingException e) {
            logger.log(Level.FATAL, "Sending message failed " , e);
            throw new ServiceException(e);
        }
        return isSent;
    }

    private Properties loadProperties(String fileName) {

        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.FATAL, "Reading file error " + fileName, e);
            throw new RuntimeException(e);
        }
        return properties;
    }
}
