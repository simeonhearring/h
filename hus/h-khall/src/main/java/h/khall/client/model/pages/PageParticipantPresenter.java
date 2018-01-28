package h.khall.client.model.pages;

import h.khall.client.model.AbstractPresenter;

public class PageParticipantPresenter extends AbstractPresenter<PageParticipantPresenter.Display>
{
  public PageParticipantPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}