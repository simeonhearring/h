package h.khall.server.reports;

import static h.model.shared.util.StringUtil.ensure;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h.khall.server.dao.Dao;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Assignments.Count;
import h.model.shared.khall.Assignments.Report;
import h.model.shared.khall.Congregation;
import h.model.shared.khall.Curriculum;
import h.model.shared.khall.Event;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Meeting.Month;
import h.model.shared.khall.Meeting.Week;
import h.model.shared.khall.Part;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.model.shared.khall.StudyPoint;
import h.model.shared.util.StringUtil;
import h.util.TimeUtil;

public class OclmReport extends AbstractReportDefault<Report>
{
  private static String sCongId = "pCongId";
  private static String sYear = "pYear";
  private static String sMonth = "pMonth";
  private static String sEncrypt = "pEncrypt";
  private static String sCongNme = "pCongNme";

  @Override
  public Map<String, Object> getParameters(Map<String, String> inParameters)
  {
    Map<String, Object> ret = new HashMap<>();

    ret.put(sCongId, Integer.valueOf(inParameters.get(sCongId)));
    ret.put(sYear, Integer.valueOf(inParameters.get(sYear)));
    ret.put(sMonth, Integer.valueOf(inParameters.get(sMonth)));
    ret.put(sEncrypt, inParameters.get(sEncrypt));

    return ret;
  }

  @Override
  public Collection<Report> getCollection(Map<String, Object> inParameters)
  {
    Dao dao = getDao();
    Profile profile = profile(inParameters);

    Meeting meeting = dao.selectMeeting(profile);
    Persons persons = dao.selectPersons(profile);
    Congregation cong = dao.selectCong(profile);

    int yr = (int) inParameters.get(sYear);
    int mo = (int) inParameters.get(sMonth);

    inParameters.put(sCongNme, cong.getName());

    List<Report> ret = report(persons, meeting.gMonth(yr, mo), cong);

    sort(ret);

    return ret;
  }

  protected void sort(List<Report> inRet)
  {
  }

  private static Profile profile(Map<String, Object> inParameters)
  {
    Profile ret = new Profile();
    ret.setCongId((Integer) inParameters.get(sCongId));
    ret.setEncrypt((String) inParameters.get(sEncrypt));
    ret.setYear((Integer) inParameters.get(sYear));
    ret.setCount(Count.ALL);
    return ret;
  }

  protected List<Report> report(Persons inPersons, Month inMonth, Congregation inCong)
  {
    List<Report> ret = new ArrayList<>();

    for (int i = 0; i < inMonth.size(); i++)
    {
      Week week = inMonth.g(i);

      TimeKeeper keeper = new TimeKeeper(meetingDate(inCong.gMidweekOn(), week.getOf()));

      String[] chairman =
          chairman(inPersons, week.gAssignments(Part.CHAIRMAN, Hall.MAIN).get(Hall.MAIN));

      boolean isLiving2 = week.gAssignment(Part.LIVING_2, Hall.MAIN) != null;

      Event event = inCong.gEvent(week.getOf());
      boolean coVisit = event != null && event.isCoVisit();

      for (Part value : Part.schedule(coVisit, isStudent(), isLiving2))
      {
        boolean skip = value.isApply() && week.gAssignment(value, Hall.MAIN) == null;

        if (!skip)
        {
          Map<Hall, Assignment> assignment = week.gAssignments(value, inCong.getHalls());
          eventAssignment(value, assignment, event);
          Report r = newReport(keeper, chairman[0], chairman[1], assignment.get(Hall.MAIN), week.getOf(), event);
          addReports(ret, inPersons, inMonth, assignment, r.copy(), chairman);
        }
      }
    }

    return ret;
  }

  private void eventAssignment(Part inPart, Map<Hall, Assignment> inAssignment, Event inEvent)
  {
    if (Part.CO_TALK.equals(inPart))
    {
      for (Assignment value : inAssignment.values())
      {
        value.getCurriculum()
            .setTheme(inPart.getLabel(true) + StringUtil.ensure(inEvent.getTheme(), ": "));
      }
    }
  }

  private String[] chairman(Persons inPersons, Assignment inChairman)
  {
    String csource = null;
    String cname = null;

    if (inChairman != null)
    {
      csource = inChairman.getSource();
      cname = ensure(inPersons.gName(inChairman.getParticipantId()));
    }
    return new String[]
    {
        csource, cname
    };
  }

