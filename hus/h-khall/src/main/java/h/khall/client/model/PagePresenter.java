package h.khall.client.model;

import h.khall.client.ui.event.MidweekEvent;
import h.khall.client.ui.event.MinistryEvent;
import h.khall.client.ui.event.ParticipantEvent;
import h.khall.client.ui.event.SampleEvent;
import h.style.g.client.model.Attach;

public class PagePresenter extends AbstractPresenter<PagePresenter.Display>
  implements SampleEvent.Handler, MidweekEvent.Handler, ParticipantEvent.Handler,
  MinistryEvent.Handler
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
    addHandler(MinistryEvent.TYPE, this);
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
  public void dispatch(MinistryEvent inEvent)
  {
    mDisplay.ministry();
  }

  @Override
  public void dispatch(ParticipantEvent inEvent)
  {
    mDisplay.participants();
  }

  public interface Display extends Attach
  {
    void ministry();

    void midweek();

    void sample();

    void participants();
  }
}