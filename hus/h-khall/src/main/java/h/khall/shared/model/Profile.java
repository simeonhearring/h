package h.khall.shared.model;

@SuppressWarnings("serial")
public class Profile extends h.model.shared.Profile
{
  private String mCongNum;
  private String mEncrypt;

  private Client mClient;

  public Client getClient()
  {
    return mClient;
  }

  public void setClient(Client inClient)
  {
    mClient = inClient;
  }

  public String getCongNum()
  {
    return mCongNum;
  }

  public void setCongNum(String inCongNum)
  {
    mCongNum = inCongNum;
  }

  public String getEncrypt()
  {
    return mEncrypt;
  }

  public void setEncrypt(String inEncrypt)
  {
    mEncrypt = inEncrypt;
  }
}