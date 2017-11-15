package h.khall.client.model;

import h.khall.client.ui.event.MidweekEvent;
import h.khall.client.ui.event.ParticipantEvent;
import h.khall.client.ui.event.SampleEvent;
import h.style.g.client.model.Attach;

public class PagePresenter extends AbstractPresenter<PagePresenter.Display>
  implements SampleEvent.Handler, MidweekEvent.Handler, ParticipantEvent.Handler
{
  public PagePresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PagePresenter handlers()
  {
    addHandler(SampleEvent.TYPE, this);
    addHandler(MidweekEvent.TYPE, this);
    addHandler(ParticipantEvent.TYPE, this);
    return this;
  }

  @Override
  public void dispatch(SampleEvent inEvent)
  {
    mDisplay.sample();
  }

  @Override
  public void dispatch(MidweekEvent inEvent)
  {
    mDisplay.midweek();
  }

  @Override
  public void dispatch(ParticipantEvent inEvent)
  {
    mDisplay.participants();
  }

  public interface Display extends Attach
  {
    void midweek();

    void sample();

    void participants();
  }
}