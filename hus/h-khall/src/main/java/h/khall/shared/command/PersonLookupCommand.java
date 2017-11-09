package h.khall.shared.command;

import java.util.List;

import h.model.shared.Person;
import h.style.g.shared.command.AbstractDataCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("PersonLookupCommand")
public class PersonLookupCommand extends AbstractDataCommand<List<Person>>
{
  private String mQuery;

  PersonLookupCommand()
  {
  }

  public PersonLookupCommand(String inQuery)
  {
    mQuery = inQuery;
  }

  public String getQuery()
  {
    return mQuery;
  }
}