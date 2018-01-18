package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class YrMo implements Serializable
{
  public static String[] sMonthNames =
  {
      "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
  };

  private String mDisplay;
  private int mYear, mMonth;

  YrMo()
  {
  }

  public YrMo(int inYear, int inMonth)
  {
    String year = String.valueOf(inYear);
    int e = year.length();
    int s = e - 2;

    mDisplay = sMonthNames[inMonth - 1] + " " + year.substring(s, e);
    mYear = inYear;
    mMonth = inMonth;
  }

  public String getDisplay()
  {
    return mDisplay;
  }

  public void setDisplay(String inDisplay)
  {
    mDisplay = inDisplay;
  }

  public int getYear()
  {
    return mYear;
  }

  public void setYear(int inYear)
  {
    mYear = inYear;
  }

  public int getMonth()
  {
    return mMonth;
  }

  public void setMonth(int inMonth)
  {
    mMonth = inMonth;
  }

  public static List<YrMo> past(int inYear, int inMonth, int inPast)
  {
    List<YrMo> ret = new ArrayList<>();

    int yr = inYear;
    int mo = inMonth;

    for (int i = 0; i < inPast; i++)
    {
      ret.add(new YrMo(yr, mo--));
      if (mo == 0)
      {
        mo = 12;
        yr--;
      }
    }

    return ret;
  }

  public static String[] toText(List<YrMo> inYm, int inSize)
  {
    String[] ret = new String[inSize];
    for (int i = 0; i < ret.length; i++)
    {
      ret[i] = inYm.get(i).getDisplay();
    }
    return ret;
  }
}