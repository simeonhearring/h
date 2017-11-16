package h.khall.shared.command;

import java.util.List;

import h.model.shared.Tag;
import h.model.shared.khall.Part;
import h.style.g.shared.command.AbstractDataCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("AssignLookupCommand")
public class AssignLookupCommand extends AbstractDataCommand<List<Tag>>
{
  private String mQuery;
  private Part mPart;

  AssignLookupCommand()
  {
  }

  public AssignLookupCommand(String inQuery, Part inPart)
  {
    mQuery = inQuery;
    mPart = inPart;
  }

  public String getQuery()
  {
    return mQuery;
  }

  public Part getPart()
  {
    return mPart;
  }
}