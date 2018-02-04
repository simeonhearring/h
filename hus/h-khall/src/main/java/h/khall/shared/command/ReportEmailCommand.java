package h.khall.shared.command;

import java.util.List;

import h.style.g.shared.command.AbstractCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("ReportEmailCommand")
public class ReportEmailCommand extends AbstractCommand
{
  private int mCongId;
  private List<Long> mIds;

  ReportEmailCommand()
  {
  }

  public ReportEmailCommand(int inCongId, List<Long> inIds)
  {
    mCongId = inCongId;
    mIds = inIds;
  }

  public int getCongId()
  {
    return mCongId;
  }

  public List<Long> getIds()
  {
    return mIds;
  }
}