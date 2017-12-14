package h.khall.server.dao;

import h.model.shared.khall.Assignment;
import h.model.shared.khall.Congregation;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.style.g.shared.chart.Chart;

public interface Dao
{
  Profile selectProfile(Profile inProfile);

  Chart selectChart(Profile inProfile);

  Meeting selectMeeting(Profile inProfile);

  Persons selectPersons(Profile inProfile);

  void update(String inKey, Person inPerson);

  void update(Assignment inAssignment);

  Congregation selectCong(Profile inProfile);
}