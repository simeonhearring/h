package h.model.shared.khall;

import static h.model.shared.util.ListUtil.subReverse;
import static h.model.shared.util.StringUtil.ensure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Assignments implements Serializable
{
  public enum Count
  {
    ALL,
    STUDENT,
    NON_STUDENT;
  }

  private List<Assignment> mAssignments = new ArrayList<>();

  Assignments()
  {
  }

  public double gCount(Count inCount)
  {
    double ret = 0;
    switch (inCount)
    {
      case ALL:
        ret = gCount();
        break;
      case STUDENT:
        ret = gStudentCount();
        break;
      case NON_STUDENT:
        ret = gNStudentCount();
        break;
      default:
        break;
    }
    return ret;
  }

  public int gAssigned(Count inCount)
  {
    int ret = 0;
    switch (inCount)
    {
      case ALL:
        ret = gAssigned();
        break;
      case STUDENT:
        ret = gStudentAssigned();
        break;
      case NON_STUDENT:
        ret = gNStudentAssigned();
        break;
      default:
        break;
    }
    return ret;
  }

  public double gCount()
  {
    return mAssignments.size();
  }

  public List<Assignment> gAssignments()
  {
    return mAssignments;
  }

  public List<Assignment> gHistory(long inPersonId, int inMax)
  {
    List<Assignment> ret = new ArrayList<>();
    for (Assignment value : subReverse(gAssignedTo(inPersonId), inMax))
    {
      ret.add(value);
    }
    return ret;
  }

  public List<Assignment> gAssignedTo(long inPersonId)
  {
    List<Assignment> ret = new ArrayList<>();
    for (Assignment value : mAssignments)
    {
      if (value.isAssignedTo(inPersonId))
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public double gStudentCount()
  {
    int ret = 0;
    for (Assignment value : mAssignments)
    {
      if (value.isStudent())
      {
        ret++;
      }
    }
    return ret;
  }

  public double gNStudentCount()
  {
    int ret = 0;
    for (Assignment value : mAssignments)
    {
      if (!value.isStudent())
      {
        ret++;
      }
    }
    return ret;
  }

  public int gAssigned()
  {
    int ret = 0;
    for (Assignment value : mAssignments)
    {
      if (value.isAssigned())
      {
        ret++;
      }
    }
    return ret;
  }

  public int gStudentAssigned()
  {
    int ret = 0;
    for (Assignment value : mAssignments)
    {
      if (value.isStudent() && value.isAssigned())
      {
        ret++;
      }
    }
    return ret;
  }

  public int gNStudentAssigned()
  {
    int ret = 0;
    for (Assignment value : mAssignments)
    {
      if (!value.isStudent() && value.isAssigned())
      {
        ret++;
      }
    }
    return ret;
  }

  public void add(Assignment inValue)
  {
    mAssignments.add(inValue);
  }

  public void add(List<Assignment> inValues)
  {
    mAssignments.addAll(inValues);
  }

  public Assignment gAssignment(Part inPpart, Hall inHall)
  {
    Assignment ret = null;
    for (Assignment value : mAssignments)
    {
      if (inPpart.equals(value.getCurriculum().getPpart()) && inHall.equals(value.getHall()))
      {
        ret = value;
        break;
      }
    }
    return ret;
  }

  public static class Report implements Serializable
  {
    public enum Meet
    {
      INTRO,
      TREASURES,
      APPLY,
      LIVING;
    }

    private String mWeek;
    private String mMeeting;

    private String mTime;
    private String mTheme;

    private String mParticipantsA;
    private String mParticipantsB;

    private String mPart;

    public String getWeek()
    {
      return mWeek;
    }

    public String getMeeting()
    {
      return mMeeting;
    }

    public void setMeeting(String inMeeting)
    {
      mMeeting = inMeeting;
    }

    public String getTime()
    {
      return mTime;
    }

    public String getTheme()
    {
      return mTheme;
    }

    public String getParticipantsA()
    {
      return mParticipantsA;
    }

    public String getParticipantsB()
    {
      return mParticipantsB;
    }

    public String getChairman()
    {
      return mParticipantsA;
    }

    public void setWeek(String inWeek, String inReading)
    {
      mWeek = inWeek + ensure(inReading, " | ");
    }

    public void setSongPrayer(String inSong)
    {
      setTheme(ensure(inSong, "Song ", " and prayer"), null);
    }

    public void setSong(String inSong)
    {
      setTheme(ensure(inSong, "Song "), null);
    }

    public void setTime(String inTime)
    {
      mTime = ensure(inTime);
    }

    public void setTheme(Part inPart, String inTheme, String inSource, String inTiming)
    {
      if (inPart.isSong())
      {
        if (inPart.isPrayer())
        {
          setSongPrayer(inSource);
        }
        else
        {
          setSong(inSource);
        }
      }
      else if (inPart.isSelfThemed())
      {
        setTheme(inPart.getLabel(false), inTiming);
      }
      else
      {
        setTheme(inTheme, inTiming);
      }
    }

    public void setTheme(String inTheme, String inTiming)
    {
      mTheme = ensure(inTheme) + ensure(inTiming, "<style size='6'> (", ")</style>");
    }

    public void setChairman(String inChairman)
    {
      mParticipantsA = ensure(mParticipantsA, "<sup>Chairman: </sup>");
    }

    public void setParticipantsA(String inParticipant, String inAssistant, String inStudyPoint)
    {
      mParticipantsA = ensure(inParticipant, ensure(inStudyPoint, "<sup>", "</sup> "))
          + ensure(inAssistant, "<br/>");
    }

    public void setParticipantsB(String inParticipant, String inAssistant, String inStudyPoint)
    {
      mParticipantsA = ensure(inParticipant, ensure(inStudyPoint, "<sup>", "</sup> "))
          + ensure(inAssistant, "<br/>");
    }

    public String getPart()
    {
      return mPart;
    }

    public void setPart(String inPart)
    {
      mPart = inPart;
    }
  }
}