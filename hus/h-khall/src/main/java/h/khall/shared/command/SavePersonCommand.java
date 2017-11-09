package h.khall.shared.command;

import h.model.shared.Person;
import h.style.g.shared.command.AbstractCommand;
import h.style.g.shared.rpc.CommandName;

@SuppressWarnings("serial")
@CommandName("SavePersonCommand")
public class SavePersonCommand extends AbstractCommand
{
  private Person mPerson;

  SavePersonCommand()
  {
  }

  public SavePersonCommand(Person inPerson)
  {
    mPerson = inPerson;
  }

  public Person getPerson()
  {
    return mPerson;
  }
}