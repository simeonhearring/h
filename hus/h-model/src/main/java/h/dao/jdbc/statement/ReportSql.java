package h.dao.jdbc.statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.object.SqlUpdate;

import h.dao.jdbc.AbstractSql;
import h.model.shared.khall.Report;
import h.model.shared.util.EnumUtil;

public class ReportSql extends AbstractSql
{
  private final SqlUpdate mUpsert;
  private final MapReport mSelect0;
  private final MapReport mSelect1;
  private final MapReport mSelect2;
  private final MapReport mSelect3;

  public ReportSql(DataSource inDataSource)
  {
    mStmts = getStatements("Report.xml");

    Statement upsert = mStmts.getStatement("UPSERT");
    mUpsert = newSqlUpdate(inDataSource, upsert.getSql(), upsert.types());
    mSelect0 = new MapReport(inDataSource, mStmts.getStatement("SELECT_C"));
    mSelect1 = new MapReport(inDataSource, mStmts.getStatement("SELECT_CP"));
    mSelect2 = new MapReport(inDataSource, mStmts.getStatement("SELECT_CR"));
    mSelect3 = new MapReport(inDataSource, mStmts.getStatement("SELECT_CPR"));
  }

  public int upsert(Report inReport)
  {
    return mUpsert.update(params(inReport.getCongId(), inReport.getPubId(), inReport.getYear(),
        inReport.getMonth(), inReport.gDate(), inReport.gSendDate(),
        inReport.getNoActivity(), inReport.getPlacements(), inReport.getVideoShowings(),
        inReport.getHours(), inReport.getReturnVisits(), inReport.getBibleStudies(),
        inReport.getCreditHours(), inReport.getIncludeAllHours(), inReport.getRemarks(),
        EnumUtil.name(inReport.getType()), inReport.getPartialHours(), inReport.gSendDate(),
        inReport.getNoActivity(), inReport.getPlacements(), inReport.getVideoShowings(),
        inReport.getHours(), inReport.getReturnVisits(), inReport.getBibleStudies(),
        inReport.getCreditHours(), inReport.getIncludeAllHours(), inReport.getRemarks(),
        EnumUtil.name(inReport.getType()), inReport.getPartialHours()));
  }

  public List<Report> select(int inCongId)
  {
    return mSelect0.execute(params(inCongId));
  }

  public List<Report> select(int inCongId, long inPubId)
  {
    return mSelect1.execute(params(inCongId, inPubId));
  }

  public List<Report> select(int inCongId, Date inBegin, Date inEnd)
  {
    return mSelect2.execute(params(inCongId, inBegin, inEnd));
  }

  public List<Report> select(int inCongId, int inPastMonths)
  {
    Calendar start = Calendar.getInstance();
    start.set(Calendar.MONTH, -inPastMonths);
    Calendar end = Calendar.getInstance();
    return select(inCongId, start.getTime(), end.getTime());
  }

  public List<Report> select(int inCongId, long inPubId, Date inBegin, Date inEnd)
  {
    return mSelect3.execute(params(inCongId, inPubId, inBegin, inEnd));
  }

  private class MapReport extends MappingSqlEncrypt<Report>
  {
    public MapReport(DataSource inDataSource, Statement inStmt)
    {
      super(inDataSource, inStmt.getSql(), inStmt.types());
    }

    @Override
    public Report mapRow(ResultSet inRs, int inRowNum) throws SQLException
    {
      return Mapping.mapReport(inRs);
    }
  }
}