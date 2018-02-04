package h.khall.shared.command;

import h.khall.shared.model.Login;
import h.model.shared.khall.Profile;
import h.style.g.shared.command.AbstractDataCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("SubmitReportEmailCommand")
public class SubmitReportEmailCommand extends AbstractDataCommand<Login>
{
  private Profile mProfile;

  SubmitReportEmailCommand()
  {
  }

  public SubmitReportEmailCommand(Profile inProfile)
  {
    mProfile = inProfile;
  }

  @Override
  public Profile getProfile()
  {
    return mProfile;
  }
}