package h.style.g.server.command;

import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public interface CommandBean<C extends RpcCommand>
{
  RpcResponse execute(C inCommand);
}
