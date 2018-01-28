package h.khall.client.model;

import h.khall.client.ui.event.PageMidweekEvent;
import h.khall.client.ui.event.PageMinistryEvent;
import h.khall.client.ui.event.PageParticipantEvent;
import h.khall.client.ui.event.PageSampleEvent;
import h.style.g.client.model.Attach;

public class PagePresenter extends AbstractPresenter<PagePresenter.Display>
  implements PageSampleEvent.Handler, PageMidweekEvent.Handler, PageParticipantEvent.Handler,
  PageMinistryEvent.Handler
{
  public PagePresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PagePresenter handlers()
  {
    addHandler(PageSampleEvent.TYPE, this);
    addHandler(PageMidweekEvent.TYPE, this);
    addHandler(PageParticipantEvent.TYPE, this);
    addHandler(PageMinistryEvent.TYPE, this);
    return this;
  }

  @Override
  public void dispatch(PageSampleEvent inEvent)
  {
    mDisplay.sample();
  }

  @Override
  public void dispatch(PageMidweekEvent inEvent)
  {
    mDisplay.midweek();
  }

  @Override
  public void dispatch(PageMinistryEvent inEvent)
  {
    mDisplay.ministry();
  }

  @Override
  public void dispatch(PageParticipantEvent inEvent)
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