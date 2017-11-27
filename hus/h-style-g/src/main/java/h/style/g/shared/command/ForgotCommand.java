package h.style.g.shared.command;

import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("ForgotCommand")
public class ForgotCommand extends AbstractCommand
{
  private String mEmail;

  ForgotCommand()
  {
  }

  public ForgotCommand(String inEmail)
  {
    mEmail = inEmail;
  }

  public String getEmail()
  {
    return mEmail;
  }
}