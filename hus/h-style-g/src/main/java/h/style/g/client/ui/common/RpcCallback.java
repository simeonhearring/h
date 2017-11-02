package h.style.g.client.ui.common;

import com.google.gwt.user.client.rpc.AsyncCallback;

import h.style.g.client.ui.event.AlertEvent;
import h.style.g.shared.command.LoggerCommand;
import h.style.g.shared.command.LoggerCommand.Level;
import h.style.g.shared.rpc.common.NotifyResponse;

public abstract class RpcCallback<T> implements AsyncCallback<T>
{
  public abstract void onRpcSuccess(T inResult);

  public void onNotify(NotifyResponse inResponse)
  {
    Global.getInstance().fire(new AlertEvent(inResponse.getNotifyMessage()));
  }

  @Override
  public void onFailure(Throwable inCaught)
  {
    LoggerCommand command = new LoggerCommand(Level.ERROR, "RPC FAILURE", inCaught);
    Global.getInstance().fire(command, command);
  }

  @Override
  public void onSuccess(T inResult)
  {
    if (inResult instanceof NotifyResponse)
    {
      onNotify((NotifyResponse) inResult);
    }
    else
    {
      onRpcSuccess(inResult);
    }
  }
}