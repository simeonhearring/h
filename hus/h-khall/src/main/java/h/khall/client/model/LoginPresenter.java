package h.khall.client.model;

public class LoginPresenter
{
  public interface Display extends h.style.g.client.model.Display
  {
  }

  private Display mDisplay;

  public LoginPresenter(Display inDisplay)
  {
    mDisplay = inDisplay;
  }

  public void login(String inUserName, String inPassword, String inCongNum, String inEncrypt)
  {
    mDisplay.notify("login: " + inUserName);
  }

  public void forgotPassword()
  {
    mDisplay.notify("forgot password");
  }

  public void register()
  {
    mDisplay.notify("register");
  }
}