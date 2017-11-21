package h.service.email365;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailOffice365
{
  private static final Logger LOGGER = Logger.getAnonymousLogger();

  private static final String SMTP = "smtp.office365.com";
  private static final int PORT = 587;
  private static final String USER_NAME = "shearring@jwpub.org";
  private static final String PASSWORD = "";

  private final String from = "shearring@jwpub.org";
  private final String to = "shearring@jwpub.org";

  private final String subject = "Testing";
  private final String messageContent = "If this comes through, we are in pretty good shape.";

  public void sendEmail()
  {
    final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator()
    {
      @Override
      protected PasswordAuthentication getPasswordAuthentication()
      {
        return new PasswordAuthentication(USER_NAME, PASSWORD);
      }
    });

    try
    {
      final Message message = new MimeMessage(session);
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setFrom(new InternetAddress(from));
      message.setSubject(subject);
      message.setText(messageContent);
      message.setSentDate(new Date());
      Transport.send(message);
    }
    catch (final MessagingException ex)
    {
      LOGGER.log(Level.WARNING, "Erro ao enviar mensagem: " + ex.getMessage(), ex);
    }
  }

  public Properties getEmailProperties()
  {
    final Properties config = new Properties();
    config.put("mail.smtp.auth", "true");
    config.put("mail.smtp.starttls.enable", "true");
    config.put("mail.smtp.host", SMTP);
    config.put("mail.smtp.port", PORT);
    return config;
  }

  public static void main(final String[] args)
  {
    new SendEmailOffice365().sendEmail();
  }
}