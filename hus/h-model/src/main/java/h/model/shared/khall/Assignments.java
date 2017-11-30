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
    private String mWeek;
    private String mMeeting;
    private String mChairman;

    private String mTime;
    private String mTheme;

    private String mDate;
    private String mPart;
    private String mHall;

    private String mParticipantsA;
    private String mParticipantsB; // for Aux. Class.

    private String mParticipant;
    private String mAssistant;
    private String mStudyPoint;

    private String mHistory;

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
      return mChairman;
    }

    public String getParticipant()
    {
      return mParticipant;
    }

    public String getAssistant()
    {
      return mAssistant;
    }

    public String getStudyPoint()
    {
      return mStudyPoint;
    }

    public String getDate()
    {
      return mDate;
    }

    public void setWeek(String inDate, String inReading, String inChairman)
    {
      mDate = inDate;
      mWeek = mDate + ensure(inReading, " | ");
      mChairman = ensure(inChairman, "<sup>Chairman: </sup>");
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

    public void setParticipantsA(String inParticipant, String inAssistant, String inStudyPoint)
    {
      mParticipantsA = ensure(inParticipant, ensure(inStudyPoint, "<sup>", "</sup> "))
          + ensure(inAssistant, "<br/>");
      mParticipant = ensure(inParticipant);
      mAssistant = ensure(inAssistant);
      mStudyPoint = ensure(inStudyPoint);
    }

    public void setParticipantsB(String inParticipant, String inAssistant, String inStudyPoint)
    {
      mParticipantsB = ensure(inParticipant, ensure(inStudyPoint, "<sup>", "</sup> "))
          + ensure(inAssistant, "<br/>");
    }

    public void setStudyPoint(String inStudyPoint)
    {
      mStudyPoint = inStudyPoint;
    }

    public String getPart()
    {
      return mPart;
    }

    public void setPart(String inPart)
    {
      mPart = inPart;
    }

    public String getHall()
    {
      return mHall;
    }

    public void setHall(String inHall)
    {
      mHall = inHall;
    }

    public Report copy()
    {
      Report ret = new Report();
      ret.mChairman = mChairman;
      ret.mWeek = mWeek;
      ret.mMeeting = mMeeting;
      ret.mChairman = mChairman;

      ret.mDate = mDate;
      ret.mPart = mPart;
      ret.mHall = mHall;

      ret.mTime = mTime;
      ret.mTheme = mTheme;

      // ret.mParticipantsA = mParticipantsA;
      // ret.mParticipantsB = mParticipantsB;
      //
      // ret.mParticipant = mParticipant;
      // ret.mAssistant = mAssistant;
      // ret.mStudyPoint = mStudyPoint;
      return ret;
    }

    public String getHistory()
    {
      return mHistory;
    }

    public void setHistory(String inHistory)
    {
      mHistory = inHistory;
    }
  }
}