package h.khall.client.model;

public class LoginPresenter
{
  private LoginDisplay mDisplay;

  public LoginPresenter(LoginDisplay inDisplay)
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