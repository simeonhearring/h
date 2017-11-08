package h.khall.client.model;

import h.khall.client.ui.event.LoginEvent;
import h.style.g.client.model.AbstractPresenter;
import h.style.g.client.model.Attach;

public class RegisterPresenter extends AbstractPresenter<RegisterPresenter.Display>
{
  public RegisterPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void register(String inCongNme, String inCongNum, String inEmail, String inLast,
      String inFirst)
  {
    mDisplay.notify("register: " + inCongNme);
  }

  public void login()
  {
    fire(new LoginEvent());
  }

  public interface Display extends Attach
  {
  }
}