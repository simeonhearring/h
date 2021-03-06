package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h.model.shared.Person.Gender;
import h.model.shared.Tag;

@SuppressWarnings("serial")
public class Persons implements Serializable, Comparator<Person>
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

  public void sort()
  {
    Collections.sort(mPersons, this);
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

  public List<Person> getEldersOrServants()
  {
    List<Person> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isElder() || value.isServant())
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public List<Person> getServants()
  {
    List<Person> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isServant())
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public List<Person> getPublishers()
  {
    List<Person> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isPublisher())
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public List<Long> getElderServantIds()
  {
    List<Long> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isElder() || value.isServant())
      {
        ret.add(value.getIdLong());
      }
    }
    return ret;
  }

  public List<Long> getRegularIds()
  {
    List<Long> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isRegular())
      {
        ret.add(value.getIdLong());
      }
    }
    return ret;
  }

  public List<Person> getFamily(Long inId)
  {
    List<Person> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.getHead() != null && value.getHead().equals(inId))
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public List<Long> getPubIds()
  {
    List<Long> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isPublisher())
      {
        ret.add(value.getIdLong());
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

  public List<Person> getFsg(Integer inId)
  {
    List<Person> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isFsg(inId))
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public List<Long> getPubFsgIds(Integer inId)
  {
    List<Long> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isPublisher() && value.isFsg(inId))
      {
        ret.add(value.getIdLong());
      }
    }
    return ret;
  }

  public List<Person> getPubFsg(Integer inId)
  {
    List<Person> ret = new ArrayList<>();
    for (Person value : mPersons)
    {
      if (value.isPublisher() && value.isFsg(inId))
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public static List<Person> filter(List<Person> inList, String inQuery, boolean inMember)
  {
    List<Person> ret = new ArrayList<>();
    inQuery = inQuery.toLowerCase();
    for (Person value : inList)
    {
      if (memberCheck(inMember, value.isMember())
          && value.toQuery().toLowerCase().contains(inQuery))
      {
        ret.add(value);
      }
    }
    return ret;
  }

  private static boolean memberCheck(boolean inInclude, boolean inMember)
  {
    return inInclude ? inMember : true;
  }

  public void filterName(List<Tag> inData, String inName)
  {
    inName = inName.toLowerCase();
    for (Person value : mPersons)
    {
      if (value.isMember() && value.getName().toLowerCase().contains(inName))
      {
        inData.add(value);
      }
    }
  }

  public Person gPerson(Long inId)
  {
    if (inId == null)
    {
      return null;
    }

    return mOrganize.get(inId);
  }

  public String gName(Long inId)
  {
    Person person = gPerson(inId);
    return person != null ? person.getName() : null;
  }

  public Gender gGender(Long inId)
  {
    Person person = gPerson(inId);
    return person != null ? person.getGender() : null;
  }

  public List<Tag> gTags(Long inParticipantId, Long inAssistantId, StudyPoint inStudyPoint)
  {
    List<Tag> ret = new ArrayList<>();
    if (inParticipantId != null)
    {
      ret.add(gPerson(inParticipantId));
      if (inAssistantId != null)
      {
        ret.add(gPerson(inAssistantId));
      }
      if (inStudyPoint != null)
      {
        ret.add(inStudyPoint);
      }
    }
    return ret;
  }

  public List<Person> gAvailable(Part inPart, Long inPersonId, Gender inGender)
  {
    List<Person> ret = new ArrayList<>();
    Gender gender = gGender(inPersonId);
    for (Person value : mPersons)
    {
      if (value.isAvailable(inPart, gender) && value.isGender(inGender))
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public List<Person> gPersons(List<Long> inIds)
  {
    List<Person> ret = new ArrayList<>();
    for (Long value : inIds)
    {
      ret.add(gPerson(value));
    }
    Collections.sort(ret, this);
    return ret;
  }

  @Override
  public int compare(Person inO1, Person inO2)
  {
    String n1 = inO1.gName();
    String n2 = inO2.gName();
    return n1.compareTo(n2);
  }

  public List<Person> gPersons(Long... inId)
  {
    List<Person> ret = new ArrayList<>();
    for (Long value : inId)
    {
      if (value != null)
      {
        ret.add(gPerson(value));
      }
    }
    return ret;
  }
}