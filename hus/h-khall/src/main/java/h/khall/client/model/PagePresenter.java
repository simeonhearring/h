package h.khall.client.model;

import h.style.g.client.model.AbstractPresenter;

public class PagePresenter extends AbstractPresenter<PagePresenter.Display>
{
  public interface Display extends Attach
  {
  }

  public PagePresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }
}