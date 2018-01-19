package h.khall.shared.model;

import java.util.Date;
import java.util.List;

import h.model.shared.Person.Gender;
import h.model.shared.Tag;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Congregation;
import h.model.shared.khall.Event;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Part;
import h.model.shared.khall.PartInfo;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Reports;
import h.style.g.shared.chart.Chart;

@SuppressWarnings("serial")
public class Client extends h.model.shared.Client
{
  private Persons mPersons;
  private Meeting mMeeting;
  private Reports mReports;
  private Chart mChart;
  private Congregation mCong;

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

  public Person gPerson(Long inId)
  {
    return mPersons.gPerson(inId);
  }

  public String gName(Long inId)
  {
    return mPersons.gPerson(inId).gName();
  }

  public void setPersons(Persons inPersons)
  {
    mPersons = inPersons;
    mPersons.sort();
  }

  public List<Tag> getTags(Assignment inAssignment)
  {
    return mPersons.gTags(inAssignment.getParticipantId(), inAssignment.getAssistantId(),
        inAssignment.getStudyPoint());
  }

  public PartInfo gPartInfo(Part inPart, Long inParticipantId, Gender inGender)
  {
    PartInfo ret = new PartInfo(inPart, inParticipantId);
    for (Person value : mPersons.gAvailable(inPart, inParticipantId, inGender))
    {
      ret.add(value, mMeeting.gHistory(value.getIdLong()));
    }
    return ret;
  }

  public Reports getReports()
  {
    return mReports;
  }

  public void setReports(Reports inReports)
  {
    mReports = inReports;
  }

  public void setCong(Congregation inCong)
  {
    mCong = inCong;
  }

  public Congregation getCong()
  {
    return mCong;
  }

  public List<Event> gEvents(Date inOf)
  {
    return mCong.gEvents(inOf);
  }
}