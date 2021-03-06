package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import h.model.shared.util.NumberUtil;

@SuppressWarnings("serial")
public class Congregation implements Serializable
{
  private Integer mId;
  private String mNumber;
  private String mName;
  private Hall[] mHalls;
  private Map<Integer, FieldServiceGroup> mFsgs;
  private Map<Date, Event> mEvents;

  public Integer getId()
  {
    return mId;
  }

  public void setId(Integer inId)
  {
    mId = inId;
  }

  public Date gMidweekOn()
  {
    Date ret = null;
    for (Entry<Date, Event> value : mEvents.entrySet())
    {
      if (Event.Type.WEEKMID.equals(value.getValue().getType()))
      {
        ret = value.getKey();
        break;
      }
    }
    return ret;
  }

  public String getNumber()
  {
    return mNumber;
  }

  public void setNumber(String inNumber)
  {
    mNumber = inNumber;
  }

  public String getName()
  {
    return mName;
  }

  public void setName(String inName)
  {
    mName = inName;
  }

  public Hall[] getHalls()
  {
    return mHalls;
  }

  public void setHalls(Hall... inHalls)
  {
    mHalls = inHalls;
  }

  public Map<Date, Event> getEvents()
  {
    return mEvents;
  }

  public void setEvents(Map<Date, Event> inEvents)
  {
    mEvents = inEvents;
  }

  public Event gEvent(Date inOf)
  {
    Event ret = null;

    if (mEvents.containsKey(inOf))
    {
      ret = mEvents.get(inOf);
    }
    else
    {
      long s = inOf.getTime();
      long e = s + 518400000L; // 6 days

      for (Date value : mEvents.keySet())
      {
        if (NumberUtil.isBetweenEqual(value.getTime(), s, e))
        {
          ret = mEvents.get(value);
          break;
        }
      }
    }

    return ret;
  }

  public List<Event> gEvents(Date inOf)
  {
    List<Event> ret = new ArrayList<>();
    Event e = gEvent(inOf);
    if (e != null)
    {
      ret.add(e);
    }
    return ret;
  }

  public Map<Integer, FieldServiceGroup> getFsgs()
  {
    return mFsgs;
  }

  public void setFsgs(Map<Integer, FieldServiceGroup> inFsgs)
  {
    mFsgs = inFsgs;
  }

  public String gFsgTitle(Integer inFsgId)
  {
    String ret = "-unassigned-";
    if (mFsgs.containsKey(inFsgId))
    {
      ret = mFsgs.get(inFsgId).getTitle();
    }
    return ret;
  }
}