package h.khall.shared.model;

import java.util.ArrayList;
import java.util.List;

import h.model.shared.Tag;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Persons;
import h.style.g.shared.chart.Chart;

@SuppressWarnings("serial")
public class Client extends h.model.shared.Client
{
  private Chart mChart;
  private Meeting mMeeting;
  private Persons mPersons;

  public Chart getChart()
  {
    return mChart;
  }

  public void setChart(Chart inChart)
  {
    mChart = inChart;
  }

  public Meeting getMeeting()
  {
    return mMeeting;
  }

  public void setMeeting(Meeting inMeeting)
  {
    mMeeting = inMeeting;
  }

  public Persons getPersons()
  {
    return mPersons;
  }

  public void setPersons(Persons inPersons)
  {
    mPersons = inPersons;
  }

  public List<Tag> getTags(Assignment inAssignment)
  {
    List<Tag> ret = new ArrayList<>();
    if (inAssignment.getParticipantId() != null)
    {
      ret.add(mPersons.get(inAssignment.getParticipantId()));
      if (inAssignment.getAssistantId() != null)
      {
        ret.add(mPersons.get(inAssignment.getAssistantId()));
      }
      if (inAssignment.getStudyPoint() != null)
      {
        ret.add(inAssignment.getStudyPoint());
      }
    }
    return ret;
  }
}