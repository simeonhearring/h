package h.service.email;

public abstract class AbstractEmailMessage implements EmailMessage
{
  @Override
  public String[] getReplyTo()
  {
    return null;
  }

  @Override
  public String[] getCopyAddress()
  {
    return null;
  }

  @Override
  public String[] getBlindCopyAddress()
  {
    return null;
  }

  @Override
  public Attachment[] getAttachments()
  {
    return null;
  }
}