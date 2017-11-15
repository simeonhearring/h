package h.khall.server.dao;

import h.khall.shared.model.Meeting;
import h.khall.shared.model.Persons;
import h.khall.shared.model.Profile;
import h.style.g.shared.chart.Chart;

public interface Dao
{
  Profile selectProfile(h.model.shared.Profile inProfile);

  Chart selectChart(Profile inProfile);

  Meeting selectMonthly(Profile inProfile);

  Persons selectPersons(Profile inProfile);
}