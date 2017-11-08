package h.khall.client.model;

import h.style.g.client.model.AbstractPresenter;

public class SideBarPresenter extends AbstractPresenter<SideBarPresenter.Display>
{
  public SideBarPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }
}