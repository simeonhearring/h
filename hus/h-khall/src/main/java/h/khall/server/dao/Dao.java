package h.khall.server.dao;

import java.util.Date;

import h.model.shared.khall.Assignment;
import h.model.shared.khall.Congregation;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.model.shared.khall.Report;
import h.model.shared.khall.Reports;
import h.style.g.shared.chart.Chart;

public interface Dao
{
  Profile selectProfile(Profile inProfile);

  Chart selectChart(Profile inProfile);

  Meeting selectMeeting(Profile inProfile);

  Persons selectPersons(Profile inProfile);

  Persons selectPersons(String inEncrypt, Integer inCongId);

  Person selectPerson(String inEncrypt, long inId);

  void update(String inKey, Person inPerson);

  void update(Assignment inAssignment);

  void update(Report inReport);

  void update(Congregation inCong);

  Congregation selectCong(Profile inProfile);

  Reports selectReports(Profile inProfile);

  Reports selectReports(Integer inCongId, Date inStart, Date inEnd);

  Reports selectReports(Integer inCongId, int inPastMonths);
}