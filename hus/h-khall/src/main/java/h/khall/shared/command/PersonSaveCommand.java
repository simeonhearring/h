package h.khall.shared.command;

import h.model.shared.khall.Person;
import h.style.g.shared.command.AbstractCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("PersonSaveCommand")
public class PersonSaveCommand extends AbstractCommand
{
  private Person mPerson;

  PersonSaveCommand()
  {
  }

  public PersonSaveCommand(Person inPerson)
  {
    mPerson = inPerson;
  }

  public Person getPerson()
  {
    return mPerson;
  }
}