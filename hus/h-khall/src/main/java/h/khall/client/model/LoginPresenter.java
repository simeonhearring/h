package h.khall.client.model;

import h.khall.client.ui.event.AttachEvent;
import h.khall.client.ui.event.AttachEvent.TypeA;
import h.khall.client.ui.event.ClientEvent;
import h.khall.client.ui.event.ProfileEvent;
import h.khall.shared.command.LoginCommand;
import h.khall.shared.model.Login;
import h.model.shared.khall.Profile;
import h.model.shared.util.StringUtil;
import h.style.g.client.model.Attach;
import h.style.g.client.ui.common.RpcCallback;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.client.ui.util.StorageUtil;

public class LoginPresenter extends AbstractPresenter<LoginPresenter.Display>
{
  public LoginPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public void login(String inUserName, String inPassword, String inCongNum, String inEncrypt)
  {
    if (StringUtil.isEmail(inUserName) && !StringUtil.isInValid(inPassword, inCongNum, inEncrypt))
    {
      Profile profile = new Profile();
      profile.setUserId(mDisplay.encrypt(inUserName));
      profile.setPassword(mDisplay.encrypt(inPassword));
      profile.setCongNum(mDisplay.encrypt(inCongNum));
      profile.setEncrypt(mDisplay.encrypt(inEncrypt));

      StorageUtil.addEncryptKey(inCongNum, inEncrypt);
      mDisplay.addSessionValue("congEnc", inEncrypt);

      fire(new LoginCommand(profile), new LoginCallback());
    }
  }

  public void forgotPassword()
  {
    fire(new AttachEvent(TypeA.FORGOT));
  }

  public void register()
  {
    fire(new AttachEvent(TypeA.REGISTER));
  }

  public interface Display extends Attach
  {
  }

  private class LoginCallback extends RpcCallback<LoginCommand>
  {
    @Override
    public void onRpcSuccess(LoginCommand inCommand)
    {
      Login login = inCommand.getData();
      if (login.isAuthenticated())
      {
        fire(new AttachEvent(TypeA.MIDWEEK), new ProfileEvent(login.getProfile()),
            new ClientEvent(login.getClient()), new RefreshEvent());
      }
      else
      {
        mDisplay.alert(
            "That didn't work! Did you type your password correctly? Is the encryption key correct?");
      }
    }
  }
}