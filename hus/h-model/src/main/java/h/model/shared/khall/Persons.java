package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h.model.shared.Tag;

@SuppressWarnings("serial")
public class Persons implements Serializable
{
  private List<Person> mPersons;
  private Map<Long, Person> mOrganize;

  public List<Person> getPersons()
  {
    return mPersons;
  }

  public void setPersons(List<Person> inPersons)
  {
    mPersons = inPersons;
    organize();
  }

  private void organize()
  {
    mOrganize = new HashMap<>();
    for (Person value : mPersons)
    {
      mOrganize.put(value.getIdLong(), value);
    }
  }

  public List<Person> getElders()
  {
    List<Person> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isElder())
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public List<Person> getRegular()
  {
    List<Person> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isRegular())
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public static List<Person> filter(List<Person> inList, String inQuery)
  {
    List<Person> ret = new ArrayList<>();
    inQuery = inQuery.toLowerCase();
    for (Person value : inList)
    {
      if (value.toQuery().toLowerCase().contains(inQuery))
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public void filterName(List<Tag> inData, String inName)
  {
    inName = inName.toLowerCase();
    for (Person value : mPersons)
    {
      if (value.getName().toLowerCase().contains(inName))
      {
        inData.add(value);
      }
    }
  }

  public Person get(Long inId)
  {
    return mOrganize.get(inId);
  }
}