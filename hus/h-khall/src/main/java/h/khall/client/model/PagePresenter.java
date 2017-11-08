package h.khall.client.model;

import h.khall.client.ui.event.MidweekEvent;
import h.style.g.client.model.Attach;

public class PagePresenter extends AbstractPresenter<PagePresenter.Display>
  implements MidweekEvent.Handler
{
  public PagePresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PagePresenter handlers()
  {
    addHandler(MidweekEvent.TYPE, this);
    return this;
  }

  @Override
  public void dispatch(MidweekEvent inEvent)
  {
    mDisplay.midweek();
  }

  public interface Display extends Attach
  {
    void midweek();
  }
}