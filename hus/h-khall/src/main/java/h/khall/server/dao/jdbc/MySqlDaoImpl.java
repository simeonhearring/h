package h.khall.server.dao.jdbc;

import java.util.Date;

import javax.sql.DataSource;

import h.dao.jdbc.statement.CongSql;
import h.dao.jdbc.statement.PersonEncryptSql;
import h.dao.jdbc.statement.ProfileEncryptSql;
import h.dao.jdbc.statement.ReportSql;
import h.dao.jdbc.statement.ScheduleSql;
import h.khall.server.dao.Dao;
import h.khall.server.dao.bean.JavaBeanDaoImpl;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Congregation;
import h.model.shared.khall.Meeting;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.model.shared.khall.Report;
import h.model.shared.khall.Reports;

public class MySqlDaoImpl extends JavaBeanDaoImpl implements Dao
{
  private ProfileEncryptSql mProfileSql;
  private PersonEncryptSql mPersonSql;
  private ScheduleSql mScheduleSql;
  private ReportSql mReportSql;
  private CongSql mCongSql;

  public void setDataSource(DataSource inDataSource)
  {
    mProfileSql = new ProfileEncryptSql(inDataSource);
    mPersonSql = new PersonEncryptSql(inDataSource);
    mScheduleSql = new ScheduleSql(inDataSource);
    mCongSql = new CongSql(inDataSource);
  }

  public void setReportDataSource(DataSource inDataSource)
  {
    mReportSql = new ReportSql(inDataSource);
  }

  @Override
  public Profile selectProfile(Profile inProfile)
  {
    return mProfileSql.select(inProfile.gEncrypt(), inProfile.gLocator());
  }

  @Override
  public void update(Profile inProfile)
  {
    mProfileSql.upsert(inProfile);
  }

  @Override
  public void update(String inKey, Profile inProfile)
  {
    mProfileSql.upsert(inKey, inProfile);
  }

  @Override
  public Person selectPerson(String inEncrypt, long inId)
  {
    return mPersonSql.selectById(inEncrypt, inId);
  }

  @Override
  public Persons selectPersons(Profile inProfile)
  {
    return selectPersons(inProfile.gEncrypt(), inProfile.getCongId());
  }

  @Override
  public Persons selectPersons(String inEncrypt, Integer inCongId)
  {
    Persons ret = new Persons();
    ret.setPersons(mPersonSql.selectByCongId(inEncrypt, inCongId));
    return ret;
  }

  @Override
  public Reports selectReports(Profile inProfile)
  {
    Reports ret = new Reports();
    ret.addReports(mReportSql.select(inProfile.getCongId()));
    return ret;
  }

  @Override
  public Reports selectReports(Integer inCongId, int inPastMonths)
  {
    Reports ret = new Reports();
    ret.addReports(mReportSql.select(inCongId, inPastMonths));
    return ret;
  }

  @Override
  public Reports selectReports(Integer inCongId, Date inStart, Date inEnd)
  {
    Reports ret = new Reports();
    ret.addReports(mReportSql.select(inCongId, inStart, inEnd));
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
  public Congregation selectCong(Profile inProfile)
  {
    return mCongSql.selectById(inProfile.getCongId());
  }

  @Override
  public void update(Congregation inCong)
  {
    mCongSql.upsert(inCong);
  }

  @Override
  public void update(Report inReport)
  {
    mReportSql.upsert(inReport);
  }
}