package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h.model.shared.khall.Roles.Role;
import h.model.shared.util.StringUtil;
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

  private static double ensure(Report inReport)
  {
    return ensure(inReport.getHours(), inReport.getPartialHours());
  }

  private static double ensure(Integer inInteger, Double inDouble)
  {
    return ensure(inInteger) + ensure(inDouble);
  }

  public String getServiceYear()
  {
    return getServiceYear(mYear, mMonth);
  }

  public static String getServiceYear(int inYear, int inMonth)
  {
    String ret = null;
    switch (inMonth)
    {
      case 9:
      case 10:
      case 11:
      case 12:
        ret = inYear + "/" + (inYear + 1);
        break;
      default:
        ret = inYear - 1 + "/" + inYear;
        break;
    }
    return ret;
  }

  public static class Stat implements Serializable
  {
    private StatsIds mInactive;
    private StatsIds mIrregular;
    private StatsIds mBelowThreshold;
    private StatsIds mReactivated;
    private StatsIds mAboveThreshold;

    public void setSize(int inSize)
    {
      mInactive = new StatsIds();
      mInactive.setSize(inSize);

      mIrregular = new StatsIds();
      mIrregular.setSize(inSize);

      mBelowThreshold = new StatsIds();
      mBelowThreshold.setSize(inSize);

      mAboveThreshold = new StatsIds();
      mAboveThreshold.setSize(inSize);

      mReactivated = new StatsIds();
      mReactivated.setSize(inSize);
    }

    public StatsIds getInactive()
    {
      return mInactive;
    }

    public StatsIds getIrregular()
    {
      return mIrregular;
    }

    public StatsIds getBelowThreshold()
    {
      return mBelowThreshold;
    }

    public StatsIds getAboveThreshold()
    {
      return mAboveThreshold;
    }

    public StatsIds getReactivated()
    {
      return mReactivated;
    }

    public void inactive(YrMo inYm, int inPos, Long inPersonId)
    {
      mInactive.add(inPos, inYm, inPersonId);
    }

    public void irregular(YrMo inYm, int inPos, Long inPersonId)
    {
      mIrregular.add(inPos, inYm, inPersonId);
    }

    public void belowThreshold(YrMo inYm, int inPos, Long inPersonId)
    {
      mBelowThreshold.add(inPos, inYm, inPersonId);
    }

    public void aboveThreshold(YrMo inYm, int inPos, Long inPersonId)
    {
      mAboveThreshold.add(inPos, inYm, inPersonId);
    }

    public void reactivated(YrMo inYm, int inPos, Long inPersonId)
    {
      mReactivated.add(inPos, inYm, inPersonId);
    }
  }

  public static class PubRange implements Serializable
  {
    private DoubleRange mHours;
    private DoubleRange mBibleStudies;
    private DoubleRange mReturnVisits;
    private DoubleRange mPlacements;
    private DoubleRange mVideoShowings;

    public void setSize(int inSize)
    {
      mHours = new DoubleRange();
      mHours.setSize(inSize);
      mBibleStudies = new DoubleRange();
      mBibleStudies.setSize(inSize);
      mReturnVisits = new DoubleRange();
      mReturnVisits.setSize(inSize);
      mPlacements = new DoubleRange();
      mPlacements.setSize(inSize);
      mVideoShowings = new DoubleRange();
      mVideoShowings.setSize(inSize);
    }

    public double gAverage(int inCount, Double[] inRange)
    {
      double sum = 0;
      for (int i = 0; i < inCount; i++)
      {
        sum += inRange[i];
      }
      return sum / inCount;
    }

    public void setValue(int inPos, Report inReport)
    {
      mHours.setValue(inPos, inReport.gHours());
      mBibleStudies.setValue(inPos, inReport.getBibleStudies());
      mReturnVisits.setValue(inPos, inReport.getReturnVisits());
      mPlacements.setValue(inPos, inReport.getPlacements());
      mVideoShowings.setValue(inPos, inReport.getVideoShowings());
    }

    public void addValue(int inPos, Report inReport)
    {
      mHours.addValue(inPos, inReport.gHours());
      mBibleStudies.addValue(inPos, inReport.getBibleStudies());
      mReturnVisits.addValue(inPos, inReport.getReturnVisits());
      mPlacements.addValue(inPos, inReport.getPlacements());
      mVideoShowings.addValue(inPos, inReport.getVideoShowings());
    }

    public Double[] getHours()
    {
      return mHours.mRange;
    }

    public Double[] getBibleStudies()
    {
      return mBibleStudies.mRange;
    }

    public Double[] getReturnVisits()
    {
      return mReturnVisits.mRange;
    }

    public Double[] getPlacements()
    {
      return mPlacements.mRange;
    }

    public Double[] getVideoShowings()
    {
      return mVideoShowings.mRange;
    }
  }

  public static class DoubleRange implements Serializable
  {
    private Double[] mRange;

    public void setSize(int inSize)
    {
      mRange = new Double[inSize];
      for (int i = 0; i < mRange.length; i++)
      {
        mRange[i] = 0.0;
      }
    }

    public void setValue(int inPos, Number inValue)
    {
      mRange[inPos] = inValue == null ? 0.0 : inValue.doubleValue();
    }

    public void addValue(int inPos, Number inValue)
    {
      mRange[inPos] += inValue == null ? 0.0 : inValue.doubleValue();
    }
  }

  public static class StatsIds implements Serializable
  {
    private Double[] mValues;
    private Map<String, List<Long>> mIds;

    public void setSize(int inSize)
    {
      mIds = new HashMap<>();
      mValues = new Double[inSize];
      for (int i = 0; i < mValues.length; i++)
      {
        mValues[i] = 0.0;
      }
    }

    public Double[] getValues()
    {
      return mValues;
    }

    public Map<String, List<Long>> getIds()
    {
      return mIds;
    }

    public void add(int inPos, YrMo inYm, Long inPersonId)
    {
      mValues[inPos] += 1;
      if (!mIds.containsKey(inYm.getDisplay()))
      {
        mIds.put(inYm.getDisplay(), new ArrayList<Long>());
      }
      mIds.get(inYm.getDisplay()).add(inPersonId);
    }
  }

  public static class Total implements Serializable
  {
    private int mCount;
    private int mActiveCount;
    private int mPlacements;
    private int mVideoShowings;
    private double mHours;
    private int mReturnVisits;
    private int mBibleStudies;
    private int mCreditHours;
    private boolean mReactivated;

    public void add(Report inReport)
    {
      mCount++;
      mPlacements += ensure(inReport.getPlacements());
      mVideoShowings += ensure(inReport.getVideoShowings());
      double hours = ensure(inReport.getHours(), inReport.getPartialHours());
      mHours += hours;
      mReturnVisits += ensure(inReport.getReturnVisits());
      mBibleStudies += ensure(inReport.getBibleStudies());
      mCreditHours += ensure(inReport.getCreditHours());
      if (mHours > 0.0)
      {
        mActiveCount++;
      }
      if (hours > 0.0)
      {
        mReactivated = mCount == 1 ? true : false;
      }
    }

    public boolean isReactivated()
    {
      return mReactivated;
    }

    public int getActiveCount()
    {
      return mActiveCount;
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
      return gPlacementsAvg(mActiveCount);
    }

    public double gVideoShowingsAvg()
    {
      return gVideoShowingsAvg(mActiveCount);
    }

    public double gHoursAvg()
    {
      return gHoursAvg(mActiveCount);
    }

    public double gReturnVisitsAvg()
    {
      return gReturnVisitsAvg(mActiveCount);
    }

    public double gBibleStudiesAvg()
    {
      return gBibleStudiesAvg(mActiveCount);
    }

    public double gCreditHoursAvg()
    {
      return gCreditHoursAvg(mActiveCount);
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

    public boolean isInactive()
    {
      return mHours == 0.0;
    }

    public boolean isIrregular()
    {
      return mActiveCount < 6;
    }

    public boolean isBelowThreshold(double inThreshold)
    {
      return gHoursAvg(mCount) < inThreshold;
    }

    public boolean isBelowThreshold(Report inReport, double inThreshold)
    {
      return ensure(inReport) <= inThreshold;
    }

    public boolean isAboveThreshold(Report inReport, double inThreshold)
    {
      return ensure(inReport) > inThreshold;
    }
  }

  public boolean cleanRemarks()
  {
    boolean ret = mRemarks != null && mRemarks.startsWith("[web saved on");
    if (ret)
    {
      int index = mRemarks.indexOf("]");
      mRemarks = StringUtil.emptyToNull(mRemarks.substring(index + 1, mRemarks.length()).trim());
    }
    return ret;
  }

  public String getKey()
  {
    return mYear + "-" + mMonth;
  }

  public Double gHours()
  {
    return ensure(this);
  }
}