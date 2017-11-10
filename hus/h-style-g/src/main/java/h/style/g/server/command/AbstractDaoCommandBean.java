package h.style.g.server.command;

import h.style.g.shared.rpc.common.RpcCommand;

public abstract class AbstractDaoCommandBean<D, C extends RpcCommand> extends AbstractCommandBean<C>
{
  protected D mDao;

  public void setDao(D inDao)
  {
    mDao = inDao;
  }
}