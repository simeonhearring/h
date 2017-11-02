package h.style.g.shared.command;

import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcResponse;

@SuppressWarnings("serial")
public abstract class AbstractCommand implements RpcCommand, RpcResponse
{
  private String mUserInfo;

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