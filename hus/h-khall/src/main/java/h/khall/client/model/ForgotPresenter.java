package h.khall.client.model;

import h.style.g.client.model.Attach;

public class ForgotPresenter extends AbstractPresenter<ForgotPresenter.Display>
{
  public ForgotPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void forgot(String inEmail)
  {
    mDisplay.notify("Forgot: " + inEmail);
  }

  public interface Display extends Attach
  {
  }
}