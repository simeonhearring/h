package h.model.shared.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

@SuppressWarnings("deprecation")
public final class TimeUtil
{
  public static Date getFirstOfMonth(int inYear, int inMonth)
  {
    return new Date(inYear - 1900, inMonth - 1, 1);
  }

  public static Date getFirstOfMonth()
  {
    Date date = new Date();
    return new Date(date.getYear(), date.getMonth(), 1);
  }

  public static Date toDate(String inString)
  {
    if (inString == null)
    {
      return null;
    }

    return new Date(Long.valueOf(inString));
  }

  public static Date getDate(int inYear, int inMonth, int inDay)
  {
    return new Date(inYear - 1900, inMonth - 1, inDay);
  }

  public static int currentYear()
  {
    return new Date().getYear() + 1900;
  }

  public static int currentServiceMonth()
  {
    return new Date().getMonth();
  }

  public static Long toLong(Date inDate)
  {
    return inDate == null ? 0L : inDate.getTime();
  }

  public static int getAge(Date inBirth)
  {
    int ret = 0;
    if (inBirth != null)
    {
      long timeBetween = new Date().getTime() - inBirth.getTime();
      double yearsBetween = timeBetween / 3.156e+10;
      ret = (int) Math.floor(yearsBetween);
    }
    return ret;
  }

  public static String getDaySuffix(int inDay)
  {
    String s = String.valueOf(inDay);

    if (s.endsWith("1"))
    {
      return "st";
    }
    else if (s.endsWith("2"))
    {
      return "nd";
    }
    else if (s.endsWith("3"))
    {
      return "rd";
    }
    else
    {
      return "th";
    }
  }

  public static int getCurrentMonth()
  {
    return Integer.valueOf(DateTimeFormat.getFormat("MM").format(new Date()));
  }

  public static int getCurrentYear()
  {
    return Integer.valueOf(DateTimeFormat.getFormat("yyyy").format(new Date()));
  }

  public static double yearDifference(Date inDate)
  {
    double year = Double.valueOf(DateTimeFormat.getFormat("yyyy.M").format(inDate));
    double current = Double.valueOf(getCurrentYear() + "." + getCurrentMonth());
    double ret = current - year;
    return ret;
  }

  public static String format(String inPattern, Date inValue)
  {
    return DateTimeFormat.getFormat(inPattern).format(inValue);
  }

  public static Date parse(String inPattern, String inValue)
  {
    return DateTimeFormat.getFormat(inPattern).parse(inValue);
  }

  public static Date getEndOfMonth(int inYear, int inMonth)
  {
    return new Date(inYear - 1900, inMonth - 1, getLastOfMonth(inYear, inMonth));
  }

  public static int getLastOfMonth(int inYear, int inMonth)
  {
    int ret = 31;
    switch (inMonth)
    {
      case 2:
        ret = isLeapYear(inYear) ? 29 : 28;
        break;
      case 9:
      case 4:
      case 6:
      case 11:
        ret = 30;
        break;
      default:
        break;
    }
    return ret;
  }

  public static boolean isLeapYear(int inYear)
  {
    return inYear % 400 == 0 || inYear % 4 == 0 && inYear % 100 != 0;
  }

  public static String dateRange(Date inValue)
  {
    Date end = new Date(inValue.getTime() + 518400000); // 6 days
    String pattern = inValue.getMonth() == end.getMonth() ? "dd" : "MMMM dd";
    return (format("MMMM dd-", inValue) + format(pattern, end)).toUpperCase();
  }
}