package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.khall.Profile;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;

public class PersonSaveCommandBean extends AbstractDaoCommandBean<Dao, PersonSaveCommand>
{
  @Override
  public RpcResponse execute(PersonSaveCommand inCommand)
  {
    String key = ((Profile) inCommand.getProfile()).gEncrypt();
    mDao.update(key, inCommand.getPerson());
    return inCommand;
  }
}