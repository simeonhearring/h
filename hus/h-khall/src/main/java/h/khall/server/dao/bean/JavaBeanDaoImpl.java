package h.khall.server.dao.bean;

import h.khall.server.dao.Dao;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Charts;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.style.g.shared.chart.Chart;
import h.tool.util.RandomUtil;

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
    ret.setCongId(1);
    ret.setYear(2018);
    return ret;
  }

  @Override
  public Chart selectChart(Profile inProfile)
  {
    return chart();
  }

  @Override
  public Meeting selectMonthly(Profile inProfile)
  {
    Meeting ret = new Meeting();
    ret.setYear(inProfile.getYear());
    ret.setAssignments(RandomAssignments.assigns(PERSONS.getPersons()));
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

    String[] months =
    {
        "January", "February", "March", "April", "May", "June", "July"
    };
    ret.addLabel(months);

    dataset(ret.createDataset("Hearring"), random(months.length));
    dataset(ret.createDataset("Humphrey"), random(months.length));
    dataset(ret.createDataset("Tunstill"), random(months.length));
    dataset(ret.createDataset("Kerstner"), random(months.length));

    return ret;
  }

  private static void dataset(Chart.Dataset inSet, double[] inData)
  {
    String c1 = "hsla(" + RandomUtil.randomInt(360) + ",90%,90%,";
    inSet.setBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setBorderColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setPointBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setPointBorderColor("#fff");
    inSet.addData(inData);
  }

  private static double[] random(int inLength)
  {
    double[] ret = new double[inLength];
    for (int i = 0; i < ret.length; i++)
    {
      ret[i] = RandomUtil.randomInt(100);
    }
    return ret;
  }

  @Override
  public void update(Assignment inAssignment)
  {
  }
}