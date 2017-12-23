package h.khall.server.dao.bean;

import java.util.Date;

import h.khall.server.dao.Dao;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Assignments.Count;
import h.model.shared.khall.Charts;
import h.model.shared.khall.Congregation;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.model.shared.khall.Report;
import h.model.shared.util.RandomUtil;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.chart.Chart.Stat;

public class JavaBeanDaoImpl implements Dao
{
  private static final Persons PERSONS = RandomPersons.persons();

  @Override
  public Profile selectProfile(Profile inProfile)
  {
    Profile ret = new Profile();
    ret.setUserId(inProfile.getUserId());
    ret.setCongNum(inProfile.getCongNum());
    ret.setEncrypt(inProfile.getEncrypt());
    ret.setYear(2018);

    // TODO authenticate password

    ret.setUserTitle("Owner");
    ret.setUserName("Simeon L Hearring");
    ret.setCongId("58966".equals(inProfile.getCongNum()) ? 59 : 60);
    ret.setCount(Count.STUDENT);

    return ret;
  }

  @Override
  public Congregation selectCong(Profile inProfile)
  {
    Congregation ret = new Congregation();
    ret.setId(inProfile.getCongId());
    ret.setNumber(inProfile.getCongNum());

    if (inProfile.getCongId().equals(59))
    {
      ret.setMidweekOn(new Date(RandomAssignments.THU_JAN_7_2016_1930));
      ret.setName("English - Gallatin, TN");
      ret.setHalls(Hall.MAIN);
    }
    else
    {
      ret.setMidweekOn(new Date(RandomAssignments.TUE_JAN_5_2016_19));
      ret.setName("Triple Creek - Gallatin, TN");
      ret.setHalls(Hall.MAIN);
    }

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
    ret.addAssignments(RandomAssignments.assignments(selectPersons(inProfile).getPersons(), false));
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

  @Override
  public void update(String inKey, Person inPerson)
  {
  }

  @Override
  public void update(Report inReport)
  {
  }
}