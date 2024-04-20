package org.datdev.job.services.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService implements IEmailService {
        @Autowired
        private Environment env;

        public void sendEmail(Email request) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", env.getProperty("EmailHost"));
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(env.getProperty("EmailUsername"), env.getProperty("EmailPassword"));
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(env.getProperty("EmailUsername")));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(request.getTo())
                );
                message.setSubject(request.getSubject());
                message.setText(request.getBody(), "utf-8", "html");

                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

}
