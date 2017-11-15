package h.khall.client.model;

import java.util.List;

import h.khall.shared.model.Person;
import h.style.g.client.ui.event.RefreshEvent;

public class PersonsPresenter extends AbstractPresenter<PersonsPresenter.Display>
  implements RefreshEvent.Handler
{
  public PersonsPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
  }

  public PersonsPresenter handlers()
  {
    addHandler(RefreshEvent.TYPE, this);
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
    mDisplay.setAll(mClient.getPersons().getPersons());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setAll(List<Person> inList);
  }
}