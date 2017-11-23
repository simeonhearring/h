package h.khall.client.model;

import h.khall.client.ui.event.AssignmentSavedEvent;
import h.model.shared.khall.Charts;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Meeting.Month;
import h.model.shared.khall.Meeting.Year;
import h.style.g.client.ui.event.ChartEvent;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.chart.Chart.Dataset;

public class MidweekPresenter extends AbstractPresenter<MidweekPresenter.Display>
  implements RefreshEvent.Handler, AssignmentSavedEvent.Handler
{
  private enum V
  {
    Assignments,
    Assigned;
  }

  private static String[] sMonthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
  private static int[][] sRange = {{0,1,2}, {3,4,5}, {6,7,8}, {9,10,11}};
  private int mPageIndex = 0;
  private Chart mChart = chart();

  public MidweekPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mPageIndex = currentIndex();
  }

  public MidweekPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    register(addHandler(AssignmentSavedEvent.TYPE, this));
    return this;
  }

  public void previous()
  {
    mPageIndex = mPageIndex == 0 ? sRange.length - 1 : mPageIndex - 1;
    addMonths();
  }

  public void next()
  {
    mPageIndex = mPageIndex == sRange.length - 1 ? 0 : mPageIndex + 1;
    addMonths();
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    addMonths();
    addYearChart();
  }

  @Override
  public void dispatch(AssignmentSavedEvent inEvent)
  {
    addYearChart();
  }

  private void addMonths()
  {
    mDisplay.getMonth0().reset();
    mDisplay.getMonth1().reset();
    mDisplay.getMonth2().reset();

    Meeting meeting = mClient.getMeeting();

    int yr = mProfile.getYear();

    int mn0 = sRange[mPageIndex][0];
    int mn1 = sRange[mPageIndex][1];
    int mn2 = sRange[mPageIndex][2];

    Month mo0 = meeting.getMonth(yr, mn0);
    Month mo1 = meeting.getMonth(yr, mn1);
    Month mo2 = meeting.getMonth(yr, mn2);

    mDisplay.getMonth0().setMonth(yr, mn0, mo0);
    mDisplay.getMonth1().setMonth(yr, mn1, mo1);
    mDisplay.getMonth2().setMonth(yr, mn2, mo2);
  }

  private void addYearChart()
  {
    int yr = mProfile.getYear();

    Year year = mClient.getMeeting().getYear(yr);

    mChart.update(sMonthNames);
    mChart.update(V.Assignments.name(), year.gCountM());
    mChart.update(V.Assigned.name(), year.gAssignedM());

    fire(new ChartEvent(mChart));
  }

  private int currentIndex()
  {
    int ret = 0;
    // TODO
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