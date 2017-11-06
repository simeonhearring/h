package h.khall.client.model;

import h.khall.client.ui.event.PageEvent;
import h.khall.client.ui.event.RegisterEvent;
import h.style.g.client.model.AbstractPresenter;

public class ChartPresenter extends AbstractPresenter<ChartPresenter.Display>
{
  public ChartPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void login(String inUserName, String inPassword, String inCongNum, String inEncrypt)
  {
    fire(new PageEvent());
  }

  public void forgotPassword()
  {
    mDisplay.notify("forgot password");
  }

  public void register()
  {
    fire(new RegisterEvent());
  }

  public interface Display extends Attach
  {
  }
}