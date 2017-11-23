package h.khall.client.model;

import h.style.g.client.ui.event.ChartEvent;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.chart.Chart;

public class StatsPresenter extends AbstractPresenter<StatsPresenter.Display>
  implements RefreshEvent.Handler, ChartEvent.Handler
{
  private String mDataType;

  public StatsPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    register(addHandler(ChartEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    update(mClient.getChart());
  }

  @Override
  public void dispatch(ChartEvent inEvent)
  {
    update(inEvent.getChart());
  }

  private void update(Chart inChart)
  {
    if (mDataType.equals(inChart.getDataType()))
    {
      mDisplay.load(inChart);
    }
  }

  public void setDataType(String inDataType)
  {
    mDataType = inDataType;
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void load(Chart inChart);
  }
}