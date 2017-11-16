package h.service.email;

import java.util.Map.Entry;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import h.model.shared.util.StringUtil;

public class SendEmailService implements EmailService
{
  private final Properties mProperties;

  private String mFrom = "support@husoftware.net";

  private String mPass = "smooth20";

  // mail.smtp.host=smtp.gmail.com|mail.smtp.port=125|mail.smtp.socketFactory.port=465|mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
  // mail.smtp.host=mail.husoftware.net|mail.smtp.port=2525|mail.smtp.socketFactory.port=2525|mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
  public SendEmailService()
  {
    mProperties = new Properties();
    mProperties.put("mail.smtp.host", "mail.husoftware.net");
    mProperties.put("mail.smtp.auth", "true");
    mProperties.put("mail.smtp.port", "2525");
  }

  public static void main(String[] args)
  {
    SendEmailService service = new SendEmailService();
    // service.setHost("mail.appt.biz");
    // service.setPass("B[O@*!c491ci^?UaSq");
    // service.setFrom("simeon@appt.biz");
    String msg = service.sendEmail(new AbstractEmailMessage()
    {
      @Override
      public String getSubject()
      {
        return "hello2";
      }

      @Override
      public String getBody()
      {
        return "<table><tr><td><input type=\"number\" style=\"width: 50px;\" id=\"20146bk\" /></td></tr></table>";
      }

      @Override
      public BodyType getBodyType()
      {
        return BodyType.HTML;
      }

      @Override
      public String[] getAddress()
      {
        return "6155455137@mms.att.net,simeonlhearring@gmail.com".split(",");
      }
    });
    System.out.println(msg);
  }

  public String getProperties()
  {
    StringBuilder sb = new StringBuilder();

    for (Entry<Object, Object> value : mProperties.entrySet())
    {
      sb.append(value.getKey()).append("=").append(value.getValue()).append("<br />");
    }
    sb.append("mFrom=").append(mFrom).append("<br />");
    sb.append("mPass=").append(mPass.replaceAll("[^0-9]+", "*")).append("<br />");
    return sb.toString();
  }

  public void setFrom(String inFrom)
  {
    mFrom = inFrom;
  }

  public void setPass(String inPass)
  {
    mPass = inPass;
  }

  public void setHost(String inHost)
  {
    mProperties.put("mail.smtp.host", inHost);
  }

  public void setAuth(String inAuth)
  {
    mProperties.put("mail.smtp.auth", inAuth);
  }

  public void setPort(String inPort)
  {
    mProperties.put("mail.smtp.port", inPort);
  }

  public void setProperty(String inProperty)
  {
    String[] values = inProperty.split("=");
    mProperties.put(values[0], values[1]);
  }

  public void setRemove(String inKey)
  {
    mProperties.remove(inKey);
  }

  @Override
  public String sendEmail(final EmailMessage inEmailMessage) throws EmailException
  {
    Session session = Session.getInstance(mProperties, new javax.mail.Authenticator()
    {
      @Override
      protected PasswordAuthentication getPasswordAuthentication()
      {
        String from = mFrom, pass = mPass;
        return new PasswordAuthentication(from, pass);
      }
    });

    try
    {
      MimeMultipart mimeMultipart = new MimeMultipart();

      String html = inEmailMessage.getBody();
      if (StringUtil.isValid(html))
      {
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(inEmailMessage.getBody(), inEmailMessage.getBodyType().getType());
        mimeMultipart.addBodyPart(textPart);
      }

      Attachment[] attach = inEmailMessage.getAttachments();
      if (attach != null && attach.length > 0)
      {
        for (Attachment value : attach)
        {
          MimeBodyPart attachmentPart = new MimeBodyPart();
          attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(value.getBytes(),
              "application/" + value.getType())));
          attachmentPart.setFileName(value.getName() + "." + value.getType());
          mimeMultipart.addBodyPart(attachmentPart);
        }
      }

      Message message = new MimeMessage(session);

      message.setFrom(new InternetAddress(mFrom));
      message.setRecipients(Message.RecipientType.TO, Email.convert(inEmailMessage.getAddress()));
      Address[] cc = Email.convert(inEmailMessage.getCopyAddress());
      if (cc != null)
      {
        message.setRecipients(Message.RecipientType.CC, cc);
      }
      Address[] bcc = Email.convert(inEmailMessage.getBlindCopyAddress());
      if (bcc != null)
      {
        message.setRecipients(Message.RecipientType.BCC, bcc);
      }
      Address[] replyTo = Email.convert(inEmailMessage.getReplyTo());
      if (replyTo != null)
      {
        message.setReplyTo(replyTo);
      }

      message.setSubject(inEmailMessage.getSubject());
      message.setContent(mimeMultipart);
      // message.setText(html);

      Transport.send(message);

      return "SENT";
    }
    catch (Exception e)
    {
      String msg =
          "Address: [" + inEmailMessage.getAddress() + "] Subject: [" + inEmailMessage.getSubject()
              + "] Body: [" + inEmailMessage.getBody() + "]";

      throw new EmailException("Error sending email. " + msg, e);
    }
  }
}