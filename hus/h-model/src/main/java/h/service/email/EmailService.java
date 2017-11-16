package h.service.email;

public interface EmailService
{
  String sendEmail(EmailMessage inEmailMessage) throws EmailException;
}
