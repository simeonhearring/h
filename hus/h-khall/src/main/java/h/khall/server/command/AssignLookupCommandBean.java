package h.khall.server.command;

import java.util.ArrayList;
import java.util.List;

import h.khall.server.dao.Dao;
import h.khall.shared.command.AssignLookupCommand;
import h.khall.shared.model.Profile;
import h.khall.shared.model.StudyPoint;
import h.model.shared.Tag;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;

public class AssignLookupCommandBean extends AbstractDaoCommandBean<Dao, AssignLookupCommand>
{
  @Override
  public RpcResponse execute(AssignLookupCommand inCommand)
  {
    List<Tag> data = new ArrayList<>();
    inCommand.setData(data);

    String query = inCommand.getQuery();
    if (StudyPoint.doFilter(query))
    {
      StudyPoint.filter(data, inCommand.getPart(), query);
    }
    else
    {
      mDao.selectPersons((Profile) inCommand.getProfile()).filterPerson(data, query);
    }

    return inCommand;
  }
}