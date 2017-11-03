package h.khall.client.model;

import h.style.g.client.model.AbstractPresenter;

public class LoginPresenter extends AbstractPresenter<LoginPresenter.Display>
{
  public interface Display extends Attach
  {
    void register();

    void loggedIn();
  }

  public LoginPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void login(String inUserName, String inPassword, String inCongNum, String inEncrypt)
  {
    mDisplay.notify("login: " + inUserName);
    mDisplay.loggedIn();
  }

  public void forgotPassword()
  {
    mDisplay.notify("forgot password");
  }

  public void register()
  {
    mDisplay.notify("register");
    mDisplay.register();
  }
}