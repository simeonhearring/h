package h.khall.client.model;

import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.AttachEvent.TypeA;
import h.model.shared.util.StringUtil;
import h.style.g.client.model.Attach;
import h.style.g.shared.command.ForgotCommand;

public class PageForgotPresenter extends AbstractPresenter<PageForgotPresenter.Display>
{
  public PageForgotPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void forgot(String inEmail)
  {
    if (StringUtil.isEmail(inEmail))
    {
      mDisplay.notify("Password reset instructions sent to... " + inEmail);
      fire(new ForgotCommand(inEmail), new AttachEvent(TypeA.LOGIN));
    }
    else
    {
      mDisplay.notify("Please enter a valid email address.");
    }
  }

  public interface Display extends Attach
  {
  }
}