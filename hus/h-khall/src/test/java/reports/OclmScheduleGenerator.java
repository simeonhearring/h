package reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import h.khall.server.dao.bean.RandomName;
import h.model.shared.ReportBean;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Part;
import h.model.shared.khall.StudyPoint;

import net.sf.jasperreports.engine.JRDefaultScriptlet;

public class OclmScheduleGenerator extends JRDefaultScriptlet
{
  private static final RandomName RAND = new RandomName();

  public static Collection<ReportBean> createBeanCollection()
  {
    return styleOne();
  }

  public static List<ReportBean> styleOne()
  {
    List<ReportBean> ret = new ArrayList<>();

    assignments("JANUARY 04-10 2 CHRONICLES 29-32", ret, true, true);

    ret.add(new ReportBean(Part.EVENT, "JANUARY 11-17 2 CHRONICLES 33-36", "Circuit Assembly"));

    assignments("JANUARY 11-17 2 CHRONICLES 33-36", ret, false, true);

    assignments("JANUARY 18-24 EZRA 1-5", ret, false, true);

    assignments("JANUARY 25-31 EZRA 6-10", ret, false, true);

    assignments("FEBRUARY 1–7 NEHEMIAH 1-4", ret, true, true);

    assignments("FEBRUARY 8–14 NEHEMIAH 5-8", ret, false, true);

    assignments("FEBRUARY 15–21 NEHEMIAH 9-11", ret, false, true);

    assignments("FEBRUARY 22–28 NEHEMIAH 12-13", ret, false, true);

    return ret;
  }

  public static List<ReportBean> slips()
  {
    // slips & worksheet
    List<ReportBean> ret = new ArrayList<>();

    slips(ret, "Week of Jan 01, 2017", Hall.MAIN);
    slips(ret, "Week of Jan 01, 2017", Hall.SECOND);

    slips(ret, "Week of Jan 08, 2017", Hall.MAIN);
    slips(ret, "Week of Jan 08, 2017", Hall.SECOND);

    return ret;
  }

  private static void assignments(String inDate, List<ReportBean> inList, boolean inFirstWeek,
      boolean inSchools)
  {
    inList.add(
        rb(inDate, Part.CHAIRMAN, "", "Chairman", (Hall) null, (StudyPoint) null, "Gene Porter"));
    inList.add(rb(inDate, Part.SONG_1, "7:30", "Song 126 and Prayer", (Hall) null,
        (StudyPoint) null, "Chairman"));

    inList.add(rb(inDate, Part.OPEN, "7:35", "Opening Comments (3 min. or less)", (Hall) null,
        (StudyPoint) null, "Chairman"));

    inList.add(rb(inDate, Part.TREASURES, "7:38", "“Nehemiah Loved True Worship”: (10 min.)",
        (Hall) null, (StudyPoint) null, "John Nailer"));

    inList.add(rb(inDate, Part.DIGGING, "7:48",
        "Digging for Spiritual Gems: (8 min.)", (Hall) null, (StudyPoint) null, "Randy Burch"));

    inList.add(rb(inDate, Part.BIBLE_READING, "7:56", "Bible Reading: (4 min. or less)", null,
        StudyPoint.SP_5, "Jathen Hearring"));

    // inList.add(rb(inDate, Part.BIBLE_READING, "7:56", "Bible Reading: (4 min.
    // or less)",
    // Hall.SECOND, StudyPoint.SP_5, "Jathen Hearring"));

    if (inFirstWeek)
    {
      // inList.add(rb(inDate, Part.PREPARE_FOR_THIS_MONTHS_PRESENTATIONS,
      // "8:00",
      // "Prepare This Month’s Presentations: (15 min.)", (Hall) null,
      // (StudyPoint) null,
      // "Chairman"));
    }
    else
    {
      inList.add(rb(inDate, Part.INITIAL_CALL, "8:00", "Initial Call: (2 min. or less)", null,
          StudyPoint.SP_8, "Lyris O’Bard/Tara Allen"));
      // inList.add(rb(inDate, Part.INITIAL_CALL, "8:00", "Initial Call: (2 min.
      // or less)",
      // Hall.SECOND, StudyPoint.SP_48, "Shirley Amos/Barbara
      // Thompson"));

      inList.add(rb(inDate, Part.RETURN_VISIT, "8:03", "Return Visit: (4 min. or less)", null,
          StudyPoint.SP_11, "Ricardo Hearring/Wendell  McDaniel"));
      // inList.add(rb(inDate, Part.RETURN_VISIT, "8:03", "Return Visit: (4 min.
      // or less)",
      // Hall.SECOND, StudyPoint.SP_32, "Camilla Burch/Jennifer
      // Hearring"));

      inList.add(rb(inDate, Part.BIBLE_STUDY, "8:08", "Bible Study: (6 min. or less)", null,
          StudyPoint.SP_6, "Nicole Nailer/Johnnie Shipp"));
      // inList.add(rb(inDate, Part.BIBLE_STUDY, "8:08", "Bible Study: (6 min.
      // or less)",
      // Hall.SECOND, StudyPoint.SP_36, "Seneca O’Bard/Aidan
      // Hearring"));
    }

    inList.add(
        rb(inDate, Part.SONG_2, "8:15", "Song 103", (Hall) null, (StudyPoint) null, "Chairman"));

    inList.add(rb(inDate, Part.LIVING_1, "8:20",
        "Plan Now to Serve as an Auxiliary Pioneer During  March or April: (15 min.)",
        (Hall) null, (StudyPoint) null, "William McDaniel"));

    inList
        .add(rb(inDate, Part.C_BIBLE_STUDY, "8:35", "Congregation Bible Study (30 min.)",
            (Hall) null, (StudyPoint) null, "Simeon Hearring\nNathaniel Nailer"));

    inList.add(rb(inDate, Part.REVIEW, "9:05",
        "Review Followed by Preview of Next Week (3 min.)", (Hall) null, (StudyPoint) null,
        "Chairman"));

    inList.add(rb(inDate, Part.SONG_3, "9:05", "Song 135 and Prayer", (Hall) null,
        (StudyPoint) null, "Joey Bertrand"));
  }

