package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.khall.shared.command.AssignmentSaveCommand;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;

public class AssignmentSaveCommandBean extends AbstractDaoCommandBean<Dao, AssignmentSaveCommand>
{
  @Override
  public RpcResponse execute(AssignmentSaveCommand inCommand)
  {
    // TODO save assignment
    return inCommand;
  }
}