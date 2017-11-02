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
  }

  public void login()
  {
    mDisplay.notify("login");
  }

  public interface Display extends Attach
  {
  }
}