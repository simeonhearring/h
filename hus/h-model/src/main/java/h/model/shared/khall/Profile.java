package h.model.shared.khall;

@SuppressWarnings("serial")
public class Profile extends h.model.shared.Profile
{
  private String mCongNme;
  private String mCongNum;
  private String mEncrypt;

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

  public String getCongNme()
  {
    return mCongNme;
  }

  public void setCongNme(String inCongNme)
  {
    mCongNme = inCongNme;
  }
}