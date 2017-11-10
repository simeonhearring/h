package h.style.g.shared.rpc.common;

import h.model.shared.Profile;

public interface RpcProfileCommand extends RpcCommand
{
  Profile getProfile();

  void setProfile(Profile inProfile);
}