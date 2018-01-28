package h.khall.client.model.pages;

import h.khall.client.model.AbstractPresenter;

public class PageSamplePresenter extends AbstractPresenter<PageSamplePresenter.Display>
{
  public PageSamplePresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}