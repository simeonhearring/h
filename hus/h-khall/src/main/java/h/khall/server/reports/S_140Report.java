package h.khall.server.reports;

import static h.model.shared.util.StringUtil.ensure;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h.khall.server.dao.Dao;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Assignments.Count;
import h.model.shared.khall.Assignments.Report;
import h.model.shared.khall.Curriculum;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Meeting.Month;
import h.model.shared.khall.Meeting.Week;
import h.model.shared.khall.Part;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.model.shared.khall.StudyPoint;

public class S_140Report extends AbstractReportDefault<Report>
{
  private String mCongId = "pCongId";
  private String mYear = "pYear";
  private String mMonth = "pMonth";
  private String mEncrypt = "pEncrypt";
  private String mCongNme = "pCongNme";

  @Override
  public Map<String, Object> getParameters(Map<String, String> inParameters)
  {
    Map<String, Object> ret = new HashMap<>();

    ret.put(mCongId, Integer.valueOf(inParameters.get(mCongId)));
    ret.put(mYear, Integer.valueOf(inParameters.get(mYear)));
    ret.put(mMonth, Integer.valueOf(inParameters.get(mMonth)));
    ret.put(mEncrypt, inParameters.get(mEncrypt));
    ret.put(mCongNme, inParameters.get(mCongNme));

    return ret;
  }

  @Override
  public Collection<Report> getCollection(Map<String, Object> inParameters)
  {
    Dao dao = getDao();
    Profile profile = profile(inParameters);

    Meeting meeting = dao.selectMeeting(profile);
    Persons persons = dao.selectPersons(profile);

    int yr = (int) inParameters.get(mYear);
    int mo = (int) inParameters.get(mMonth);

    Month month = meeting.gMonth(yr, mo);

    List<Report> ret = report(persons, month);
    return ret;
  }

  private Profile profile(Map<String, Object> inParameters)
  {
    Profile ret = new Profile();
    ret.setCongId((Integer) inParameters.get(mCongId));
    ret.setEncrypt((String) inParameters.get(mEncrypt));
    ret.setYear((Integer) inParameters.get(mYear));
    ret.setCount(Count.ALL);
    return ret;
  }

  protected List<Report> report(Persons inPersons, Month inMonth)
  {
    List<Report> ret = new ArrayList<>();
    Date midweek = new Date(1451955600000L); // 1/4/2016 7 pm
    TimeKeeper keeper = new TimeKeeper(midweek);

    for (int i = 0; i < inMonth.size(); i++)
    {
      Week week = inMonth.g(i);

      String reading = week.gAssignmentE(Part.CHAIRMAN, Hall.MAIN).getSource();

      for (Part value : Part.schedule(false))
      {
        Assignment a = week.gAssignmentE(value, Hall.MAIN);
        String time = format("h:mm", keeper.gElapsed(a.getWeekOf(), a.getCurriculum()));
        ret.add(newReport(inPersons, reading, value, a, time));
      }
    }

    return ret;
  }

  private Report newReport(Persons inPersons, String inReading, Part inPpart, Assignment inA1, String inTime)
  {
    Report ret = new Report();

    ret.setPart(inPpart.name());

    ret.setWeek(dateRange(inA1.getDate()), inReading);
    ret.setMeeting(Part.meeting(inPpart));

    ret.setTime(inTime);
    ret.setTheme(inA1.getPart(), inA1.getTheme(), inA1.getSource(), inA1.gTiming());

    String p = ensure(inPersons.gName(inA1.getParticipantId()));
    String a = ensure(inPersons.gName(inA1.getAssistantId()));
    String s = StudyPoint.display(inA1.getStudyPoint());
    ret.setParticipantsA(p, a, s);

    return ret;
  }

  @SuppressWarnings("deprecation")
  public static String dateRange(Date inValue)
  {
    Date end = new Date(inValue.getTime() + 518400000); // 6 days
    String pattern = inValue.getMonth() == end.getMonth() ? "dd" : "MMMM dd";
    return (format("MMMM dd-", inValue) + format(pattern, end)).toUpperCase();
  }

  private static String format(String inPattern, Date inValue)
  {
    if (inValue == null)
    {
      return "";
    }
    return new SimpleDateFormat(inPattern).format(inValue);
  }

  private static class TimeKeeper
  {
    private Date mWeekOf;
    private Date mStart, mTime;

    public TimeKeeper(Date inTime)
    {
      mStart = inTime;
      mTime = inTime;
    }

    public Date gElapsed(Curriculum inCurriculum)
    {
      Date ret = mTime;

      mTime = new Date(mTime.getTime() + inCurriculum.gElapse());

      return ret;
    }

    public boolean isWeekOf(Date inWeekOf)
    {
      boolean ret = !inWeekOf.equals(mWeekOf);
      if (ret)
      {
        mWeekOf = inWeekOf;
        mTime = mStart;
      }
      return ret;
    }

    public Date gElapsed(Date inWeekOf, Curriculum inCurriculum)
    {
      isWeekOf(inWeekOf);
      return gElapsed(inCurriculum);
    }
  }
}