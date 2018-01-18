package h.khall.client.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;

import h.model.shared.khall.Charts;
import h.model.shared.khall.Person;
import h.model.shared.khall.Report;
import h.model.shared.khall.YrMo;
import h.style.g.client.ui.event.ChartEvent;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.chart.Chart.Dataset;
import h.style.g.shared.chart.Chart.Stat;

public class MinistryPresenter extends AbstractPresenter<MinistryPresenter.Display>
  implements RefreshEvent.Handler, ChangeHandler
{
  private enum V
  {
    Inactive,
    Reactivated,
    Irregular,
    Below_Threshold,
    Publisher_Average,
    Auxiliary_Average,
    Regular_Average;
  }

  private Chart mChart = chart();
  private Map<String, List<Long>> mNames;

  public MinistryPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mNames = new HashMap<>();
    addTypeList();
  }

  public MinistryPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    updateChart();
  }

  @Override
  public void onChange(ChangeEvent inEvent)
  {
    String ym = mDisplay.getYm();
    String type = mDisplay.getType();

    mDisplay.clearName();
    for (Person value : mClient.getPersons().gPersons(mNames.get(ym + type)))
    {
      mDisplay.addName(value.gName(), value.getId());
    }
  }

  private void updateChart()
  {
    int yr = 2017; // TODO
    int mo = 12;
    double th = mProfile.gThreshold();

    addPubList();

    List<Person> persons = mClient.getPersons().getPublishers();

    List<YrMo> yml = YrMo.past(yr, mo, 24);
    String[] yma = YrMo.toText(yml, 18);

    addMonthList(yma);

    Report.Stat stat = mClient.getReports().gStat(yml, persons, yma.length, th);

    putTypeLists(yma, stat);

    onChange(null);

    // mChart.getStat().setSubHead(TextUtil.toText(mProfile.getCount()) + "
    // Parts");
    mChart.update(yma);
    mChart.update(V.Inactive.name(), stat.getInactive().getValues());
    mChart.update(V.Irregular.name(), stat.getIrregular().getValues());
    mChart.update(V.Below_Threshold.name(), stat.getBelowThreshold().getValues());
    mChart.update(V.Reactivated.name(), stat.getReactivated().getValues());
    mChart.getStat().setTopRight("Current publisher count: " + persons.size());

    fire(new ChartEvent(mChart));
  }

  private void putTypeLists(String[] inMonths, Report.Stat inStats)
  {
    mNames.clear();
    for (String value : inMonths)
    {
      mNames.put(value + V.Inactive.name(), ensure(inStats.getInactive().getIds(), value));
      mNames.put(value + V.Reactivated.name(), ensure(inStats.getReactivated().getIds(), value));
      mNames.put(value + V.Irregular.name(), ensure(inStats.getIrregular().getIds(), value));
      mNames.put(value + V.Below_Threshold.name(), ensure(inStats.getBelowThreshold().getIds(), value));
    }
  }

  private void addTypeList()
  {
    mDisplay.clearType();
    for (V value : V.values())
    {
      mDisplay.addType(value.name(), value.name());
    }
  }

  private void addMonthList(String[] inMonths)
  {
    mDisplay.clearYm();
    for (String value : inMonths)
    {
      mDisplay.addYm(value, value);
    }
  }

  private void addPubList()
  {
    List<Person> persons = mClient.getPersons().getPublishers();

    Collections.sort(persons, mClient.getPersons());

    mDisplay.clearPublisher();
    for (Person value : persons)
    {
      mDisplay.addPublishers(value.gName(), value.getId());
    }
  }

  private List<Long> ensure(Map<String, List<Long>> inIds, String inMonth)
  {
    List<Long> ret = inIds.get(inMonth);
    if (ret == null)
    {
      ret = new ArrayList<>();
    }
    return ret;
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void clearYm();

    String getType();

    String getYm();

    void addName(String inItem, String inValue);

    void clearName();

    void addYm(String inItem, String inValue);

    void clearType();

    void addType(String inItem, String inValue);

    void clearPublisher();

    void addPublishers(String inItem, String inValue);
  }

  private static Chart chart()
  {
    Chart ret = new Chart(Chart.Type.LINE);

    Stat stat = new Stat();
    stat.setHead("Ministry Summary");
    stat.setSubHead("");
    stat.setTopRight(null);
    // stat.setFooter(Part.labels(true, " ", Part.student()));
    ret.setStat(stat);

    ret.setDataType(Charts.MINISTRY);

    ret.setResponsive(true);

    format(ret.createDataset(V.Inactive.name(), 0.0, 0.0, 0.0), 254);
    format(ret.createDataset(V.Irregular.name(), 0.0, 0.0, 0.0), 352);
    format(ret.createDataset(V.Below_Threshold.name(), 0.0, 0.0, 0.0), 152);
    format(ret.createDataset(V.Reactivated.name(), 0.0, 0.0, 0.0), 402);

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