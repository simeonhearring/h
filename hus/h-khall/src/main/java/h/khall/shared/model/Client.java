package h.khall.shared.model;

import java.util.List;

import h.model.shared.Person.Gender;
import h.model.shared.Tag;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Part;
import h.model.shared.khall.PartInfo;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.style.g.shared.chart.Chart;

@SuppressWarnings("serial")
public class Client extends h.model.shared.Client
{
  private Persons mPersons;
  private Meeting mMeeting;
  private Chart mChart;

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
    return mPersons.gTags(inAssignment.getParticipantId(), inAssignment.getAssistantId(),
        inAssignment.getStudyPoint());
  }

  public PartInfo gPartInfo(Part inPart, Long inParticipantId, Gender inGender)
  {
    PartInfo ret = new PartInfo(inPart);
    for (Person value : mPersons.gAvailable(inPart, inParticipantId, inGender))
    {
      ret.add(value, mMeeting.gHistory(value.getIdLong()));
    }
    return ret;
  }
}