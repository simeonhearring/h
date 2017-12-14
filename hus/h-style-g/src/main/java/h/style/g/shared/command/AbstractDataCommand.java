package h.style.g.shared.command;

import h.style.g.shared.rpc.common.RpcProfileCommand;

@SuppressWarnings("serial")
public abstract class AbstractDataCommand<T> extends AbstractCommand implements RpcProfileCommand
{
  private T mData;

  public void setData(T inData)
  {
    mData = inData;
  }

  public T getData()
  {
    return mData;
  }
}
