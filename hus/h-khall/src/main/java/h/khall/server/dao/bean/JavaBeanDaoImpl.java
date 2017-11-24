package h.khall.server.dao.bean;

import h.khall.server.dao.Dao;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Assignments.Count;
import h.model.shared.khall.Charts;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.model.shared.util.RandomUtil;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.chart.Chart.Stat;

public class JavaBeanDaoImpl implements Dao
{
  private static final Persons PERSONS = RandomPersons.persons();

  @Override
  public Profile selectProfile(h.model.shared.Profile inProfile)
  {
    Profile ret = new Profile();
    ret.setUserId("simeonlhearring@gmail.com");
    ret.setUserTitle("Owner");
    ret.setUserName("Simeon L Hearring");
    ret.setCongId(59);
    ret.setYear(2018);
    ret.setEncrypt("9M1})6Y]ibnxrp^zSQz@*BAc[Cn+Ub1R");
    ret.setCount(Count.STUDENT);
    return ret;
  }

  @Override
  public Chart selectChart(Profile inProfile)
  {
    return chart();
  }

  @Override
  public Meeting selectMeeting(Profile inProfile)
  {
    Meeting ret = new Meeting();
    ret.setAssignments(RandomAssignments.assigns(selectPersons(inProfile).getPersons()));
    return ret;
  }

  @Override
  public Persons selectPersons(Profile inProfile)
  {
    return PERSONS;
  }

  private static Chart chart()
  {
    Chart ret = new Chart(Chart.Type.LINE);

    ret.setDataType(Charts.FSG);

    ret.setResponsive(true);

    Stat stat = new Stat();
    stat.setHead("$ 50,992");
    stat.setSubHead("Half-year revenue margin");
    stat.setTopRight("<small>Average value of sales in the past month in:<strong>United states</strong></small><br />All sales: 162,862");
    stat.setFooter("<strong>Analysis of sales:</strong>The value has been changed over time, and last month reached a level over $50,000.");
    ret.setStat(stat);

    String[] months =
    {
        "January", "February", "March", "April", "May", "June", "July"
    };
    ret.addLabel(months);

    ret.format(ret.createDataset("Hearring", random(months.length)));
    ret.format(ret.createDataset("Humphrey", random(months.length)));
    ret.format(ret.createDataset("Tunstill", random(months.length)));
    ret.format(ret.createDataset("Kerstner", random(months.length)));
    return ret;
  }

  private static Double[] random(int inLength)
  {
    Double[] ret = new Double[inLength];
    for (int i = 0; i < ret.length; i++)
    {
      ret[i] = (double) RandomUtil.randomInt(100);
    }
    return ret;
  }

  @Override
  public void update(Assignment inAssignment)
  {
  }
}