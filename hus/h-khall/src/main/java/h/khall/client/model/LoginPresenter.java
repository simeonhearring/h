package h.khall.client.model;

import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.AttachEvent.TypeA;
import h.khall.client.ui.event.ProfileEvent;
import h.khall.client.ui.event.RegisterEvent;
import h.khall.shared.model.Profile;
import h.style.g.client.model.AbstractPresenter;
import h.style.g.client.model.Attach;
import h.style.g.client.ui.common.RpcCallback;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.command.LoginCommand;

public class LoginPresenter extends AbstractPresenter<LoginPresenter.Display>
{
  public LoginPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void login(String inUserName, String inPassword, String inCongNum, String inEncrypt)
  {
    Profile profile = new Profile();
    profile.setUserId(mDisplay.encrypt(inUserName));
    profile.setPassword(mDisplay.encrypt(inPassword));
    profile.setCongNum(mDisplay.encrypt(inCongNum));
    profile.setEncrypt(mDisplay.encrypt(inEncrypt));

    fire(new LoginCommand(profile), new RpcCallback<LoginCommand>()
    {
      @Override
      public void onRpcSuccess(LoginCommand inCommand)
      {
        fire(new AttachEvent(TypeA.MIDWEEK), new ProfileEvent(inCommand.getData()),
            new RefreshEvent());
      }
    });
  }

  public void forgotPassword()
  {
    fire(new AttachEvent(TypeA.FORGOT));
  }

  public void register()
  {
    fire(new RegisterEvent());
  }

  public interface Display extends Attach
  {
  }
}