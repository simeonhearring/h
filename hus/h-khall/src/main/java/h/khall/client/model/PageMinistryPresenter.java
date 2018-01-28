package h.khall.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;

import h.model.shared.khall.Charts;
import h.model.shared.khall.FieldServiceGroup;
import h.model.shared.khall.Person;
import h.model.shared.khall.Report;
import h.model.shared.khall.YrMo;
import h.model.shared.util.StringUtil;
import h.style.g.client.ui.event.ChartEvent;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.chart.Chart.Stat;

public class PageMinistryPresenter extends AbstractPresenter<PageMinistryPresenter.Display>
  implements RefreshEvent.Handler, ChangeHandler
{
  private enum V
  {
    Below_Threshold,
    Above_Threshold,
    Inactive,
    Reactivated,
    Irregular;

    public String gLabel()
    {
      return StringUtil.toTitle(this);
    }
  }

  private Chart mChart = chart();
  private Map<String, List<Long>> mNames;

  public PageMinistryPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mNames = new HashMap<>();
    addTypeList();
  }

  public PageMinistryPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    updateChart();
    addPubList();
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
    int yr = mProfile.gCurrentServiceYear();
    int mo = mProfile.gCurrentServiceMonth();
    double th = mProfile.gThreshold();

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
    mChart.update(V.Below_Threshold.gLabel(), stat.getBelowThreshold().getValues());
    mChart.update(V.Above_Threshold.gLabel(), stat.getAboveThreshold().getValues());
    mChart.update(V.Inactive.gLabel(), stat.getInactive().getValues());
    mChart.update(V.Irregular.gLabel(), stat.getIrregular().getValues());
    mChart.update(V.Reactivated.gLabel(), stat.getReactivated().getValues());
    mChart.getStat().setTopRight("Current publisher count: " + persons.size());

    fire(new ChartEvent(mChart));
  }

  private void putTypeLists(String[] inMonths, Report.Stat inStats)
  {
    mNames.clear();
    for (String value : inMonths)
    {
      mNames.put(value + V.Below_Threshold.name(), ensure(inStats.getBelowThreshold().getIds(), value));
      mNames.put(value + V.Above_Threshold.name(), ensure(inStats.getAboveThreshold().getIds(), value));
      mNames.put(value + V.Inactive.name(), ensure(inStats.getInactive().getIds(), value));
      mNames.put(value + V.Reactivated.name(), ensure(inStats.getReactivated().getIds(), value));
      mNames.put(value + V.Irregular.name(), ensure(inStats.getIrregular().getIds(), value));
    }
  }

  private void addTypeList()
  {
    mDisplay.clearType();
    for (V value : V.values())
    {
      mDisplay.addType(value.gLabel(), value.name());
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
    mDisplay.clearPublisher();
    for (Person value : mClient.getPersons().getPublishers())
    {
      mDisplay.addPublishers(value.gName(), value.getId());
    }
  }

  public void filterFsg(Integer inFsgId)
  {
    if (inFsgId.intValue() <= 0)
    {
      if (FieldServiceGroup.isElderOrServant(inFsgId))
      {
        mDisplay.clearPublisher();
        for (Person value : mClient.getPersons().getEldersOrServants())
        {
          mDisplay.addPublishers(value.gName(), value.getId());
        }
      }
      else if (FieldServiceGroup.isPioneers(inFsgId))
      {
        mDisplay.clearPublisher();
        for (Person value : mClient.getPersons().getRegular())
        {
          mDisplay.addPublishers(value.gName(), value.getId());
        }
      }
      else
      {
        addPubList();
      }
    }
    else
    {
      mDisplay.clearPublisher();
      for (Person value : mClient.getPersons().getPubFsg(inFsgId))
      {
        mDisplay.addPublishers(value.gName(), value.getId());
      }
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
    stat.setHead("Congregation");
    stat.setSubHead("");
    stat.setTopRight(null);
    // stat.setFooter(Part.labels(true, " ", Part.student()));
    ret.setStat(stat);

    ret.setDataType(Charts.MINISTRY);

    ret.setResponsive(true);

    format(ret.createDataset(V.Below_Threshold.gLabel(), 0.0, 0.0, 0.0), 152);
    format(ret.createDataset(V.Above_Threshold.gLabel(), 0.0, 0.0, 0.0), 632);
    format(ret.createDataset(V.Inactive.gLabel(), 0.0, 0.0, 0.0), 254);
    format(ret.createDataset(V.Irregular.gLabel(), 0.0, 0.0, 0.0), 352);
    format(ret.createDataset(V.Reactivated.gLabel(), 0.0, 0.0, 0.0), 402);

    return ret;
  }
}