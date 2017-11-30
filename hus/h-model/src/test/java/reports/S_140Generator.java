package reports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import h.model.shared.khall.Assignments.Report;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Part;

import net.sf.jasperreports.engine.JRDefaultScriptlet;

public class S_140Generator extends JRDefaultScriptlet
{
  public static Collection<Report> createBeanCollection()
  {
    return schedule();
  }

  public static List<Report> schedule()
  {
    List<Report> ret = new ArrayList<>();

    ret.add(week1("INTRO"));
    ret.add(theme(week1("INTRO"), "Song 98 and prayer", null));
    ret.add(theme(week1("INTRO"), "Opening Comments", "3 min. or less"));

    ret.add(theme(week1("TREASURES"), "“Jehovah’s Word Comes True in Every Detail”", "10 min."));
    ret.add(theme(week1("TREASURES"), "Digging for Spiritual Gemes", "8 min."));
    ret.add(theme(week1("TREASURES"), "Bible Reading", "4 min. or less"));

    ret.add(student(week1("APPLY")));
    ret.add(student(week1("APPLY")));
    ret.add(student(week1("APPLY")));

    ret.add(theme(week1("LIVING"), "Song 98 and prayer", null));
    ret.add(theme(week1("LIVING"), "“Jehovah’s Word Comes True in Every Detail”", "10 min."));
    ret.add(theme(week1("LIVING"), "“Jehovah’s Word Comes True in Every Detail”", "10 min."));
    ret.add(theme(week1("LIVING"), "“Jehovah’s Word Comes True in Every Detail”", "10 min."));
    ret.add(theme(week1("LIVING"), "Song 98 and prayer", null));

    return ret;
  }

  private static Report theme(Report inAssign, String inTheme, String inTiming)
  {
    inAssign.setTheme(inTheme, inTiming);
    inAssign.setParticipantsA("Hearring, Jathen", null, null);
    return inAssign;
  }

  private static Report student(Report inAssign)
  {
    inAssign.setTheme("Initial Call", "2 min. or less");
    inAssign.setParticipantsA("Hearring, Jathen", "Hearring, Aidan", "9");
    inAssign.setParticipantsB("Hearring, Nadia", "Hearring, Alania", "9");
    return inAssign;
  }

  private static Report week1(String inMeeting)
  {
    Report ret = new Report();
    ret.setWeek("JUNE 05-11", "JEREMIAH 51-52", "Hearring, Simeon");
    ret.setMeeting(inMeeting);
    ret.setHall(Hall.MAIN.name());
    ret.setPart(Part.INITIAL_CALL.name());

    ret.setTime("7:00");
    return ret;
  }
}