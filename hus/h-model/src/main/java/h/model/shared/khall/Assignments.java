package h.model.shared.khall;

import static h.model.shared.util.ListUtil.subReverse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Assignments implements Serializable
{
  public enum Count
  {
    ALL,
    STUDENT,
    NONSTUDENT;
  }

  private List<Assignment> mAssignments;

  Assignments()
  {
  }

  public Assignments(List<Assignment> inAssignments)
  {
    mAssignments = inAssignments;
  }

  public double gCount(Count inCount)
  {
    double ret = 0;
    switch (inCount)
    {
      case ALL:
        ret = gCount();
        break;
      case STUDENT:
        ret = gStudentCount();
        break;
      case NONSTUDENT:
        ret = gNStudentCount();
        break;
      default:
        break;
    }
    return ret;
  }

  public int gAssigned(Count inCount)
  {
    int ret = 0;
    switch (inCount)
    {
      case ALL:
        ret = gAssigned();
        break;
      case STUDENT:
        ret = gStudentAssigned();
        break;
      case NONSTUDENT:
        ret = gNStudentAssigned();
        break;
      default:
        break;
    }
    return ret;
  }

  public double gCount()
  {
    return mAssignments.size();
  }

  public List<Assignment> gAssignments()
  {
    return mAssignments;
  }

  public List<Assignment> gHistory(Persons inPersons, Long inId, int inLast)
  {
    List<Assignment> ret = new ArrayList<>();
    for (Assignment value : subReverse(gAssignedTo(inId), inLast))
    {
      ret.add(value);
    }
    return ret;
  }

  public List<Assignment> gAssignedTo(Long inId)
  {
    List<Assignment> ret = new ArrayList<>();
    for (Assignment value : mAssignments)
    {
      if (value.isAssignedTo(inId))
      {
        ret.add(value);
      }
    }
    return ret;
  }

  public double gStudentCount()
  {
    int ret = 0;
    for (Assignment value : mAssignments)
    {
      if (value.isStudent())
      {
        ret++;
      }
    }
    return ret;
  }

  public double gNStudentCount()
  {
    int ret = 0;
    for (Assignment value : mAssignments)
    {
      if (!value.isStudent())
      {
        ret++;
      }
    }
    return ret;
  }

  public int gAssigned()
  {
    int ret = 0;
    for (Assignment value : mAssignments)
    {
      if (value.isAssigned())
      {
        ret++;
      }
    }
    return ret;
  }

  public int gStudentAssigned()
  {
    int ret = 0;
    for (Assignment value : mAssignments)
    {
      if (value.isStudent() && value.isAssigned())
      {
        ret++;
      }
    }
    return ret;
  }

  public int gNStudentAssigned()
  {
    int ret = 0;
    for (Assignment value : mAssignments)
    {
      if (!value.isStudent() && value.isAssigned())
      {
        ret++;
      }
    }
    return ret;
  }

  public void add(Assignment inValue)
  {
    mAssignments.add(inValue);
  }

  public Assignment gAssignment(Part inPpart, Hall inHall)
  {
    Assignment ret = null;
    for (Assignment value : mAssignments)
    {
      if (inPpart.equals(value.getCurriculum().getPpart()) && inHall.equals(value.getHall()))
      {
        ret = value;
        break;
      }
    }
    return ret;
  }

}