package h.khall.client.model;

import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.AttachEvent.TypeA;
import h.style.g.client.model.Attach;
import h.style.g.shared.command.ForgotCommand;

public class ForgotPresenter extends AbstractPresenter<ForgotPresenter.Display>
{
  public ForgotPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void forgot(String inEmail)
  {
    if (isEmail(inEmail))
    {
      mDisplay.notify("Password reset sent to... " + inEmail);
      fire(new ForgotCommand(inEmail), new AttachEvent(TypeA.LOGIN));
    }
    else
    {
      mDisplay.notify("Please enter a valid email address.");
    }
  }

  private boolean isEmail(String inEmail)
  {
    // TODO
    return inEmail != null && !"".equals(inEmail.trim()) && inEmail.indexOf("@") != -1
        && inEmail.indexOf(".") != -1;
  }

  public interface Display extends Attach
  {
  }
}