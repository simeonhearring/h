package h.khall.client.model;

import h.style.g.client.model.AbstractPresenter;

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
    mDisplay.login();
  }

  public void login()
  {
    mDisplay.notify("login");
    mDisplay.login();
  }

  public interface Display extends Attach
  {
    void login();
  }
}