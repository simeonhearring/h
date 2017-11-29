package h.khall.server.reports;

import static h.model.shared.util.StringUtil.ensure;

import java.text.SimpleDateFormat;
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
import h.model.shared.khall.Hall;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Meeting.Month;
import h.model.shared.khall.Meeting.Week;
import h.model.shared.khall.Part;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.model.shared.khall.StudyPoint;
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
    return ret;
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

      TimeKeeper keeper = new TimeKeeper(meetingDate(inCong.getMidweekOn(), week.getOf()));

      Assignment chairman = week.gAssignmentE(Part.CHAIRMAN, Hall.MAIN).get(Hall.MAIN);
      String csource = chairman.getSource();
      String cname = ensure(inPersons.gName(chairman.getParticipantId()));

      for (Part value : Part.schedule(week.isCoWeek(), isStudent()))
      {
        Map<Hall, Assignment> assignment = week.gAssignmentE(value, inCong.getHalls());
        Report r = newReport(keeper, csource, cname, assignment.get(Hall.MAIN));
        addReport(ret, inPersons, assignment, r.copy());
      }
    }

    return ret;
  }

  protected boolean isStudent()
  {
    return false;
  }

  protected boolean isWorksheet()
  {
    return false;
  }

  protected void addReport(List<Report> inList, Persons inPersons, Map<Hall, Assignment> inA, Report inR)
  {
    inR.setHall(Hall.MAIN.name());

    String[] main = assign(inPersons, inA.get(Hall.MAIN));
    inR.setParticipantsA(main[0], main[1], main[2]);

    String[] aux = assign(inPersons, inA.get(Hall.SECOND));
    inR.setParticipantsB(aux[0], aux[1], aux[2]);

    inList.add(inR);
  }

  private String[] assign(Persons inPersons, Assignment inAssignment)
  {
    String participant = null, assistant = null, studypoint = null;
    if (inAssignment != null)
    {
      participant = ensure(inPersons.gName(inAssignment.getParticipantId()));
      assistant = ensure(inPersons.gName(inAssignment.getAssistantId()));
      studypoint = StudyPoint.display(inAssignment.getStudyPoint());
    }
    return new String[]
    {
        participant,
        assistant,
        studypoint
    };
  }

  private Report newReport(TimeKeeper inT, String inReading, String inChairman, Assignment inA)
  {
    Report ret = new Report();

    Date date = inT.gElapsed(inA.getCurriculum());

    ret.setTime(format("h:mm", date));

    ret.setWeek(date(date), inReading, inChairman);

    ret.setMeeting(Part.meeting(inA.getPart()));
    ret.setPart(inA.getPart().name());

    ret.setTheme(inA.getPart(), inA.getTheme(), inA.getSource(), inA.gTiming());

    return ret;
  }

  private String date(Date inValue)
  {
    String ret = null;
    if (isStudent())
    {
      ret = isWorksheet() ? "Week of: " + format("MM/dd/yy", inValue) : format("EEE MM/dd/yy", inValue);
    }
    else
    {
      Date end = new Date(inValue.getTime() + 518400000); // +6 days
      String pattern = TimeUtil.isSameMonth(inValue, end) ? "dd" : "MMMM dd";
      ret = (format("MMMM dd-", inValue) + format(pattern, end)).toUpperCase();
    }
    return ret;
  }

  protected static Date meetingDate(Date inMeetingDay, Date inValue)
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