package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.command.ForgotCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public class ForgotCommandBean extends AbstractDaoCommandBean<Dao, ForgotCommand>
{
  @Override
  public RpcResponse execute(ForgotCommand inCommand)
  {
    // TODO forgot
    return inCommand;
  }
}