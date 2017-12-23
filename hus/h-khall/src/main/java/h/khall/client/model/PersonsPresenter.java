package h.khall.client.model;

import static h.model.shared.khall.Persons.filter;

import java.util.List;

import h.model.shared.khall.Person;
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
    mDisplay.setElders(mClient.getPersons().getElders());
    mDisplay.setRegular(mClient.getPersons().getRegular());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setAll(List<Person> inList);

    void setElders(List<Person> inList);

    void setRegular(List<Person> inList);
  }

  public void search(String inText)
  {
    mDisplay.setAll(filter(mClient.getPersons().getPersons(), inText, false));
    mDisplay.setElders(filter(mClient.getPersons().getElders(), inText, true));
    mDisplay.setRegular(filter(mClient.getPersons().getRegular(), inText, true));
  }
}