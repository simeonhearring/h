package h.style.g.shared.command;

import h.model.shared.Profile;
import h.style.g.shared.rpc.common.RpcProfileCommand;

@SuppressWarnings("serial")
public abstract class AbstractDataCommand<T> extends AbstractCommand implements RpcProfileCommand
{
  private T mData;

  private Profile mProfile;

  @Override
  public Profile getProfile()
  {
    return mProfile;
  }

  @Override
  public void setProfile(Profile inProfile)
  {
    mProfile = inProfile;
  }

  public void setData(T inData)
  {
    mData = inData;
  }

  public T getData()
  {
    return mData;
  }
}
