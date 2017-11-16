package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.khall.shared.command.PersonLookupCommand;
import h.model.shared.khall.Profile;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;

public class PersonLookupCommandBean extends AbstractDaoCommandBean<Dao, PersonLookupCommand>
{
  @Override
  public RpcResponse execute(PersonLookupCommand inCommand)
  {
    inCommand.setData(mDao.selectPersons((Profile) inCommand.getProfile()));
    return inCommand;
  }
}