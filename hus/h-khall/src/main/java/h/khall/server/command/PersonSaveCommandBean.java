package h.khall.server.command;

import h.khall.server.dao.Dao;
import h.khall.shared.command.PersonSaveCommand;
import h.model.shared.khall.Person;
import h.model.shared.khall.Profile;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;

public class PersonSaveCommandBean extends AbstractDaoCommandBean<Dao, PersonSaveCommand>
{
  @Override
  public RpcResponse execute(PersonSaveCommand inCommand)
  {
    Profile profile = (Profile) inCommand.getProfile();
    Person person = inCommand.getPerson();
    if (person.isNew())
    {
      person.setCongId(profile.getCongId());
      mDao.insert(profile.gEncrypt(), person);
    }
    else
    {
      mDao.update(profile.gEncrypt(), person);
    }
    return inCommand;
  }
}