package h.khall.client.model;

import h.khall.client.ui.event.LogoutEvent;
import h.style.g.client.ui.event.RefreshEvent;

public class SideNavPresenter extends AbstractPresenter<SideNavPresenter.Display>
    implements RefreshEvent.Handler
{
  public SideNavPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    addHandler(RefreshEvent.TYPE, this);
  }

  public void logout()
  {
    fire(new LogoutEvent());
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    mDisplay.setUserName(mProfile.getUserName());
    mDisplay.setUserTitle(mProfile.getUserTitle());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setUserName(String inName);

    void setUserTitle(String inTitle);
  }
}