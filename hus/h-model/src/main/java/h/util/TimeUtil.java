package h.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil
{
  public static Date parse(String inPattern, String inDate)
  {
    Date ret = null;

    try
    {
      ret = new SimpleDateFormat(inPattern).parse(inDate);
    }
    catch (Exception e)
    {
      // do nothing
    }

    return ret;
  }

  public static String parse(String inPattern, Date inDate)
  {
    String ret = null;

    try
    {
      ret = new SimpleDateFormat(inPattern).format(inDate);
    }
    catch (Exception e)
    {
      // do nothing
    }

    return ret;
  }

  public static Date combine(Date inDate, String inTimeText)
  {
    return combine(inDate, "HH:mm", inTimeText);
  }

  public static Date combine(Date inDate, String inTimePattern, String inTimeText)
  {
    Date time = parse(inTimePattern, inTimeText);
    return combine(inDate, time);
  }

  @SuppressWarnings("deprecation")
  public static Date combine(Date inDate, Date inTime)
  {
    Calendar c = new GregorianCalendar();
    c.setTime(inDate);
    if (inTime != null)
    {
      c.set(Calendar.HOUR_OF_DAY, inTime.getHours());
      c.set(Calendar.MINUTE, inTime.getMinutes());
    }
    return c.getTime();
  }

  public static String parse(String inFrom, String inTo, String inDate)
  {
    String ret = null;

    try
    {
      ret = new SimpleDateFormat(inTo).format(new SimpleDateFormat(inFrom).parse(inDate));
    }
    catch (Exception e)
    {
      // do nothing
    }

    return ret;
  }

  public static String format(String inPattern, Date inDate)
  {
    String ret = null;

    try
    {
      ret = new SimpleDateFormat(inPattern).format(inDate);
    }
    catch (Exception e)
    {
      // do nothing
    }

    return ret;
  }

  public static Date parse(String inText)
  {
    Date ret = null;

    ret = parse("hh:mm a", inText);
    if (ret == null)
    {
      ret = parse("HH:mm", inText);
    }

    return ret;
  }

  public static boolean isSameDay(Date inDate1, Date inDate2)
  {
    String d1 = format("yyyy-MM-dd", inDate1);
    String d2 = format("yyyy-MM-dd", inDate2);
    return d1.equals(d2);
  }
}