package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
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
  private Assignments mAssignments;
  private Decade mDecade;

  @GwtIncompatible(value = "uses java.util.Calendar - server only!")
  public void setAssignments(List<Assignment> inAssignments)
  {
    mAssignments = new Assignments(inAssignments);
    mDecade = new Decade();
    for (Assignment value : inAssignments)
    {
      Date d = value.getWeekOf();
      Calendar c = Calendar.getInstance();
      c.setTime(d);
      c.setFirstDayOfWeek(Calendar.MONDAY);

      int year = c.get(Calendar.YEAR);
      int month = c.get(Calendar.MONTH);
      int week = c.get(Calendar.WEEK_OF_MONTH);

      mDecade.addAssignment(year, month, week, value);
    }
  }

  public List<Assignment> gHistory(Persons inPersons, Long inId)
  {
    return mAssignments.gHistory(inPersons, inId, 10);
  }

  public Year getYear(int inYear)
  {
    return mDecade.g(inYear);
  }

  /*
   * inMonth = 0 - 11 (i.e. January is 0)
   */
  public Month getMonth(int inYear, int inMonth)
  {
    return mDecade.g(inYear).g(inMonth);
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

    public void addAssignment(int inYear, int inMonth, int inWeek, Assignment inValue)
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
    private List<Assignment> mAssignment = new ArrayList<>();

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
      return mAssignment.size();
    }

    public int gAssigned()
    {
      int ret = 0;
      for (Assignment value : mAssignment)
      {
        if (value.isAssigned())
        {
          ret++;
        }
      }
      return ret;
    }

    public void add(Assignment inValue)
    {
      mAssignment.add(inValue);
    }

    public List<Assignment> getAssignment()
    {
      return mAssignment;
    }

    public Assignment get(Part inPpart, Hall inHall)
    {
      Assignment ret = null;
      for (Assignment value : mAssignment)
      {
        if (inPpart.equals(value.getCurriculum().getPpart()) && inHall.equals(value.getHall()))
        {
          ret = value;
          break;
        }
      }
      return ret;
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