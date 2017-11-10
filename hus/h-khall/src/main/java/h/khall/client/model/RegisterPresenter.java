package h.khall.client.model;

import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.AttachEvent.TypeA;
import h.khall.shared.model.Profile;
import h.model.shared.util.StringUtil;
import h.style.g.client.model.Attach;
import h.style.g.client.ui.common.RpcCallback;
import h.style.g.shared.command.RegisterCommand;

public class RegisterPresenter extends AbstractPresenter<RegisterPresenter.Display>
{
  public RegisterPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void register(String inCongNme, String inCongNum, final String inEmail, String inLast,
      String inFirst)
  {
    if (StringUtil.isEmail(inEmail) && !StringUtil.isInValid(inCongNme, inCongNum, inLast, inFirst))
    {
      Profile profile = new Profile();
      profile.setUserId(mDisplay.encrypt(inEmail));
      profile.setLast(mDisplay.encrypt(inLast));
      profile.setFirst(mDisplay.encrypt(inFirst));
      profile.setCongNum(mDisplay.encrypt(inCongNum));

      fire(new RegisterCommand(profile), new RpcCallback<RegisterCommand>()
      {
        @Override
        public void onRpcSuccess(RegisterCommand inCommand)
        {
          mDisplay.notify("Your login information was sent to: " + inEmail);
          login();
        }
      });
    }
    else
    {
      mDisplay.alert("Please enter all required information.");
    }
  }

  public void login()
  {
    fire(new AttachEvent(TypeA.LOGIN));
  }

  public interface Display extends Attach
  {
  }
}