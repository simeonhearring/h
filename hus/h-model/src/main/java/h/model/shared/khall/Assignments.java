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
    NON_STUDENT;
  }

  private List<Assignment> mAssignments = new ArrayList<>();

  Assignments()
  {
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
      case NON_STUDENT:
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
      case NON_STUDENT:
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

  public List<Assignment> gHistory(long inPersonId, int inMax)
  {
    List<Assignment> ret = new ArrayList<>();
    for (Assignment value : subReverse(gAssignedTo(inPersonId), inMax))
    {
      ret.add(value);
    }
    return ret;
  }

  public List<Assignment> gAssignedTo(long inPersonId)
  {
    List<Assignment> ret = new ArrayList<>();
    for (Assignment value : mAssignments)
    {
      if (value.isAssignedTo(inPersonId))
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