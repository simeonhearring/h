package h.style.g.shared.command;

import h.model.shared.Profile;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("LoginCommand")
public class LoginCommand extends AbstractDataCommand<Profile>
{
  private Profile mProfile;

  LoginCommand()
  {
  }

  public LoginCommand(Profile inProfile)
  {
    mProfile = inProfile;
  }

  public Profile getProfile()
  {
    return mProfile;
  }
}