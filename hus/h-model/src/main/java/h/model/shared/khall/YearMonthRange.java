package h.model.shared.khall;

import java.util.Date;

import h.model.shared.util.TimeUtil;

public class YearMonthRange
{
  private int mYearFrom;
  private int mMonthFrom;

  private int mYearTo;
  private int mMonthTo;

  private int mServiceYear;

  public YearMonthRange()
  {
    this(new Date());
  }

  @SuppressWarnings("deprecation")
  public YearMonthRange(Date inDate)
  {
    this(inDate.getMonth() + 1, inDate.getYear() + 1900);
  }

  public YearMonthRange(int inMonth, int inYear)
  {
    int month = inMonth;
    mMonthTo = month - 1;
    mYearTo = inYear;

    mServiceYear = mYearTo;
    if (month >= 1 && month <= 8)
    {
      mServiceYear = mServiceYear - 1;
    }

    if (mMonthTo == 0)
    {
      mMonthTo = 12;
      mYearTo = mYearTo - 1;
    }

    mYearFrom = mYearTo - 1;
    mMonthFrom = mMonthTo;
  }

  // public YearMonthRange(int inPastMonths)
  // {
  // mMonthTo = Integer.parseInt(new
  // SimpleDateFormat("MM").format(getCurrentDate()));
  // mYearTo = Integer.parseInt(new
  // SimpleDateFormat("yyyy").format(getCurrentDate()));
  // if (mMonthTo == 1)
  // {
  // mMonthTo -= 1;
  // mYearTo -= 1;
  // }
  //
  // mMonthFrom -= 6;
  //
  // }

  public YearMonthRange(int inYearFrom, int inMonthFrom, int inYearTo, int inMonthTo)
  {
    mYearFrom = inYearFrom;
    mMonthFrom = inMonthFrom;
    mYearTo = inYearTo;
    mMonthTo = inMonthTo;
  }

  public int getServiceYear()
  {
    return mServiceYear;
  }

  public int getYearFrom()
  {
    return mYearFrom;
  }

  public int getYearTo()
  {
    return mYearTo;
  }

  public int getMonthFrom()
  {
    return mMonthFrom;
  }

  public int getMonthTo()
  {
    return mMonthTo;
  }

  public void setYearFrom(int inYearFrom)
  {
    mYearFrom = inYearFrom;
  }

  public void setMonthFrom(int inMonthFrom)
  {
    mMonthFrom = inMonthFrom;
  }

  public void setYearTo(int inYearTo)
  {
    mYearTo = inYearTo;
  }

  public void setMonthTo(int inMonthTo)
  {
    mMonthTo = inMonthTo;
  }

  public Date getTo()
  {
    return TimeUtil.getFirstOfMonth(mYearTo, mMonthTo);
  }

  public Date getFrom()
  {
    return TimeUtil.getFirstOfMonth(mYearFrom, mMonthFrom);
  }

  public Date getFirstOfMonth()
  {
    return TimeUtil.getFirstOfMonth(getYearTo(), getMonthTo());
  }

  public Date getFirstOfServiceYear()
  {
    return TimeUtil.getFirstOfMonth(mServiceYear, 9);
  }

  protected Date getCurrentDate()
  {
    return new Date();
  }

  public static int getServiceYear(final int inYear, final int inMonth)
  {
    Date date = TimeUtil.getFirstOfMonth(inYear, inMonth);
    YearMonthRange range = new YearMonthRange(date);
    return range.getServiceYear();
  }

  public static YearMonthRange getYearMonthRange(final int inToYear, final int inToMonth)
  {
    Date date = TimeUtil.getFirstOfMonth(inToYear, inToMonth + 1);
    YearMonthRange ret = new YearMonthRange(date);
    return ret;
  }

  @SuppressWarnings("deprecation")
  public static Date getFirstOfServiceYear(Date inDate, int inYearModifer)
  {
    int year = inDate.getYear() + 1900;
    int month = inDate.getMonth() + 1;

    year = yearOfServiceYear(year, month) + inYearModifer;

    return TimeUtil.getFirstOfMonth(year, 9);
  }

  public static Date getFirstOfServiceYear(int inYear, int inMonth)
  {
    inYear = yearOfServiceYear(inYear, inMonth);
    return TimeUtil.getFirstOfMonth(inYear, 9);
  }

  public static int yearOfServiceYear(int inYear, int inMonth)
  {
    if (inMonth >= 1 && inMonth <= 8)
    {
      inYear = inYear - 1;
    }
    return inYear;
  }

  @SuppressWarnings("deprecation")
  public static YearMonthRange getSixMo()
  {
    Date now = new Date();
    Date moAgo = new Date(now.getTime() - 18408222000L); // 7 mo ago

    int fromYear = moAgo.getYear() + 1900;
    int fromMonth = moAgo.getMonth() + 1;
    int toYear = now.getYear() + 1900;
    int toMonth = now.getMonth();

    return new YearMonthRange(fromYear, fromMonth, toYear, toMonth);
  }
}