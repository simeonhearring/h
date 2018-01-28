package h.khall.client.model.pages;

import h.khall.client.model.AbstractPresenter;
import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.AttachEvent.TypeA;
import h.model.shared.khall.Profile;
import h.model.shared.util.StringUtil;
import h.style.g.client.model.Attach;
import h.style.g.shared.command.ForgotCommand;

public class PageForgotPresenter extends AbstractPresenter<PageForgotPresenter.Display>
{
  public PageForgotPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void forgot(String inEmail, String inEncrypt)
  {
    if (StringUtil.isEmail(inEmail))
    {
      Profile profile = new Profile();
      profile.setUserId(mDisplay.encrypt(inEmail));
      profile.setEncrypt(mDisplay.encrypt(inEncrypt));

      mDisplay.notify("Password reset instructions sent to... " + inEmail);
      fire(new ForgotCommand(profile), new AttachEvent(TypeA.LOGIN));
    }
    else
    {
      mDisplay.notify("Please enter a valid email address.");
    }
  }

  public void cancel()
  {
    fire(new AttachEvent(TypeA.LOGIN));
  }

  public interface Display extends Attach
  {
  }
}