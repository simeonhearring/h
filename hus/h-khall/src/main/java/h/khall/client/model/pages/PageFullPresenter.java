package h.khall.client.model.pages;

import h.khall.client.model.AbstractPresenter;
import h.style.g.client.model.Attach;

public class PageFullPresenter extends AbstractPresenter<PageFullPresenter.Display>
{
  public PageFullPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends Attach
  {
  }
}