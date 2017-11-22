package h.model.shared.khall;

import static h.model.shared.util.ListUtil.subReverse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Assignments implements Serializable
{
  private List<Assignment> mAssignments;

  public Assignments()
  {
  }

  public Assignments(List<Assignment> inAssignments)
  {
    mAssignments = inAssignments;
  }

  public List<String> gHistory(Persons inPersons, Long inId, int inLast)
  {
    List<String> ret = new ArrayList<>();
    for (Assignment value : subReverse(gAssignedTo(inId), inLast))
    {
      ret.add(value.gHistoryLine(inPersons, inId));
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
}