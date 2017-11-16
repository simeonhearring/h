package h.service.email;

public class Attachment
{
  private String mName;
  private String mType;
  private byte[] mBytes;

  Attachment()
  {
  }

  public Attachment(String inName, String inType, byte[] inBytes)
  {
    mName = inName;
    mType = inType;
    mBytes = inBytes;
  }

  public String getName()
  {
    return mName;
  }

  public byte[] getBytes()
  {
    return mBytes;
  }

  public String getType()
  {
    return mType;
  }
}