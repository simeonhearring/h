package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.command.RegisterCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public class RegisterCommandBean extends AbstractDaoCommandBean<Dao, RegisterCommand>
{
  @Override
  public RpcResponse execute(RegisterCommand inCommand)
  {
    // TODO register
    return inCommand;
  }
}