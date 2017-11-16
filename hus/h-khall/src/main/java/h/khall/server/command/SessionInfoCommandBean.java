package h.khall.server.command;

import h.model.shared.khall.SessionInfo;
import h.style.g.server.command.AbstractCommandBean;
import h.style.g.shared.command.SessionInfoCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public class SessionInfoCommandBean extends AbstractCommandBean<SessionInfoCommand>
{
  @Override
  public RpcResponse execute(SessionInfoCommand inCommand)
  {
    SessionInfo data = new SessionInfo();
    inCommand.setData(data);
    return inCommand;
  }
}