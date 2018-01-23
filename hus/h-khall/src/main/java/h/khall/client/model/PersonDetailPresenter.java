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
    Person person = mClient.gPerson(inEvent.getId());

    mDisplay.setPerson(person);

    mDisplay.setFsg(mClient.getCong().gFsgTitle(person.getFsgId()));

    mDisplay.clear();
    for (Person value : mClient.getPersons().getFamily(inEvent.getId()))
    {
      mDisplay.add(value);
    }

    mDisplay.clearHead();
    for (Person value : mClient.getPersons().gPersons(person.getHead()))
    {
      mDisplay.addHead(value);
    }
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setPerson(Person inPerson);

    void add(Person inPerson);

    void clear();

    void addHead(Person inPerson);

    void clearHead();

    void setFsg(String inFsg);
  }
}