  protected boolean isStudent()
  {
    return false;
  }

  protected boolean isWorksheet()
  {
    return false;
  }

  protected void addReports(List<Report> inList, Persons inPersons, Month inMonth,
      Map<Hall, Assignment> inAssignments, Report inReport, String[] inChairman)
  {
    inReport.setHall(Hall.MAIN.name());

    String[] main = assign(inPersons, inAssignments.get(Hall.MAIN), inChairman);
    inReport.setParticipantsA(main[0], main[1], main[2]);

    String[] aux = assign(inPersons, inAssignments.get(Hall.SECOND), inChairman);
    inReport.setParticipantsB(aux[0], aux[1], aux[2]);

    inList.add(inReport);
  }

  protected final String[] assign(Persons inPersons, Assignment inAssignment, String[] inChairman)
  {
    String participant = null, assistant = null, studypoint = null;
    if (inAssignment != null)
    {
      participant = ensure(inPersons.gName(inAssignment.getParticipantId()));
      assistant = ensure(inPersons.gName(inAssignment.getAssistantId()));
      studypoint = StudyPoint.display(inAssignment.gStudyPoint());

      if (inAssignment.getPart().isVideo())
      {
        participant = inChairman[1];
      }

      if (inAssignment.getPart().isCoTalk())
      {
        participant = "Circuit Overseer";
      }
    }
    return new String[]
    {
        participant,
        assistant,
        studypoint
    };
  }

  private Report newReport(TimeKeeper inT, String inReading, String inChairman, Assignment inA,
      Date inWeekOf, Event inEvent)
  {
    Report ret = new Report();

    Date date = inT.gElapsed(inA.getCurriculum());

    ret.setTime(format("h:mm", date));

    ret.setWeek(date(date, inWeekOf), inReading, inChairman);

    ret.setEvent(event(inEvent));

    ret.setMeeting(Part.meeting(inA.getPart()));
    ret.setPart(inA.getPart().name());

    ret.setTheme(inA.getPart(), inA.getTheme(), inA.getSource(), inA.gTiming());

    return ret;
  }

  private String event(Event inEvent)
  {
    String ret = "";
    if (inEvent != null)
    {
      ret = inEvent.gTypeLabel();
    }
    return ret;
  }

  private String date(Date inDate, Date inWeekOf)
  {
    String ret = null;
    if (isStudent())
    {
      ret = isWorksheet() ? "Week of: " + format("MM/dd/yy", inWeekOf) : format("EEE MM/dd/yy", inDate);
    }
    else
    {
      Date end = new Date(inWeekOf.getTime() + 518400000); // +6 days
      String pattern = TimeUtil.isSameMonth(inWeekOf, end) ? "dd" : "MMMM dd";
      ret = (format("MMMM dd-", inWeekOf) + format(pattern, end)).toUpperCase();
    }
    return ret;
  }

  public static Date meetingDate(Date inMeetingDay, Date inValue)
  {
    Calendar meetingDay = Calendar.getInstance();
    meetingDay.setTime(inMeetingDay);

    Calendar weekOf = Calendar.getInstance();
    weekOf.setTime(inValue);

    weekOf.set(Calendar.DAY_OF_WEEK, meetingDay.get(Calendar.DAY_OF_WEEK));
    weekOf.set(Calendar.HOUR_OF_DAY, meetingDay.get(Calendar.HOUR_OF_DAY));
    weekOf.set(Calendar.MINUTE, meetingDay.get(Calendar.MINUTE));

    return weekOf.getTime();
  }

  public static String meetingDate(String inPattern, Date inMeetingDay, Date inValue)
  {
    return format(inPattern, meetingDate(inMeetingDay, inValue));
  }

  public static String format(String inPattern, Date inValue)
  {
    return TimeUtil.format(inPattern, inValue);
  }

  private static class TimeKeeper
  {
    private Date mTime;

    public TimeKeeper(Date inTime)
    {
      mTime = inTime;
    }

    public Date gElapsed(Curriculum inCurriculum)
    {
      Date ret = mTime;

      mTime = new Date(mTime.getTime() + inCurriculum.gElapse());

      return ret;
    }
  }
}