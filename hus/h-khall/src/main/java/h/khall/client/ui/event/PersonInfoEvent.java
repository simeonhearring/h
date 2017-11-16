package h.khall.client.ui.event;

import h.model.shared.khall.Person;
import h.style.g.client.ui.event.Event;
import h.style.g.client.ui.event.EventHandler;
import h.style.g.client.ui.event.TypeH;

public class PersonInfoEvent extends Event<PersonInfoEvent.Handler>
{
  public static final TypeH<Handler> TYPE = new TypeH<>(PersonInfoEvent.class);

  public interface Handler extends EventHandler
  {
    void dispatch(PersonInfoEvent inEvent);
  }

  private Person mPerson;

  public PersonInfoEvent(Person inPerson)
  {
    mPerson = inPerson;
  }

  public Person getPerson()
  {
    return mPerson;
  }

  @Override
  public Type<Handler> getAssociatedType()
  {
    return TYPE;
  }

  @Override
  protected void dispatch(Handler inHandler)
  {
    debug(inHandler);
    inHandler.dispatch(this);
  }
}