package h.khall.shared.command;

import h.model.shared.khall.Profile;
import h.style.g.shared.command.AbstractCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("ProfileSaveCommand")
public class ProfileSaveCommand extends AbstractCommand
{
  private Profile mModel;

  ProfileSaveCommand()
  {
  }

  public ProfileSaveCommand(Profile inModel)
  {
    mModel = inModel;
  }

  public Profile getModel()
  {
    return mModel;
  }
}