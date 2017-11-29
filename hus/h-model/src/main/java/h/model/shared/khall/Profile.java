package h.model.shared.khall;

import h.model.shared.util.TimeUtil;

@SuppressWarnings("serial")
public class Profile extends h.model.shared.Profile
{
  private Integer mCongId;
  private String mCongNum;
  private String mEncrypt;
  private Integer mYear = TimeUtil.currentYear();
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

  public Integer getCongId()
  {
    return mCongId;
  }

  public void setCongId(Integer inCongId)
  {
    mCongId = inCongId;
  }

  public int[] gYears()
  {
    return new int[]
    {
        mYear, mYear - 1
    };
  }

  public void setYear(Integer inYear)
  {
    mYear = inYear;
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