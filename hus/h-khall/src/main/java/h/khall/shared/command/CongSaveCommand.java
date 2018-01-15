package h.khall.shared.command;

import h.model.shared.khall.Congregation;
import h.style.g.shared.command.AbstractCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("CongSaveCommand")
public class CongSaveCommand extends AbstractCommand
{
  private Congregation mCong;

  CongSaveCommand()
  {
  }

  public CongSaveCommand(Congregation inCong)
  {
    mCong = inCong;
  }

  public Congregation getCong()
  {
    return mCong;
  }
}