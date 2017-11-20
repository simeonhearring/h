package h.dao.jdbc.statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import h.dao.jdbc.AbstractSql;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Part;
import h.model.shared.khall.StudyPoint;

public class ScheduleSql extends AbstractSql
{
  private final SqlUpdate mUpsertYM;
  private final SqlUpdate mUpsertYMP;

  private final SqlUpdate mUpdate;

  private final MappingSqlQuery<Assignment> mSelectY;
  private final MappingSqlQuery<Assignment> mSelectYM;

  public ScheduleSql(DataSource inDataSource)
  {
    mStmts = getStatements("Schedule.xml");

    Statement upsertYM = mStmts.getStatement("UPSERT_YM");
    mUpsertYM = newSqlUpdate(inDataSource, upsertYM.getSql(), upsertYM.types());

    Statement upsertYMP = mStmts.getStatement("UPSERT_YMP");
    mUpsertYMP = newSqlUpdate(inDataSource, upsertYMP.getSql(), upsertYMP.types());

    Statement update = mStmts.getStatement("UPDATE");
    mUpdate = newSqlUpdate(inDataSource, update.getSql(), update.types());

    Statement selectY = mStmts.getStatement("SELECT_Y");
    mSelectY = new MappingSql<Assignment>(inDataSource, selectY.getSql(), selectY.types())
    {
      @Override
      public Assignment mapRow(ResultSet inRs, int inRowNum) throws SQLException
      {
        return Mapping.mapSchedule(inRs, "c", "s");
      }
    };

    Statement selectYM = mStmts.getStatement("SELECT_YM");
    mSelectYM = new MappingSql<Assignment>(inDataSource, selectYM.getSql(), selectYM.types())
    {
      @Override
      public Assignment mapRow(ResultSet inRs, int inRowNum) throws SQLException
      {
        return Mapping.mapSchedule(inRs, "c", "s");
      }
    };
  }

  public int upsert(long inCongregationId, Hall inSchool, int inYear, int inMonth)
  {
    return mUpsertYM.update(params(inSchool, inCongregationId, inYear, inMonth));
  }

  public int upsert(long inCongregationId, Hall inSchool, int inYear, int inMonth, Part inPart)
  {
    return mUpsertYMP.update(params(inSchool, inCongregationId, inYear, inMonth, inPart.name()));
  }

  public List<Assignment> select(long inCongregation, int inYear)
  {
    return mSelectY.execute(params(inCongregation, inYear));
  }

  public List<Assignment> select(long inCongregation, int inYear, int inMonth)
  {
    return mSelectYM.execute(params(inCongregation, inYear, inMonth));
  }

  public void update(Assignment inAssignment)
  {
    update(inAssignment.getParticipantId(), inAssignment.getAssistantId(),
        inAssignment.getStudyPoint(), inAssignment.getId());
  }

  public int update(Long inParticipantId, Long inAssistantId, StudyPoint inStudyPoint, Long inId)
  {
    return mUpdate.update(params(inParticipantId, inAssistantId, StudyPoint.get(inStudyPoint), inId));
  }
}