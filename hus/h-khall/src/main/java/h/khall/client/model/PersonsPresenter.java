package h.khall.client.model;

import static h.model.shared.khall.Persons.filter;

import java.util.List;

import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
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
    Persons persons = mClient.getPersons();
    mDisplay.setAll(persons.getPersons());
    mDisplay.setElders(persons.getElders());
    mDisplay.setRegular(persons.getRegular());
    mDisplay.setServants(persons.getServants());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    void setAll(List<Person> inList);

    void setElders(List<Person> inList);

    void setRegular(List<Person> inList);

    void setServants(List<Person> inList);
  }

  public void search(String inText)
  {
    Persons persons = mClient.getPersons();
    mDisplay.setAll(filter(persons.getPersons(), inText, false));
    mDisplay.setElders(filter(persons.getElders(), inText, true));
    mDisplay.setServants(filter(persons.getServants(), inText, true));
    mDisplay.setRegular(filter(persons.getRegular(), inText, true));
  }
}