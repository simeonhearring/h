package h.khall.client.model;

import h.khall.client.ui.event.AssignmentSavedEvent;
import h.model.shared.khall.Charts;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Meeting.Month;
import h.model.shared.khall.Meeting.Year;
import h.model.shared.khall.Part;
import h.model.shared.util.TextUtil;
import h.style.g.client.ui.event.ChartEvent;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.chart.Chart.Dataset;
import h.style.g.shared.chart.Chart.Stat;

public class MidweekPresenter extends AbstractPresenter<MidweekPresenter.Display>
  implements RefreshEvent.Handler, AssignmentSavedEvent.Handler
{
  private enum V
  {
    Assignments,
    Assigned;
  }

  private static String[] sMonthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
  private static int[][] sMonths = {{0,1,2}, {3,4,5}, {6,7,8}, {9,10,11}};
  private int[] mYears;
  private int mYearIndex = 0;
  private int mMonthIndex = 0;
  private Chart mChart = chart();

  public MidweekPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mMonthIndex = currentIndex();
  }

  public MidweekPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    register(addHandler(AssignmentSavedEvent.TYPE, this));
    return this;
  }

  public void previous()
  {
    boolean yearChg = mMonthIndex == 0;
    mMonthIndex = yearChg ? ending() : mMonthIndex - 1;
    addMonths(yearChg);
  }

  private int ending()
  {
    mYearIndex = mYearIndex == 0 ? mYears.length - 1 : mYearIndex - 1;
    return sMonths.length - 1;
  }

  public void next()
  {
    boolean yearChg = mMonthIndex == sMonths.length - 1;
    mMonthIndex = yearChg ? beginning() : mMonthIndex + 1;
    addMonths(yearChg);
  }

  private int beginning()
  {
    mYearIndex = mYearIndex == mYears.length - 1 ? 0 : mYearIndex + 1;
    return 0;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    mYears = mProfile.gYears();
    addMonths(true);
  }

  @Override
  public void dispatch(AssignmentSavedEvent inEvent)
  {
    updateChart();
  }

  private void addMonths(boolean inUpdateChart)
  {
    mDisplay.getMonth0().reset();
    mDisplay.getMonth1().reset();
    mDisplay.getMonth2().reset();

    Meeting meeting = mClient.getMeeting();

    int yr = mYears[mYearIndex];

    int mn0 = sMonths[mMonthIndex][0];
    int mn1 = sMonths[mMonthIndex][1];
    int mn2 = sMonths[mMonthIndex][2];

    Month mo0 = meeting.getMonth(yr, mn0);
    Month mo1 = meeting.getMonth(yr, mn1);
    Month mo2 = meeting.getMonth(yr, mn2);

    mDisplay.getMonth0().setMonth(yr, mn0, mo0);
    mDisplay.getMonth1().setMonth(yr, mn1, mo1);
    mDisplay.getMonth2().setMonth(yr, mn2, mo2);

    if (inUpdateChart)
    {
      updateChart();
    }
  }

  private void updateChart()
  {
    Meeting meeting = mClient.getMeeting();
    meeting.setCount(mProfile.getCount());

    Year year = meeting.getYear(mYears[mYearIndex]);

    mChart.getStat().setSubHead(TextUtil.toText(mProfile.getCount()) + " Parts");
    mChart.update(sMonthNames);
    mChart.update(V.Assignments.name(), year.gCountM());
    mChart.update(V.Assigned.name(), year.gAssignedM());
    mChart.getStat().setTopRight("Year: " + mYears[mYearIndex]);

    fire(new ChartEvent(mChart));
  }

  private int currentIndex()
  {
    int ret = 0;
    // @SuppressWarnings("deprecation")
    // int mo = new Date().getMonth();
    // for (int i = 0; i < mRange.length; i++)
    // {
    // for (int value : mRange[i])
    // {
    // if (mo == value)
    // {
    // ret = i;
    // break;
    // }
    // }
    // }
    return ret;
  }

  public interface Display extends h.style.g.client.model.Display
  {
    MonthPresenter.Display getMonth0();

    MonthPresenter.Display getMonth1();

    MonthPresenter.Display getMonth2();
  }

  private static Chart chart()
  {
    Chart ret = new Chart(Chart.Type.LINE);

    Stat stat = new Stat();
    stat.setHead("Assignment Summary");
    stat.setSubHead("Student Parts");
    stat.setTopRight(null);
    stat.setFooter(Part.labels(true, " ", Part.student()));
    ret.setStat(stat);

    ret.setDataType(Charts.OCLM);

    ret.setResponsive(true);

    ret.addLabel(sMonthNames[0], sMonthNames[1], sMonthNames[2]);

    format(ret.createDataset(V.Assignments.name(), 0.0, 0.0, 0.0), 254);
    format(ret.createDataset(V.Assigned.name(), 0.0, 0.0, 0.0), 352);

    return ret;
  }

  private static void format(Dataset inSet, int inColor)
  {
    // http://standardista.com/webkit/ch7/hsla.html
    String c1 = "hsla(" + inColor + ",67%,51%,";
    inSet.setBorderColor(c1 + "0." + 9 + ")");
    // inSet.setBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    // inSet.setPointBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setPointBorderColor("#fff");
  }
}