package h.khall.shared.command;

import h.khall.shared.model.Persons;
import h.style.g.shared.command.AbstractDataCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("PersonLookupCommand")
public class PersonLookupCommand extends AbstractDataCommand<Persons>
{
  PersonLookupCommand()
  {
  }
}