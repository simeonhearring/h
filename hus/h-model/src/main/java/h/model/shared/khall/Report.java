package h.model.shared.khall;

import java.io.Serializable;
import java.util.Date;

import h.model.shared.khall.Roles.Role;
import h.model.shared.util.TimeUtil;

@SuppressWarnings("serial")
public class Report implements Serializable
{
  private Integer mCongId;
  private Integer mPubId;
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
  private Boolean mIncludeAllHours;
  private Double mPartialHours;
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

  public Integer getPubId()
  {
    return mPubId;
  }

  public void setPubId(Integer inPubId)
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
}