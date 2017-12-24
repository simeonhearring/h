package h.model.shared.khall;

import java.io.Serializable;
import java.util.Date;

import h.model.shared.khall.Roles.Role;
import h.model.shared.util.TimeUtil;

@SuppressWarnings("serial")
public class Report implements Serializable
{
  private Integer mCongId;
  private Long mPubId;
  private Integer mYear;
  private Integer mMonth;
  private Date mSendDate;
  private Boolean mNoActivity;
  private Integer mPlacements;
  private Integer mVideoShowings;
  private Integer mHours;
  private Integer mReturnVisits;
  private Integer mBibleStudies;
  private Integer mCreditHours;
  private Double mPartialHours;
  private Boolean mIncludeAllHours;
  private String mRemarks;
  private Role mType;

  private Integer mCount;

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

  public Boolean getNoActivity()
  {
    return mNoActivity;
  }

  public void setNoActivity(Boolean inNoActivity)
  {
    mNoActivity = inNoActivity;
  }

  public Integer getPlacements()
  {
    return mPlacements;
  }

  public void setPlacements(Integer inPlacements)
  {
    mPlacements = inPlacements;
  }

  public Integer getVideoShowings()
  {
    return mVideoShowings;
  }

  public void setVideoShowings(Integer inVideoShowings)
  {
    mVideoShowings = inVideoShowings;
  }

  public Integer getHours()
  {
    return mHours;
  }

  public void setHours(Integer inHours)
  {
    mHours = inHours;
  }

  public Integer getReturnVisits()
  {
    return mReturnVisits;
  }

  public void setReturnVisits(Integer inReturnVisits)
  {
    mReturnVisits = inReturnVisits;
  }

  public Integer getBibleStudies()
  {
    return mBibleStudies;
  }

  public void setBibleStudies(Integer inBibleStudies)
  {
    mBibleStudies = inBibleStudies;
  }

  public Integer getCreditHours()
  {
    return mCreditHours;
  }

  public void setCreditHours(Integer inRbcHours)
  {
    mCreditHours = inRbcHours;
  }

  public Boolean getIncludeAllHours()
  {
    return mIncludeAllHours;
  }

  public void setIncludeAllHours(Boolean inIncludeAllHours)
  {
    mIncludeAllHours = inIncludeAllHours;
  }

  public Double getPartialHours()
  {
    return mPartialHours;
  }

  public void setPartialHours(Double inPartialHours)
  {
    mPartialHours = inPartialHours;
  }

  public String getRemarks()
  {
    return mRemarks;
  }

  public void setRemarks(String inRemarks)
  {
    mRemarks = inRemarks;
  }

  public Role getType()
  {
    return mType;
  }

  public void setType(Role inType)
  {
    mType = inType;
  }

  public void setSendDate(Date inSendDate)
  {
    mSendDate = inSendDate;
  }

  public Integer getCount()
  {
    return mCount;
  }

  public void setCount(Integer inCount)
  {
    mCount = inCount;
  }

  public Integer getCongId()
  {
    return mCongId;
  }

  public void setCongId(Integer inCongId)
  {
    mCongId = inCongId;
  }

  public Long getPubId()
  {
    return mPubId;
  }

  public void setPubId(Long inPubId)
  {
    mPubId = inPubId;
  }

  public Date gDate()
  {
    return TimeUtil.getFirstOfMonth(mYear, mMonth);
  }

  public Date gSendDate()
  {
    return mSendDate == null ? gDate() : mSendDate;
  }

  public boolean isNoActivity()
  {
    return mNoActivity != null && mNoActivity;
  }

  private static int ensure(Integer inValue)
  {
    return inValue == null ? 0 : inValue;
  }

  private static double ensure(Double inValue)
  {
    return inValue == null ? 0.0 : inValue;
  }

  private static double ensure(Integer inInteger, Double inDouble)
  {
    return ensure(inInteger) + ensure(inDouble);
  }

  public static class Total implements Serializable
  {
    private int mCount;
    private int mPlacements;
    private int mVideoShowings;
    private double mHours;
    private int mReturnVisits;
    private int mBibleStudies;
    private int mCreditHours;

    public void add(Report inReport)
    {
      mPlacements += ensure(inReport.getPlacements());
      mVideoShowings += ensure(inReport.getVideoShowings());
      mHours += ensure(inReport.getHours(), inReport.getPartialHours());
      mReturnVisits += ensure(inReport.getReturnVisits());
      mBibleStudies += ensure(inReport.getBibleStudies());
      mCreditHours += ensure(inReport.getCreditHours());
      if (mHours > 0.0)
      {
        mCount++;
      }
    }

    public int getCount()
    {
      return mCount;
    }

    public int getPlacements()
    {
      return mPlacements;
    }

    public int getVideoShowings()
    {
      return mVideoShowings;
    }

    public double getHours()
    {
      return mHours;
    }

    public int getReturnVisits()
    {
      return mReturnVisits;
    }

    public int getBibleStudies()
    {
      return mBibleStudies;
    }

    public int getCreditHours()
    {
      return mCreditHours;
    }

    public double gPlacementsAvg()
    {
      return gPlacementsAvg(mCount);
    }

    public double gVideoShowingsAvg()
    {
      return gVideoShowingsAvg(mCount);
    }

    public double gHoursAvg()
    {
      return gHoursAvg(mCount);
    }

    public double gReturnVisitsAvg()
    {
      return gReturnVisitsAvg(mCount);
    }

    public double gBibleStudiesAvg()
    {
      return gBibleStudiesAvg(mCount);
    }

    public double gCreditHoursAvg()
    {
      return gCreditHoursAvg(mCount);
    }

    public double gPlacementsAvg(int inCount)
    {
      return mPlacements / inCount;
    }

    public double gVideoShowingsAvg(int inCount)
    {
      return mVideoShowings / inCount;
    }

    public double gHoursAvg(int inCount)
    {
      return mHours / inCount;
    }

    public double gReturnVisitsAvg(int inCount)
    {
      return mReturnVisits / inCount;
    }

    public double gBibleStudiesAvg(int inCount)
    {
      return mBibleStudies / inCount;
    }

    public double gCreditHoursAvg(int inCount)
    {
      return mCreditHours / inCount;
    }
  }
}