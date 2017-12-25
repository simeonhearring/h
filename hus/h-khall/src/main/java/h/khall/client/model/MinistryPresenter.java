package h.khall.client.model;

import h.model.shared.khall.Charts;
import h.model.shared.khall.Part;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.chart.Chart.Dataset;
import h.style.g.shared.chart.Chart.Stat;

public class MinistryPresenter extends AbstractPresenter<MinistryPresenter.Display>
  implements RefreshEvent.Handler
{
  private enum V
  {
    Assignments,
    Assigned;
  }

  public MinistryPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public MinistryPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
  }

  public interface Display extends h.style.g.client.model.Display
  {
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

    ret.setDataType(Charts.REPORT);

    ret.setResponsive(true);

    // ret.addLabel(sMonthNames[0], sMonthNames[1], sMonthNames[2]);

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