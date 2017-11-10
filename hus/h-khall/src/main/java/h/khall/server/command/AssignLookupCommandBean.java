package h.khall.server.command;

import java.util.ArrayList;
import java.util.List;

import h.khall.server.dao.Dao;
import h.khall.shared.command.AssignLookupCommand;
import h.khall.shared.model.Part;
import h.khall.shared.model.Profile;
import h.khall.shared.model.StudyPoint;
import h.model.shared.Person;
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
    if (startsWith(query))
    {
      filterStudyPoint(data, inCommand.getPart(), query);
    }
    else
    {
      filterPerson(data, query, mDao.selectAssignable((Profile) inCommand.getProfile()));
    }

    return inCommand;
  }

  protected boolean startsWith(String inQuery)
  {
    return startsWith(inQuery, "#", "1", "2", "3", "4", "5", "6", "7", "8", "9");
  }

  private static boolean startsWith(String inQuery, String... inText)
  {
    boolean ret = false;
    for (String value : inText)
    {
      ret |= inQuery.startsWith(value);
    }
    return ret;
  }

  private void filterPerson(List<Tag> inData, String inQuery, List<Person> inPerson)
  {
    inQuery = inQuery.toLowerCase();
    for (Person value : inPerson)
    {
      if (value.getName().toLowerCase().contains(inQuery))
      {
        inData.add(value);
      }
    }
  }

  protected void filterStudyPoint(List<Tag> inData, Part inPart, String inQuery)
  {
    inQuery = inQuery.replaceAll("#", "").toLowerCase();
    for (StudyPoint value : StudyPoint.values())
    {
      if (value.getQueryInfo().contains(inQuery))
      {
        if (inPart == null || value.isValid(inPart))
        {
          inData.add(value);
        }
      }
    }
  }
}