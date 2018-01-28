package h.style.g.shared.command;

import h.model.shared.khall.Profile;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("ForgotCommand")
public class ForgotCommand extends AbstractCommand
{
  private Profile mProfile;

  ForgotCommand()
  {
  }

  public ForgotCommand(Profile inProfile)
  {
    mProfile = inProfile;
  }

  @Override
  public Profile getProfile()
  {
    return mProfile;
  }
}