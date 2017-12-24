package h.khall.server.command;

import java.util.ArrayList;
import java.util.List;

import h.khall.server.dao.Dao;
import h.khall.shared.command.PersonLookupCommand;
import h.model.shared.Tag;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;

public class PersonLookupCommandBean extends AbstractDaoCommandBean<Dao, PersonLookupCommand>
{
  @Override
  public RpcResponse execute(PersonLookupCommand inCommand)
  {
    Persons persons = mDao.selectPersons((Profile) inCommand.getProfile());
    inCommand.setData(filter(inCommand.getQuery(), persons));
    return inCommand;
  }

  private List<Tag> filter(String inQuery, Persons inPersons)
  {
    List<Tag> ret = new ArrayList<>();
    inPersons.filterName(ret, inQuery);
    return ret;
  }
}