  private static void slips(List<ReportBean> inList, String inDate, Hall inSchool)
  {
    inList.add(rb2(inDate, inSchool, Part.BIBLE_READING, StudyPoint.SP_1));

    inList.add(rb2(inDate, inSchool, Part.INITIAL_CALL, StudyPoint.SP_8));

    inList.add(rb2(inDate, inSchool, Part.RETURN_VISIT, StudyPoint.SP_11));

    inList.add(rb2(inDate, inSchool, Part.BIBLE_STUDY, StudyPoint.SP_6));
  }

  private static ReportBean rb(String inDateChap, Part inPart, String inTime, String inTalkName,
      Hall inSchool, StudyPoint inStudyPoint, String inParticipants)
  {
    return new ReportBean(inDateChap, inPart, inTime, inTalkName, inSchool, inStudyPoint,
        inParticipants, RAND.name(), RAND.name(), "JAN 01");
  }

  private static ReportBean rb2(String inDate, Hall inSchool, Part inPart,
      StudyPoint inStudyPoint)
  {
    return new ReportBean(inDate, inSchool, inPart, inStudyPoint, RAND.name(), RAND.name(), note());
  }

  public static String note()
  {

    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
    StringBuilder sb = new StringBuilder();

    Date d = new Date();
    String sp = StudyPoint.SP_51.item();
    String assist = "Hearring, Simeon";
    String note = "My Note";
    String part = Part.BIBLE_READING.getLabel(true);

    sb.append("<li>");
    sb.append(format.format(d)).append(" ");
    sb.append(sp).append(" <b>");
    sb.append(part).append("</b> ");
    sb.append(assist);
    if (note != null)
    {
      sb.append("  <sup>Note:</sup> ").append(note);
    }
    sb.append("</li>");

    sb.append("<li>");
    sb.append(format.format(d)).append(" ");
    sb.append(sp).append(" <b>");
    sb.append(part).append("</b> ");
    sb.append(assist);
    if (note != null)
    {
      sb.append("  <sup>Note:</sup> ").append(note);
    }
    sb.append("</li>");
    return sb.toString();
  }
}