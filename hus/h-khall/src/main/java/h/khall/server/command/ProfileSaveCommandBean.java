package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.khall.shared.command.ProfileSaveCommand;
import h.model.shared.khall.Profile;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;

public class ProfileSaveCommandBean extends AbstractDaoCommandBean<Dao, ProfileSaveCommand>
{
  @Override
  public RpcResponse execute(ProfileSaveCommand inCommand)
  {
    Profile profile = inCommand.getModel();
    if (profile.gPassword() == null)
    {
      mDao.update(profile);
    }
    else
    {
      String key = ((Profile) inCommand.getProfile()).gEncrypt();
      mDao.update(key, profile);
    }
    return inCommand;
  }
}