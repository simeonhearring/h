package h.khall.client.model;

import h.khall.client.ui.event.LogoutEvent;
import h.style.g.client.model.AbstractPresenter;

public class PageFullPresenter extends AbstractPresenter<PageFullPresenter.Display>
{
  public PageFullPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void logout()
  {
    fire(new LogoutEvent());
  }

  public interface Display extends Attach
  {
  }
}