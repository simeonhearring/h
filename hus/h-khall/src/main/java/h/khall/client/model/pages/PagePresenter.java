package h.khall.client.model.pages;

import h.khall.client.model.AbstractPresenter;
import h.khall.client.ui.event.PageAnalysisEvent;
import h.khall.client.ui.event.PageMidweekEvent;
import h.khall.client.ui.event.PageMinistryEvent;
import h.khall.client.ui.event.PageParticipantEvent;
import h.khall.client.ui.event.PageSampleEvent;
import h.style.g.client.model.Attach;

public class PagePresenter extends AbstractPresenter<PagePresenter.Display>
  implements PageSampleEvent.Handler, PageMidweekEvent.Handler, PageParticipantEvent.Handler,
  PageMinistryEvent.Handler, PageAnalysisEvent.Handler
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
    addHandler(PageAnalysisEvent.TYPE, this);
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

  @Override
  public void dispatch(PageAnalysisEvent inEvent)
  {
    mDisplay.analysis();
  }

  public interface Display extends Attach
  {
    void ministry();

    void midweek();

    void sample();

    void participants();

    void analysis();
  }
}