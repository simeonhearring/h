package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.khall.shared.command.CongSaveCommand;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;

public class CongSaveCommandBean extends AbstractDaoCommandBean<Dao, CongSaveCommand>
{
  @Override
  public RpcResponse execute(CongSaveCommand inCommand)
  {
    mDao.upsert(inCommand.getCong());
    return inCommand;
  }
}