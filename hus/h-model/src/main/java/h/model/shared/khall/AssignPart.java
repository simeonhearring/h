package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AssignPart implements Serializable
{
  private Part mPart;
  private List<Record> mRecords = new ArrayList<>();

  AssignPart()
  {
  }

  public AssignPart(Part inPart)
  {
    mPart = inPart;
  }

  public Part getPart()
  {
    return mPart;
  }

  public void add(Person inPerson, List<String> inArchive)
  {
    mRecords.add(new Record(inPerson, inArchive));
  }

  public static class Record implements Serializable
  {
    private Person mPerson;
    private List<String> mArchive;

    Record()
    {
    }

    public Record(Person inPerson, List<String> inArchive)
    {
      mPerson = inPerson;
      mArchive = inArchive;
    }

    public Person getPerson()
    {
      return mPerson;
    }

    public void setPerson(Person inPerson)
    {
      mPerson = inPerson;
    }

    public List<String> getArchive()
    {
      return mArchive;
    }

    public void setArchive(List<String> inArchive)
    {
      mArchive = inArchive;
    }
  }
}