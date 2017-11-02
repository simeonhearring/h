package h.style.g.client.service.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public interface RpcServiceAsync
{
  <C extends RpcCommand, R extends RpcResponse> void fire(C inCommand, AsyncCallback<R> inCallback);
}
