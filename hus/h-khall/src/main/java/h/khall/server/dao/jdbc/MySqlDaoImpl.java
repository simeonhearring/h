package h.khall.server.dao.jdbc;

import javax.sql.DataSource;

import h.dao.jdbc.statement.PersonEncryptSql;
import h.dao.jdbc.statement.ScheduleSql;
import h.khall.server.dao.Dao;
import h.khall.server.dao.bean.JavaBeanDaoImpl;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;

public class MySqlDaoImpl extends JavaBeanDaoImpl implements Dao
{
  private PersonEncryptSql mPersonSql;
  private ScheduleSql mScheduleSql;

  public void setDataSource(DataSource inDataSource)
  {
    mPersonSql = new PersonEncryptSql(inDataSource);
    mScheduleSql = new ScheduleSql(inDataSource);
  }

  @Override
  public Persons selectPersons(Profile inProfile)
  {
    Persons ret = new Persons();
    ret.setPersons(mPersonSql.selectByCongId(inProfile.getEncrypt(), inProfile.getCongId()));
    return ret;
  }

  @Override
  public Meeting selectMeeting(Profile inProfile)
  {
    Meeting ret = new Meeting();
    ret.setCount(inProfile.getCount());
    ret.setAssignments(mScheduleSql.select(inProfile.getCongId(), inProfile.getYear() - 1));
    ret.setAssignments(mScheduleSql.select(inProfile.getCongId(), inProfile.getYear()));
    return ret;
  }

  @Override
  public void update(Assignment inAssignment)
  {
    mScheduleSql.update(inAssignment);
  }
}