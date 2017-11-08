package h.khall.client.model;

import h.style.g.client.model.Attach;

public class PagePresenter extends AbstractPresenter<PagePresenter.Display>
{
  public PagePresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends Attach
  {
  }
}