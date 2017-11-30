package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import h.model.shared.Person.Gender;

@SuppressWarnings("serial")
public class PartInfo implements Serializable, Comparator<PartInfo.Info>
{
  private Part mPart;
  private List<Info> mInfo = new ArrayList<>();

  PartInfo()
  {
  }

  public PartInfo(Part inPart)
  {
    mPart = inPart;
  }

  public Part getPart()
  {
    return mPart;
  }

  public List<Info> getInfo()
  {
    return mInfo;
  }

  public void add(Person inPerson, List<Assignment> inArchive)
  {
    mInfo.add(new Info(inPerson, inArchive));
  }

  public void sort()
  {
    Collections.sort(mInfo, this);
  }

  public static class Info implements Serializable
  {
    private Person mPerson;
    private List<Assignment> mArchive;

    Info()
    {
    }

    public Info(Person inPerson, List<Assignment> inArchive)
    {
      mPerson = inPerson;
      mArchive = inArchive;
    }

    public Person getPerson()
    {
      return mPerson;
    }

    public List<String> getArchive(Persons inPersons)
    {
      return Assignment.getArchive(inPersons, mArchive, mPerson.getIdLong());
    }

    public Date getLast()
    {
      return mArchive.size() > 0 ? mArchive.get(0).getDate() : null;
    }
  }

  @Override
  public int compare(PartInfo.Info inO1, PartInfo.Info inO2)
  {
    Date d1 = inO1.getLast();
    Date d2 = inO2.getLast();

    d1 = d1 == null ? new Date(1L) : d1;
    d2 = d2 == null ? new Date(1L) : d2;

    int ret = d1.compareTo(d2);

    if (ret == 0)
    {
      Gender g1 = inO1.getPerson().getGender();
      Gender g2 = inO2.getPerson().getGender();
      ret = g1.compareTo(g2);
    }

    if (ret == 0)
    {
      String n1 = inO1.getPerson().getName();
      String n2 = inO2.getPerson().getName();
      ret = n1.compareTo(n2);
    }

    return ret;
  }
}