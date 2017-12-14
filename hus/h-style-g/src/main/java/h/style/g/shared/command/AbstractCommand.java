package h.style.g.shared.command;

import h.model.shared.Profile;
import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcProfileCommand;
import h.style.g.shared.rpc.common.RpcResponse;

@SuppressWarnings("serial")
public abstract class AbstractCommand implements RpcCommand, RpcResponse, RpcProfileCommand
{
  private String mUserInfo;

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

  @Override
  public String getUserInfo()
  {
    return mUserInfo;
  }

  @Override
  public void setUserInfo(String inUserInfo)
  {
    mUserInfo = inUserInfo;
  }
}