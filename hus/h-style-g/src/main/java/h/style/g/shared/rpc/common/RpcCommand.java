package h.style.g.shared.rpc.common;

import java.io.Serializable;

public interface RpcCommand extends Serializable
{
  String getUserInfo();

  void setUserInfo(String inInfo);
}