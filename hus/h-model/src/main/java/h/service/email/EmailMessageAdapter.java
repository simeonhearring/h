package h.service.email;

public abstract class EmailMessageAdapter implements EmailMessage
{
  @Override
  public String[] getAddress()
  {
    return null;
  }

  @Override
  public String getSubject()
  {
    return null;
  }

  @Override
  public String getBody()
  {
    return null;
  }

  @Override
  public Attachment[] getAttachments()
  {
    return null;
  }
}