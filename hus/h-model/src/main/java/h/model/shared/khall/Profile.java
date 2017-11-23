package h.model.shared.khall;

@SuppressWarnings("serial")
public class Profile extends h.model.shared.Profile
{
  private Integer mCongId;
  private String mCongNme;
  private String mCongNum;
  private String mEncrypt;
  private Integer mYear, mMonth;
  private Assignments.Count mCount;

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

  public Integer getCongId()
  {
    return mCongId;
  }

  public void setCongId(Integer inCongId)
  {
    mCongId = inCongId;
  }

  public Integer getYear()
  {
    return mYear;
  }

  public void setYear(Integer inYear)
  {
    mYear = inYear;
  }

  public Integer getMonth()
  {
    return mMonth;
  }

  public void setMonth(Integer inMonth)
  {
    mMonth = inMonth;
  }

  public Assignments.Count getCount()
  {
    return mCount;
  }

  public void setCount(Assignments.Count inCount)
  {
    mCount = inCount;
  }
}