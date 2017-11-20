package h.khall.server.dao.jdbc;

import javax.sql.DataSource;

import h.dao.jdbc.statement.ScheduleSql;
import h.khall.server.dao.Dao;
import h.khall.server.dao.bean.JavaBeanDaoImpl;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Profile;

public class MySqlDaoImpl extends JavaBeanDaoImpl implements Dao
{
  private ScheduleSql mScheduleSql;

  public void setDataSource(DataSource inDataSource)
  {
    mScheduleSql = new ScheduleSql(inDataSource);
  }

  @Override
  public Meeting selectMonthly(Profile inProfile)
  {
    Meeting ret = new Meeting();
    ret.setYear(inProfile.getYear());
    ret.setAssignments(mScheduleSql.select(inProfile.getCongId(), inProfile.getYear()));
    return ret;
  }

  @Override
  public void update(Assignment inAssignment)
  {
    mScheduleSql.update(inAssignment);
  }
}