package h.khall.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.annotations.GwtIncompatible;

import h.model.shared.Tag;

@SuppressWarnings("serial")
public class Meeting implements Serializable
{
  private Decade mDecade;

  @GwtIncompatible(value = "uses java.util.Calendar - server only!")
  public void setAssignments(List<Assignment> inAssignments)
  {
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

  /*
   * inMonth = 0 - 11 (i.e. January is 0)
   */
  public Month getMonth(int inYear, int inMonth)
  {
    return mDecade.g(inYear).g(inMonth);
  }

  /*
   * inWeek = 1,2,3,4,5 (i.e. First week is 1)
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
      setup(inYear, inValue);
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
      setup(inMonth, inValue);
      mMap.get(inMonth).add(inWeek, inValue);
    }
  }

  public static class Month extends Setup<Week> implements Serializable, ISetup
  {
    @Override
    public Week create(Date inWeekOf)
    {
      return new Week(inWeekOf);
    }

    public void add(int inWeek, Assignment inValue)
    {
      setup(inWeek, inValue);
      mMap.get(inWeek).add(inValue);
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

    public void add(Assignment inValue)
    {
      mAssignment.add(inValue);
    }

    public List<Assignment> getAssignment()
    {
      return mAssignment;
    }

    public List<Tag> getTags(Part inPart, Hall inHall)
    {
      List<Tag> ret = new ArrayList<>();
      Assignment a = get(inPart, inHall);
      if (a != null)
      {
        a.addTags(ret);
      }
      return ret;
    }

    public Assignment get(Part inPart, Hall inHall)
    {
      Assignment ret = null;
      for (Assignment value : mAssignment)
      {
        if (inPart.equals(value.getPart()) && inHall.equals(value.getHall()))
        {
          ret = value;
          break;
        }
      }
      return ret;
    }
  }

  public interface ISetup
  {
  }

  public static abstract class Setup<V extends ISetup> implements Serializable
  {
    protected Map<Integer, V> mMap = new HashMap<>();

    public abstract V create(Date inWeekOf);

    public void setup(int inKey, Assignment inValue)
    {
      if (!mMap.containsKey(inKey))
      {
        mMap.put(inKey, create(inValue.getWeekOf()));
      }
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
  }
}