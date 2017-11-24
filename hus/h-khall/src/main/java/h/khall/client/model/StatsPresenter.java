package h.khall.client.model;

import java.util.Date;

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
      mDisplay.setTime(format("h:mm a", new Date()));

      if (inChart.getStat() != null)
      {
        mDisplay.setHead(inChart.getStat().getHead());
        mDisplay.setSubHead(inChart.getStat().getSubHead());
        mDisplay.setTopRight(inChart.getStat().getTopRight());
        mDisplay.setFooter(inChart.getStat().getFooter());
      }
    }
  }

  public void setDataType(String inDataType)
  {
    mDataType = inDataType;
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void load(Chart inChart);

    void setDataType(String inDataType);

    void setTime(String inText);

    void setFooter(String inText);

    void setTopRight(String inText);

    void setSubHead(String inText);

    void setHead(String inText);
  }
}