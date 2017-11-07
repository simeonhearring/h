package h.khall.client.model;

import h.khall.client.ui.event.LogoutEvent;
import h.khall.client.ui.event.UserInfoEvent;
import h.style.g.client.model.AbstractPresenter;
import h.style.g.client.ui.common.Global;

public class PageFullPresenter extends AbstractPresenter<PageFullPresenter.Display>
    implements UserInfoEvent.Handler
{
  public PageFullPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    addHandler(UserInfoEvent.TYPE, this);
    dispatch(new UserInfoEvent(Global.info().getUserName(), Global.info().getUserTitle()));
  }

  public void logout()
  {
    fire(new LogoutEvent());
  }

  @Override
  public void dispatch(UserInfoEvent inEvent)
  {
    mDisplay.setUserName(inEvent.getName());
    mDisplay.setUserTitle(inEvent.getTitle());
  }

  public interface Display extends Attach
  {
    void setUserName(String inName);

    void setUserTitle(String inTitle);
  }
}