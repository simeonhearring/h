package h.khall.shared.model;

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
}