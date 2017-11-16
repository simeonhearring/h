package h.khall.server.dao;

import h.model.shared.khall.Meeting;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.style.g.shared.chart.Chart;

public interface Dao
{
  Profile selectProfile(h.model.shared.Profile inProfile);

  Chart selectChart(Profile inProfile);

  Meeting selectMonthly(Profile inProfile);

  Persons selectPersons(Profile inProfile);
}