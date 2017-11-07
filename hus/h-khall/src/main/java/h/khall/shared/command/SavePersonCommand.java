package h.khall.shared.command;

import h.model.shared.Person;
import h.style.g.shared.command.AbstractCommand;

@SuppressWarnings("serial")
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