package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.khall.shared.command.ReportSaveCommand;
import h.model.shared.khall.Report;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;

public class ReportSaveCommandBean extends AbstractDaoCommandBean<Dao, ReportSaveCommand>
{
  @Override
  public RpcResponse execute(ReportSaveCommand inCommand)
  {
    for (Report value : inCommand.getReports())
    {
      mDao.update(value);
    }
    return inCommand;
  }
}