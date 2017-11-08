package h.khall.client.model;

import h.style.g.client.model.Attach;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.chart.Chart;

public class ChartPresenter extends AbstractPresenter<ChartPresenter.Display>
  implements RefreshEvent.Handler
{
  private String mDataType;

  public ChartPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    Chart chart = mProfile.getClient().getChart();
    if (mDataType.equals(chart.getDataType()))
    {
      mDisplay.load(chart);
    }
  }

  public void setDataType(String inDataType)
  {
    mDataType = inDataType;
  }

  public interface Display extends Attach
  {
    void load(Chart inChart);
  }
}