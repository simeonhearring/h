package h.khall.client.model;

import h.khall.client.ui.event.PersonInfoEvent;
import h.model.shared.khall.Person;

public class PersonDetailPresenter extends AbstractPresenter<PersonDetailPresenter.Display>
  implements PersonInfoEvent.Handler
{
  public PersonDetailPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PersonDetailPresenter handlers()
  {
    register(addHandler(PersonInfoEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(PersonInfoEvent inEvent)
  {
    mDisplay.setPerson(mClient.gPerson(inEvent.getId()));
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setPerson(Person inPerson);
  }
}