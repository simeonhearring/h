package h.khall.server.dao;

import java.util.List;

import h.khall.shared.model.Meeting;
import h.khall.shared.model.Profile;
import h.model.shared.Person;
import h.style.g.shared.chart.Chart;

public interface Dao
{
  Profile selectProfile(h.model.shared.Profile inProfile);

  Chart selectChart(Profile inProfile);

  Meeting selectMonthly(Profile inProfile);

  List<Person> selectAssignable(Profile inProfile);
}