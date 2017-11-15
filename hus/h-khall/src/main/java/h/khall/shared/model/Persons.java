package h.khall.shared.model;

import java.io.Serializable;
import java.util.List;

import h.model.shared.Tag;

@SuppressWarnings("serial")
public class Persons implements Serializable
{
  private List<Person> mPersons;

  public List<Person> getPersons()
  {
    return mPersons;
  }

  public void setPersons(List<Person> inPersons)
  {
    mPersons = inPersons;
  }

  public void filterPerson(List<Tag> inData, String inQuery)
  {
    inQuery = inQuery.toLowerCase();
    for (Person value : mPersons)
    {
      if (value.getName().toLowerCase().contains(inQuery))
      {
        inData.add(value);
      }
    }
  }
}