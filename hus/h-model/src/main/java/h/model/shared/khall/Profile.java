package h.model.shared.khall;

import java.util.Map;

import h.model.shared.util.TimeUtil;

@SuppressWarnings("serial")
public class Profile extends h.model.shared.Profile
{
  public enum Security
  {
    PERSON_DETAIL,
    PERSON_CONTACT_INFO,
    PERSON_OPTIONS,
    PERSON_FSG,
    REPORT,
    OCLM;
  }

  private String mEncrypt;

  private Integer mCongId;
  private String mCongNum;
  private Integer mYear = TimeUtil.currentYear();
  private Assignments.Count mCount;
  private Double mThreshold;

  private Map<Security, Boolean> mSecurity;

  private int[] mCsym;

  public String gEncrypt()
  {
    return mEncrypt;
  }

  public void setEncrypt(String inEncrypt)
  {
    mEncrypt = inEncrypt;
  }

  public String getCongNum()
  {
    return mCongNum;
  }

  public void setCongNum(String inCongNum)
  {
    mCongNum = inCongNum;
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
        mYear, mYear + 1
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

  public void setThreshold(double inThreshold)
  {
    mThreshold = inThreshold;
  }

  public Double getThreshold()
  {
    return mThreshold;
  }

  public double gThreshold()
  {
    return mThreshold == null ? 5.0 : mThreshold;
  }

  public void setCsym(int... inCsym)
  {
    mCsym = inCsym;
  }

  public int gCurrentServiceYear()
  {
    return mCsym[0];
  }

  public int gCurrentServiceMonth()
  {
    return mCsym[1];
  }

  public void setSecurity(Map<Security, Boolean> inSecurity)
  {
    mSecurity = inSecurity;
  }

  public Map<Security, Boolean> getSecurity()
  {
    return mSecurity;
  }

  public boolean isEdit(Security inSecurity)
  {
    return mSecurity.containsKey(inSecurity) && mSecurity.get(inSecurity);
  }

  public boolean canReset()
  {
    String password = gPassword();
    return password != null && password.startsWith("password:");
  }

  public String gPass()
  {
    String password = gPassword();
    return password != null ? password.substring("password:".length(), password.length()) : password;
  }
}