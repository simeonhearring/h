package h.style.g.shared.command;

import h.model.shared.Profile;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("RegisterCommand")
public class RegisterCommand extends AbstractCommand
{
  private Profile mProfile;

  RegisterCommand()
  {
  }

  public RegisterCommand(Profile inProfile)
  {
    mProfile = inProfile;
  }

  public Profile getProfile()
  {
    return mProfile;
  }
}