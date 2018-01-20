package h.khall.client.model;

import h.khall.client.ui.event.PersonInfoEvent;
import h.model.shared.khall.Person;

public class PersonTablePresenter extends AbstractPresenter<PersonTablePresenter.Display>
  implements PersonInfoEvent.Handler
{
  public PersonTablePresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PersonTablePresenter handlers()
  {
    register(addHandler(PersonInfoEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(PersonInfoEvent inEvent)
  {
    update(inEvent.getId());
  }

  private void update(Long inId)
  {
    mDisplay.update(mClient.gPerson(inId));
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void update(Person inPerson);
  }
}