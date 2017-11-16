package h.service.email;

public interface EmailMessage
{
  public enum BodyType
  {
    HTML("text/html"),
    TEXT("text/plain");

    private final String mType;

    private BodyType(String inType)
    {
      mType = inType;
    }

    public String getType()
    {
      return mType;
    }
  }

  String[] getReplyTo();

  String[] getAddress();

  String[] getCopyAddress();

  String[] getBlindCopyAddress();

  String getSubject();

  BodyType getBodyType();

  String getBody();

  Attachment[] getAttachments();
}
