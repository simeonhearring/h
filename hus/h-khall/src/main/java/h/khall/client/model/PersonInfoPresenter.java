package h.khall.client.model;

import h.khall.client.ui.event.PersonInfoEvent;
import h.model.shared.khall.Person;

public class PersonInfoPresenter extends AbstractPresenter<PersonInfoPresenter.Display>
  implements PersonInfoEvent.Handler
{
  public PersonInfoPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PersonInfoPresenter handlers()
  {
    register(addHandler(PersonInfoEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(PersonInfoEvent inEvent)
  {
    mDisplay.setPerson(inEvent.getPerson());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setPerson(Person inPerson);
  }
}