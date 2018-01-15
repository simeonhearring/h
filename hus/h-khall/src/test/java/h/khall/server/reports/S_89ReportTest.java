package h.khall.server.reports;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import h.khall.server.dao.bean.RandomPersons;
import h.khall.shared.model.MeetingTest;
import h.model.shared.khall.Assignments.Report;
import h.model.shared.khall.Congregation;
import h.model.shared.khall.Event;
import h.model.shared.khall.Event.Type;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Meeting;

public class S_89ReportTest
{
  @Test
  public void test()
  {
    OclmReport oclm = new S_89Report();

    Meeting meeting = MeetingTest.meeting(true);

    Congregation meetOn = newCong(Hall.MAIN);

    List<Report> reports = oclm.report(RandomPersons.persons(), meeting.gMonth(2017, 0), meetOn);
    int pos = 0;

    Report report = reports.get(pos++);
    Assert.assertEquals("BIBLE_READING", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("TREASURES", report.getMeeting());
    Assert.assertEquals("Bible Reading", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertEquals("", report.getAssistant());
    Assert.assertEquals("MAIN", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("INITIAL_CALL", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("Initial Call", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertNotEquals("", report.getAssistant());
    Assert.assertEquals("MAIN", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("T_RETURN_VISIT", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("Third Return Visit", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertNotEquals("", report.getAssistant());
    Assert.assertEquals("MAIN", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("BIBLE_STUDY", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("Bible Study", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertNotEquals("", report.getAssistant());
    Assert.assertEquals("MAIN", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("BIBLE_READING", report.getPart());
    Assert.assertEquals("Mon 01/16/17", report.getDate());
    Assert.assertEquals("TREASURES", report.getMeeting());
    Assert.assertEquals("Bible Reading", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertEquals("", report.getAssistant());
    Assert.assertEquals("MAIN", report.getHall());

    Assert.assertEquals(16, reports.size());
  }

  @Test
  public void testWithAux()
  {
    OclmReport oclm = new S_89Report();

    Meeting meeting = MeetingTest.meeting(true);

    Congregation meetOn = newCong(Hall.MAIN, Hall.SECOND);

    List<Report> reports = oclm.report(RandomPersons.persons(), meeting.gMonth(2017, 0), meetOn);
    int pos = 0;

    Report report = reports.get(pos++);
    Assert.assertEquals("BIBLE_READING", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("TREASURES", report.getMeeting());
    Assert.assertEquals("Bible Reading", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertEquals("", report.getAssistant());
    Assert.assertEquals("MAIN", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("BIBLE_READING", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("TREASURES", report.getMeeting());
    Assert.assertEquals("Bible Reading", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertEquals("", report.getAssistant());
    Assert.assertEquals("SECOND", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("INITIAL_CALL", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("Initial Call", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertNotEquals("", report.getAssistant());
    Assert.assertEquals("MAIN", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("INITIAL_CALL", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("Initial Call", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertNotEquals("", report.getAssistant());
    Assert.assertEquals("SECOND", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("T_RETURN_VISIT", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("Third Return Visit", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertNotEquals("", report.getAssistant());
    Assert.assertEquals("MAIN", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("T_RETURN_VISIT", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("Third Return Visit", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertNotEquals("", report.getAssistant());
    Assert.assertEquals("SECOND", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("BIBLE_STUDY", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("Bible Study", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertNotEquals("", report.getAssistant());
    Assert.assertEquals("MAIN", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("BIBLE_STUDY", report.getPart());
    Assert.assertEquals("Mon 01/09/17", report.getDate());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("Bible Study", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertNotEquals("", report.getAssistant());
    Assert.assertEquals("SECOND", report.getHall());

    report = reports.get(pos++);
    Assert.assertEquals("BIBLE_READING", report.getPart());
    Assert.assertEquals("Mon 01/16/17", report.getDate());
    Assert.assertEquals("TREASURES", report.getMeeting());
    Assert.assertEquals("Bible Reading", report.getTheme());
    Assert.assertNotEquals("", report.getParticipant());
    Assert.assertEquals("", report.getAssistant());
    Assert.assertEquals("MAIN", report.getHall());

    Assert.assertEquals(32, reports.size());
  }

  private Congregation newCong(Hall... inHalls)
  {
    Congregation ret = new Congregation();
    Map<Date, Event> e = new HashMap<>();
    ret.setEvents(e);
    e.put(new Date(1451955600000L), new Event(Type.WEEKMID, null));
    // ret.setMidweekOn(new Date(1451955600000L)); // 1/4/2016 7 pm
    ret.setHalls(inHalls);
    return ret;
  }
}