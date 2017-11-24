package h.model.shared.khall;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.annotations.GwtIncompatible;

import h.model.shared.util.MapUtil;

@SuppressWarnings("serial")
public class Meeting implements Serializable
{
  private static Assignments.Count sCount = Assignments.Count.ALL;

  private Assignments mAll = new Assignments();
  private Decade mByYear = new Decade();

  public void setCount(Assignments.Count inCount)
  {
    sCount = inCount;
  }

  @GwtIncompatible(value = "uses java.util.Calendar - server only!")
  public void setAssignments(List<Assignment> inAssignments)
  {
    for (Assignment value : inAssignments)
    {
      Calendar c = Calendar.getInstance();
      c.setTime(value.getWeekOf());
      c.setFirstDayOfWeek(Calendar.MONDAY);

      int year = c.get(Calendar.YEAR);
      int month = c.get(Calendar.MONTH);
      int week = c.get(Calendar.WEEK_OF_MONTH);

      mByYear.add(year, month, week, value);
      mAll.add(value);
    }
  }

  public List<Assignment> gHistory(Long inPersonId)
  {
    return mAll.gHistory(inPersonId, 10);
  }

  public Year getYear(int inYear)
  {
    return mByYear.g(inYear);
  }

  /*
   * inMonth = 0 - 11 (i.e. January is 0)
   */
  public Month getMonth(int inYear, int inMonth)
  {
    return mByYear.g(inYear).g(inMonth);
  }

  /*
   * inWeek = 0,1,2,3,4 (i.e. First week is 0)
   */
  public Week getWeek(int inYear, int inMonth, int inWeek)
  {
    return getMonth(inYear, inMonth).g(inWeek);
  }

  public static class Decade extends Setup<Year> implements Serializable, ISetup
  {
    @Override
    public Year create(Date inWeekOf)
    {
      return new Year();
    }

    public void add(int inYear, int inMonth, int inWeek, Assignment inValue)
    {
      setup(inYear, inValue.getWeekOf());
      mMap.get(inYear).add(inMonth, inWeek, inValue);
    }
  }

  public static class Year extends Setup<Month> implements Serializable, ISetup
  {
    @Override
    public Month create(Date inWeekOf)
    {
      return new Month();
    }

    public void add(int inMonth, int inWeek, Assignment inValue)
    {
      setup(inMonth, inValue.getWeekOf());
      mMap.get(inMonth).add(inWeek, inValue);
    }

    public Double[] gCountM()
    {
      Double[] ret = new Double[12];
      for (int i = 0; i < ret.length; i++)
      {
        ret[i] = g(i).gCount();
      }
      return ret;
    }

    public Double[] gAssignedM()
    {
      Double[] ret = new Double[12];
      for (int i = 0; i < ret.length; i++)
      {
        ret[i] = g(i).gAssigned();
      }
      return ret;
    }
  }

  public static class Month extends Setup<Week> implements Serializable, ISetup, Comparator<Integer>
  {
    private Map<Integer, Integer> mKeys;

    @Override
    public Week create(Date inWeekOf)
    {
      return new Week(inWeekOf);
    }

    public void add(int inWeek, Assignment inValue)
    {
      setup(inWeek, inValue.getWeekOf());
      mMap.get(inWeek).add(inValue);
    }

    public double gAssigned()
    {
      int ret = 0;
      for (Entry<Integer, Week> value : mMap.entrySet())
      {
        ret += value.getValue().gAssigned();
      }
      return ret;
    }

    private int key(int inKey)
    {
      int ret = -1;

      if (mKeys == null)
      {
        mKeys = MapUtil.organize(mMap.keySet());
      }

      if (mKeys.containsKey(inKey))
      {
        ret = mKeys.get(inKey);
      }

      return ret;
    }

    @Override
    public Week g(int inKey)
    {
      return super.g(key(inKey));
    }

    @Override
    public int compare(Integer inO1, Integer inO2)
    {
      return inO1.compareTo(inO2);
    }
  }

  public static class Week implements Serializable, ISetup
  {
    private Date mOf;
    private Assignments mAssignment = new Assignments();

    Week()
    {
    }

    public Week(Date inOf)
    {
      mOf = inOf;
    }

    public Date getOf()
    {
      return mOf;
    }

    @Override
    public double gCount()
    {
      return mAssignment.gCount(sCount);
    }

    public int gAssigned()
    {
      return mAssignment.gAssigned(sCount);
    }

    public void add(Assignment inValue)
    {
      mAssignment.add(inValue);
    }

    public List<Assignment> gAssignment()
    {
      return mAssignment.gAssignments();
    }

    public Assignment get(Part inPpart, Hall inHall)
    {
      return mAssignment.gAssignment(inPpart, inHall);
    }

    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      builder.append("Week [mOf=");
      builder.append(mOf);
      builder.append(", mAssignment=");
      builder.append(mAssignment);
      builder.append("]");
      return builder.toString();
    }
  }

  public interface ISetup
  {
    double gCount();
  }

  public static abstract class Setup<V extends ISetup> implements Serializable
  {
    protected Map<Integer, V> mMap = new HashMap<>();

    public abstract V create(Date inWeekOf);

    public void setup(int inKey, Date inValue)
    {
      if (!mMap.containsKey(inKey))
      {
        mMap.put(inKey, create(inValue));
      }
    }

    public double gCount()
    {
      int ret = 0;
      for (Entry<Integer, V> value : mMap.entrySet())
      {
        ret += value.getValue().gCount();
      }
      return ret;
    }

    public boolean empty()
    {
      return size() == 0;
    }

    public int size()
    {
      return mMap.size();
    }

    public V g(int inKey)
    {
      V ret = null;
      if (mMap.containsKey(inKey))
      {
        ret = mMap.get(inKey);
      }
      else
      {
        ret = create(null);
      }
      return ret;
    }

    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      builder.append("Setup [mMap=");
      builder.append(mMap);
      builder.append("]");
      return builder.toString();
    }
  }
}