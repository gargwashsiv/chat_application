

package chatServer;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailService {

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String emailFromAddress = "yogeshgupta2910@gmail.com";
    private static final String emailSubjectTxt = "your login information";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public mailService(String sendTo, String emailMsgTxt) throws MessagingException{
         this.sendSSLMessage(sendTo, emailSubjectTxt, emailMsgTxt, emailFromAddress);
         System.out.println("successfully sent");
    }



    public void sendSSLMessage(String recipient, String subject, String message, String from) throws MessagingException {
        //boolean debug = true;

        Properties props = new Properties(); // all keys and value are non strings properties inherits from hashtable
        props.put("mail.smtp.host", SMTP_HOST_NAME);  //simple message transfer protocol
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("yogeshgupta2910@gmail.com", "tanmaysethi");
                    }
                });

        //session.setDebug(debug);
        Message msg = new MimeMessage(session);

        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress addressTo = new InternetAddress();
        addressTo = new InternetAddress(recipient);
        msg.setRecipient(Message.RecipientType.TO, addressTo);

        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }
}

