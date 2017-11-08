package h.khall.client.model;

import h.style.g.client.model.Attach;
import h.style.g.client.ui.event.ChartEvent;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.chart.Chart;

public class ChartPresenter extends AbstractPresenter<ChartPresenter.Display>
implements RefreshEvent.Handler, ChartEvent.Handler
{
  public ChartPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public ChartPresenter events()
  {
    addHandler(RefreshEvent.TYPE, this);
    addHandler(ChartEvent.TYPE, this);
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    mDisplay.load(mProfile.getClient().getChart());
  }

  @Override
  public void dispatch(ChartEvent inEvent)
  {
    // TODO not used
    mDisplay.load(inEvent.getChart());
  }

  public interface Display extends Attach
  {
    void load(Chart inChart);
  }
}