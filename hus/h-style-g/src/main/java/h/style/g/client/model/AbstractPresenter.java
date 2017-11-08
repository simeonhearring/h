package h.style.g.client.model;

import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcResponse;

public abstract class AbstractPresenter<D extends Display>
    extends AbstractDataPresenter<D, RpcResponse>
{
  @Override
  public void go(D inDisplay)
  {
    initDisplay(inDisplay);
  }

  @Override
  public RpcCommand getDataCommand()
  {
    return null;
  }

  @Override
  public void onRpcSuccess(RpcResponse inResult)
  {
    // do nothing
  }
}