package h.khall.server.reports;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import h.khall.server.dao.bean.RandomPersons;
import h.khall.shared.model.MeetingTest;
import h.model.shared.khall.Assignments.Report;
import h.model.shared.khall.Meeting;

public class S_140ReportTest
{
  @Test
  public void test()
  {
    S_140Report s140 = new S_140Report();

    Meeting meeting = MeetingTest.meeting(true);

    List<Report> reports = s140.report(RandomPersons.persons(), meeting.gMonth(2017, 0));

    Report report = reports.get(0);
    Assert.assertEquals("SONG_1", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("INTRO", report.getMeeting());
    Assert.assertEquals("7:00", report.getTime());
    Assert.assertEquals("Song 77 and prayer", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(1);
    Assert.assertEquals("OPEN", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("INTRO", report.getMeeting());
    Assert.assertEquals("7:05", report.getTime());
    Assert.assertEquals("Opening Comments<style size='6'> (3 min. or less)</style>", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(2);
    Assert.assertEquals("TREASURES", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("TREASURES", report.getMeeting());
    Assert.assertEquals("7:08", report.getTime());
    Assert.assertEquals("“Jehovah’s Word Comes True in Every Detail”<style size='6'> (10 min.)</style>", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(3);
    Assert.assertEquals("DIGGING", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("TREASURES", report.getMeeting());
    Assert.assertEquals("7:18", report.getTime());
    Assert.assertEquals("Digging for Spiritual Gems<style size='6'> (8 min.)</style>", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(4);
    Assert.assertEquals("BIBLE_READING", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("TREASURES", report.getMeeting());
    Assert.assertEquals("7:26", report.getTime());
    Assert.assertEquals("Bible Reading", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(5);
    Assert.assertEquals("APPLY1", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("7:26", report.getTime());
    Assert.assertEquals("Initial Call", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(6);
    Assert.assertEquals("APPLY2", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("7:26", report.getTime());
    Assert.assertEquals("Third Return Visit", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(7);
    Assert.assertEquals("APPLY3", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("APPLY", report.getMeeting());
    Assert.assertEquals("7:26", report.getTime());
    Assert.assertEquals("Bible Study", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(8);
    Assert.assertEquals("SONG_2", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("LIVING", report.getMeeting());
    Assert.assertEquals("7:26", report.getTime());
    Assert.assertEquals("Song 77", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(9);
    Assert.assertEquals("LIVING_1", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("LIVING", report.getMeeting());
    Assert.assertEquals("7:31", report.getTime());
    Assert.assertEquals("“How Strong Is Your Faith in Jehovah’s Promises?”", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(10);
    Assert.assertEquals("LIVING_2", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("LIVING", report.getMeeting());
    Assert.assertEquals("7:31", report.getTime());
    Assert.assertEquals("Local Needs", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(11);
    Assert.assertEquals("C_BIBLE_STUDY", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("LIVING", report.getMeeting());
    Assert.assertEquals("7:31", report.getTime());
    Assert.assertEquals("Congregation Bible Study<style size='6'> (30 min.)</style>", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(12);
    Assert.assertEquals("REVIEW", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("LIVING", report.getMeeting());
    Assert.assertEquals("8:01", report.getTime());
    Assert.assertEquals("Review/Preview/Announcements<style size='6'> (3 min. or less)</style>", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(13);
    Assert.assertEquals("SONG_3", report.getPart());
    Assert.assertEquals("JANUARY 09-15 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("LIVING", report.getMeeting());
    Assert.assertEquals("8:04", report.getTime());
    Assert.assertEquals("Song 77 and prayer", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    report = reports.get(14);
    Assert.assertEquals("SONG_1", report.getPart());
    Assert.assertEquals("JANUARY 16-22 | JEREMIAH 51-52", report.getWeek());
    Assert.assertEquals("INTRO", report.getMeeting());
    Assert.assertEquals("7:00", report.getTime());
    Assert.assertEquals("Song 77 and prayer", report.getTheme());
    Assert.assertNotEquals("", report.getParticipantsA());
    Assert.assertNull(report.getParticipantsB());

    Assert.assertEquals(56, reports.size());
  }
}