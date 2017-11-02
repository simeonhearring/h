package h.style.g.client.service.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcResponse;

@RemoteServiceRelativePath("rpcservlet")
public interface RpcService extends RemoteService
{
  RpcResponse fire(RpcCommand inCommand);
}