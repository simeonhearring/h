package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h.model.shared.Person.Gender;
import h.model.shared.util.RandomUtil;
import h.model.shared.util.TimeUtil;

@SuppressWarnings("serial")
public class PartInfo implements Serializable, Comparator<PartInfo.Info>
{
  private Part mPart;
  private Long mParticipantId;

  private List<Info> mInfo = new ArrayList<>();

  PartInfo()
  {
  }

  public PartInfo(Part inPart, Long inParticipantId)
  {
    mPart = inPart;
    mParticipantId = inParticipantId;
  }

  public Part getPart()
  {
    return mPart;
  }

  public List<Info> getInfo()
  {
    return mInfo;
  }

  public Person gRecomendation()
  {
    Person ret = null;

    int top = 0;
    Map<Integer, List<Person>> data = new HashMap<>();
    for (Info value : mInfo)
    {
      value.score(mPart, mParticipantId);
      int score = value.getScore();
      if (!data.containsKey(score))
      {
        top = top > score ? top : score;
        data.put(score, new ArrayList<Person>());
      }
      data.get(score).add(value.getPerson());
    }

    ret = RandomUtil.random(data.get(top));
    return ret;
  }

  public void add(Person inPerson, List<Assignment> inArchive)
  {
    mInfo.add(new Info(inPerson, inArchive));
  }

  public void sort()
  {
    Collections.sort(mInfo, this);
  }

  public static class Info implements Serializable
  {
    private Person mPerson;
    private List<Assignment> mArchive;

    private int mScore;

    Info()
    {
    }

    public Info(Person inPerson, List<Assignment> inArchive)
    {
      mPerson = inPerson;
      mArchive = inArchive;
    }

    public int getScore()
    {
      return mScore;
    }

    public int[] score(Part inPart, Long inParticipantId)
    {
      int ret[] = new int[4];
      ret[0] = scoreLast();
      ret[1] = scoreLast(inPart);
      ret[2] = scoreTimesGiven(inPart);
      ret[3] = scoreAssistant(inParticipantId);
      for (int i : ret)
      {
        mScore += i;
      }
      return ret;
    }

    public Person getPerson()
    {
      return mPerson;
    }

    public List<String> getArchive(Persons inPersons)
    {
      return Assignment.getArchive(inPersons, mArchive, mPerson.getIdLong());
    }

    public Date getLast()
    {
      return getLast((Date) null);
    }

    public Date getLast(Date inDefault)
    {
      return mArchive.size() > 0 ? mArchive.get(0).getDate() : inDefault;
    }

    public Date getLast(Part inPart, Date inDefault)
    {
      Date ret = null;
      for (Assignment value : mArchive)
      {
        if (inPart.equals(value.getPart()))
        {
          ret = value.getDate();
          break;
        }
      }
      return ret == null ? inDefault : ret;
    }

    public int scoreAssistant(Long inParticipantId)
    {
      int ret = 0;

      if (inParticipantId != null)
      {
        int count = 0;
        for (Assignment value : mArchive)
        {
          if (inParticipantId.equals(value.getAssistantId()))
          {
            count++;
          }
        }
        if (count <= 1)
        {
          ret = 5;
        }
        else if (count <= 2)
        {
          ret = 4;
        }
        else if (count <= 3)
        {
          ret = 3;
        }
        else if (count <= 4)
        {
          ret = 2;
        }
        else if (count <= 5)
        {
          ret = 1;
        }
      }

      return ret;
    }

    public int scoreTimesGiven(Part inPart)
    {
      int ret = 0;

      Map<Part, Integer> freq = new HashMap<>();
      for (Part value : Part.student())
      {
        freq.put(value, 0);
      }

      if (freq.containsKey(inPart))
      {
        for (Assignment value : mArchive)
        {
          if (freq.containsKey(value.getPart()))
          {
            int count = freq.get(value.getPart()).intValue();
            freq.put(value.getPart(), ++count);
          }
        }
        int count = freq.get(inPart);
        if (count <= 1)
        {
          ret = 5;
        }
        else if (count <= 2)
        {
          ret = 4;
        }
        else if (count <= 3)
        {
          ret = 3;
        }
        else if (count <= 4)
        {
          ret = 2;
        }
        else if (count <= 5)
        {
          ret = 1;
        }
      }
      return ret;
    }

    public int scoreLast(Part inPart)
    {
      int ret = 0;
      int days = gDaysSince(getLast(inPart, new Date(1L)));
      if (days > 365) // 12 mo
      {
        ret = 12;
      }
      else if (days > 182) // 6 mo
      {
        ret = 6;
      }
      else if (days > 91) // 3 mo
      {
        ret = 3;
      }
      return ret;
    }

    public int scoreLast()
    {
      int ret = 0;
      int days = gDaysSince(getLast(new Date(1L)));
      if (days > 365) // 12 mo
      {
        ret = 12;
      }
      else if (days > 182) // 6 mo
      {
        ret = 6;
      }
      else if (days > 91) // 3 mo
      {
        ret = 3;
      }
      return ret;
    }

    private int gDaysSince(Date inValue)
    {
      return TimeUtil.toDays(gToday().getTime() - inValue.getTime());
    }

    protected Date gToday()
    {
      return new Date();
    }
  }

  @Override
  public int compare(PartInfo.Info inO1, PartInfo.Info inO2)
  {
    Date d1 = inO1.getLast(new Date(1L));
    Date d2 = inO2.getLast(new Date(1L));

    int ret = d1.compareTo(d2);

    if (ret == 0)
    {
      Gender g1 = inO1.getPerson().getGender();
      Gender g2 = inO2.getPerson().getGender();
      ret = g1.compareTo(g2);
    }

    if (ret == 0)
    {
      String n1 = inO1.getPerson().getName();
      String n2 = inO2.getPerson().getName();
      ret = n1.compareTo(n2);
    }

    return ret;
  }
}