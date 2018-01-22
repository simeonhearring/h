package h.khall.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.HasText;

import h.khall.shared.command.ReportSaveCommand;
import h.model.shared.khall.Charts;
import h.model.shared.khall.FieldServiceGroup;
import h.model.shared.khall.Profile.Security;
import h.model.shared.khall.Report;
import h.model.shared.khall.Report.PubRange;
import h.model.shared.khall.YrMo;
import h.model.shared.util.StringUtil;
import h.style.g.client.ui.event.ChartEvent;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.chart.Chart.Stat;

public class MinistryYearPresenter extends AbstractPresenter<MinistryYearPresenter.Display>
  implements RefreshEvent.Handler
{
  private enum V
  {
    Hours,
    Return_Visits,
    Placements,
    Bible_Studies,
    Video_Showings;

    public String gLabel()
    {
      return StringUtil.toTitle(this);
    }
  }

  private Long mPubId;
  private int[] mYearMonth;
  private Chart mChart = chart();

  private Map<Integer, MinistryMonthPresenter.Display> mMap;

  public MinistryYearPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mMap = new HashMap<>();
    mDisplay.editReport(isEdit(Security.REPORT));
  }

  public MinistryYearPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    List<Long> pubIds = mClient.getPersons().getPubIds();
    String name = "Congregation";
    updateChart(pubIds, name);
  }

  public void changePub(Long inPubId, int[] inYearMonth)
  {
    mPubId = inPubId;
    mYearMonth = inYearMonth;
    addReports();
    updateChartPub();
  }

  public void changeMonth(int[] inYearMonth)
  {
    mYearMonth = inYearMonth;
    if (mPubId != null)
    {
      addReports();
    }
    else
    {
      mDisplay.notify("Select publisher");
    }
  }

  public void put(int inMonth, MinistryMonthPresenter.Display inDisplay)
  {
    mMap.put(inMonth, inDisplay);
  }

  public void save()
  {
    List<Report> list = new ArrayList<>();
    for (MinistryMonthPresenter.Display value : mMap.values())
    {
      if (value.isDirty())
      {
        int[] ym = value.getYearMonth();
        Report report = mClient.getReports().gReport(mProfile.getCongId(), mPubId, ym[0], ym[1]);
        value.report(report);
        list.add(report);
        value.setDirty(false);
      }
    }

    if (list.size() > 0)
    {
      fire(new ReportSaveCommand(list));
    }
  }

  private void addReports()
  {
    for (Report value : gReports())
    {
      MinistryMonthPresenter.Display display = mMap.get(value.getMonth());
      display.display(value);
      display.setYearMonth(value.getYear(), value.getMonth());
    }
  }

  private List<Report> gReports()
  {
    return mClient.getReports().gReports(mProfile.getCongId(), mPubId, mYearMonth[0], mYearMonth[1]);
  }

  public void chartFsg(Integer inFsgId)
  {
    if (inFsgId.intValue() <= 0)
    {
      if (FieldServiceGroup.isElderOrServant(inFsgId))
      {
        List<Long> pubIds = mClient.getPersons().getElderServantIds();
        String name = "Elders and Servants";
        updateChart(pubIds, name);
      }
      else if (FieldServiceGroup.isPioneers(inFsgId))
      {
        List<Long> pubIds = mClient.getPersons().getRegularIds();
        String name = "Regular Pioneers";
        updateChart(pubIds, name);
      }
      else
      {
        List<Long> pubIds = mClient.getPersons().getPubIds();
        String name = "Congregation";
        updateChart(pubIds, name);
      }
    }
    else
    {
      List<Long> pubIds = mClient.getPersons().getPubFsgIds(inFsgId);
      String name = mClient.getCong().getFsgs().get(inFsgId).getTitle();
      updateChart(pubIds, name);
    }
  }

  private void updateChartPub()
  {
    int yr = mProfile.gCurrentServiceYear();
    int mo = mProfile.gCurrentServiceMonth();

    List<YrMo> yml = YrMo.past(yr, mo, 24);
    String[] yma = YrMo.toText(yml, 18);

    PubRange range = mClient.getReports().gPubRange(mPubId, yml);

    String name = mClient.gName(mPubId);
    mDisplay.getName().setText(name);

    updateChart(name, yma, range);
  }

  private void updateChart(List<Long> inPubIds, String inName)
  {
    int yr = mProfile.gCurrentServiceYear();
    int mo = mProfile.gCurrentServiceMonth();

    List<YrMo> yml = YrMo.past(yr, mo, 24);
    String[] yma = YrMo.toText(yml, 18);

    PubRange range = mClient.getReports().gPubRange(inPubIds, yml);

    mDisplay.getName().setText(inName);

    updateChart(inName, yma, range);
  }

  private void updateChart(String inName, String[] inYms, PubRange inRange)
  {
    long avg3mo = Math.round(inRange.gAverage(3, inRange.getHours()));
    long avg6mo = Math.round(inRange.gAverage(6, inRange.getHours()));
    long avg12mo = Math.round(inRange.gAverage(12, inRange.getHours()));

    // mChart.getStat().setSubHead(TextUtil.toText(mProfile.getCount()) + "
    // Parts");
    mChart.update(inYms);
    mChart.update(V.Hours.gLabel(), inRange.getHours());
    mChart.update(V.Return_Visits.gLabel(), inRange.getReturnVisits());
    mChart.update(V.Placements.gLabel(), inRange.getPlacements());
    mChart.update(V.Bible_Studies.gLabel(), inRange.getBibleStudies());
    mChart.update(V.Video_Showings.gLabel(), inRange.getVideoShowings());
    mChart.getStat().setTopRight(inName);
    mChart.getStat().setFooter("HOUR AVG: 3 mo=" + avg3mo + " ... 6 mo=" + avg6mo + " ... 12 mo=" + avg12mo);

    fire(new ChartEvent(mChart));
  }

  private static Chart chart()
  {
    Chart ret = new Chart(Chart.Type.LINE);

    Stat stat = new Stat();
    stat.setHead("Publisher");
    stat.setSubHead("");
    stat.setTopRight(null);
    // stat.setFooter(Part.labels(true, " ", Part.student()));
    ret.setStat(stat);

    ret.setDataType(Charts.PUB_MINISTRY);

    ret.setResponsive(true);

    format(ret.createDataset(V.Hours.gLabel(), 0.0, 0.0, 0.0), 254);
    format(ret.createDataset(V.Return_Visits.gLabel(), 0.0, 0.0, 0.0), 402);
    format(ret.createDataset(V.Placements.gLabel(), 0.0, 0.0, 0.0), 152);
    format(ret.createDataset(V.Bible_Studies.gLabel(), 0.0, 0.0, 0.0), 352);
    format(ret.createDataset(V.Video_Showings.gLabel(), 0.0, 0.0, 0.0), 552);

    return ret;
  }

  public interface Display extends h.style.g.client.model.Display
  {
    HasText getName();

    void editReport(boolean inEnable);
  }
}