package h.khall.server.dao.jdbc;

import javax.sql.DataSource;

import h.dao.jdbc.statement.PersonEncryptSql;
import h.dao.jdbc.statement.ReportSql;
import h.dao.jdbc.statement.ScheduleSql;
import h.khall.server.dao.Dao;
import h.khall.server.dao.bean.JavaBeanDaoImpl;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.model.shared.khall.Report;

public class MySqlDaoImpl extends JavaBeanDaoImpl implements Dao
{
  private PersonEncryptSql mPersonSql;
  private ScheduleSql mScheduleSql;
  private ReportSql mReportSql;

  public void setDataSource(DataSource inDataSource)
  {
    mPersonSql = new PersonEncryptSql(inDataSource);
    mScheduleSql = new ScheduleSql(inDataSource);
    mReportSql = new ReportSql(inDataSource);
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
    ret.addAssignments(mScheduleSql.select(inProfile.getCongId(), inProfile.gYears()[1]));
    ret.addAssignments(mScheduleSql.select(inProfile.getCongId(), inProfile.gYears()[0]));
    return ret;
  }

  @Override
  public void update(String inKey, Person inPerson)
  {
    mPersonSql.update(inKey, inPerson);
  }

  @Override
  public void update(Assignment inAssignment)
  {
    mScheduleSql.update(inAssignment);
  }

  @Override
  public void update(Report inReport)
  {
    mReportSql.upsert(inReport);
  }
}