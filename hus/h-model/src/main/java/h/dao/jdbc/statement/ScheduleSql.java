package h.dao.jdbc.statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import h.dao.jdbc.AbstractSql;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Schedule;
import h.model.shared.util.EnumUtil;

public class ScheduleSql extends AbstractSql
{
  private final SqlUpdate mInsert;
  private final SqlUpdate mUpsert;
  private final SqlUpdate mUpsert2;
  private final SqlUpdate mUpdate0;
  private final SqlUpdate mUpdate1;
  // private final SqlUpdate mUpdate2;

  private final MappingSqlQuery<Schedule> mSelect0;
  private final MappingSqlQuery<Schedule> mSelect1;
  private final MappingSqlQuery<Schedule> mSelect3;
  private final MappingSqlQuery<Integer> mSelect2;
  private final JdbcTemplate mJdbcTemplate;

  public ScheduleSql(DataSource inDataSource)
  {
    mJdbcTemplate = new JdbcTemplate(inDataSource);

    String ui1 = "INSERT INTO SCHEDULE (mCurriculum, mSchool, mLevel, mCongregation, mUpdated) "
        + "SELECT mId, ? AS SCHOOL, 'LEVEL1' AS LEVEL, ? AS CONG, CURRENT_TIMESTAMP AS NOW "
        + "FROM CURRICULUM WHERE YEAR(mDate)=? AND mSchool LIKE ? "
        + "ON DUPLICATE KEY UPDATE mUpdated=CURRENT_TIMESTAMP";
    mUpsert = newSqlUpdate(inDataSource, ui1,
        types(Types.VARCHAR, Types.NUMERIC, Types.NUMERIC, Types.VARCHAR));

    String ui2 = "INSERT INTO SCHEDULE (mCurriculum, mSchool, mLevel, mCongregation, mUpdated) "
        + "SELECT mId, ? AS SCHOOL, 'LEVEL1' AS LEVEL, ? AS CONG, CURRENT_TIMESTAMP AS NOW "
        + "FROM CURRICULUM WHERE YEAR(mDate)=? AND MONTH(mDate)=? AND mSchool LIKE ? "
        + "ON DUPLICATE KEY UPDATE mUpdated=CURRENT_TIMESTAMP";
    mUpsert2 = newSqlUpdate(inDataSource, ui2,
        types(Types.VARCHAR, Types.NUMERIC, Types.NUMERIC, Types.NUMERIC, Types.VARCHAR));

    // TODO always updating across years and main.
    String u1 = "UPDATE SCHEDULE as s1 INNER JOIN SCHEDULE as s2 ON s1.mCurriculum=s2.mCurriculum "
        + "AND s1.mCongregation=s2.mCongregation AND s2.mSchool=? SET s1.mLevel=s2.mLevel, "
        + "s1.mUpdated=CURRENT_TIMESTAMP where s1.mCongregation=?";
    mUpdate1 = new SqlUpdate(inDataSource, u1, types(Types.VARCHAR, Types.NUMERIC));
    mUpdate1.compile();

    // String u2 = "update SCHEDULE set mLevel=?,mUpdated=CURRENT_TIMESTAMP "
    // + "where mCongregation=? AND mCurriculum=?";
    // mUpdate2 = newSqlUpdate(inDataSource, u2, Types.VARCHAR, Types.NUMERIC,
    // Types.NUMERIC);

    String s0 =
        "SELECT * FROM CURRICULUM c LEFT OUTER JOIN SCHEDULE s ON s.mCurriculum = c.mId AND mCongregation=? "
            + "AND s.mSchool=? where YEAR(c.mDate)=? order by c.mDate, c.mPart";
    mSelect0 = new MappingSqlQuery<Schedule>(inDataSource, s0)
    {
      @Override
      protected Schedule mapRow(ResultSet inRs, int inRowNum) throws SQLException
      {
        return Mapping.mapSchedule(inRs, "c", "s");
      }
    };
    mSelect0.setTypes(types(Types.NUMERIC, Types.VARCHAR, Types.NUMERIC));
    mSelect0.compile();

    String s3 =
        "SELECT * FROM CURRICULUM c, SCHEDULE s WHERE s.mCurriculum=c.mId AND mCongregation=? AND s.mSchool=? AND c.mId=?";
    mSelect3 = new MappingSqlQuery<Schedule>(inDataSource, s3)
    {
      @Override
      protected Schedule mapRow(ResultSet inRs, int inRowNum) throws SQLException
      {
        return Mapping.mapSchedule(inRs, "c", "s");
      }
    };
    mSelect3.setTypes(types(Types.NUMERIC, Types.VARCHAR, Types.NUMERIC));
    mSelect3.compile();

    String s1 = "SELECT * FROM SCHEDULE s where s.mCongregation=? AND YEAR(c.mDate)=? "
        + "order by c.mDate, c.mPart";
    mSelect1 = new MappingSqlQuery<Schedule>(inDataSource, s1)
    {
      @Override
      protected Schedule mapRow(ResultSet inRs, int inRowNum) throws SQLException
      {
        return Mapping.mapSchedule(inRs, "s");
      }
    };
    mSelect1.setTypes(types(Types.NUMERIC, Types.NUMERIC));
    mSelect1.compile();

    String i = "insert into SCHEDULE (mCongregation,mCurriculum,mSchool,mLevel,mUpdated) "
        + "values (?,?,?,?,CURRENT_TIMESTAMP)";
    mInsert = new SqlUpdate(inDataSource, i,
        types(Types.NUMERIC, Types.NUMERIC, Types.VARCHAR, Types.VARCHAR));
    mInsert.compile();

    String u0 = "update SCHEDULE set mCongregation=?,mCurriculum=?,mSchool=?,mLevel=?,"
        + "mUpdated=CURRENT_TIMESTAMP where mId=?";
    mUpdate0 = new SqlUpdate(inDataSource, u0,
        types(Types.NUMERIC, Types.NUMERIC, Types.VARCHAR, Types.VARCHAR, Types.NUMERIC));
    mUpdate0.compile();

    String s2 = "select YEAR(mDate) mYear FROM CURRICULUM c, SCHEDULE s where s.mCurriculum=c.mId "
        + "AND s.mCongregation=? GROUP BY mYear order by mYear ";
    mSelect2 = new MappingSqlQuery<Integer>(inDataSource, s2)
    {
      @Override
      protected Integer mapRow(ResultSet inRs, int inRowNum) throws SQLException
      {
        return inRs.getInt("mYear");
      }
    };
    mSelect2.setTypes(types(Types.NUMERIC));
    mSelect2.compile();
  }

  public List<Integer> selectYears(long inCongregationId)
  {
    return mSelect2.execute(params(inCongregationId));
  }

  public long select(long inCongregationId, Hall inSchool, int inYear)
  {
    // TODO check for null pointer exception
    String s = "SELECT COUNT(*) FROM SCHEDULE s, CURRICULUM c WHERE s.mCurriculum=c.mId AND "
        + "mCongregation=? AND mSchool=? AND YEAR(c.mDate)=?";
    long ret = mJdbcTemplate.queryForObject(s, Long.class,
        params(inCongregationId, inSchool.name(), inYear),
        types(Types.NUMERIC, Types.VARCHAR, Types.NUMERIC));
    return ret;
  }

  public int upsert(long inCongregationId, Hall inSchool, int inYear)
  {
    return mUpsert.update(params(inSchool, inCongregationId, inYear, like(inSchool.school())));
  }

  public int update(long inCongregationId)
  {
    return mUpdate1.update(params(Hall.MAIN, inCongregationId));
  }

  @Deprecated
  public int insert(Schedule inSchedule)
  {
    return mInsert.update(params(inSchedule.getCongregation(), inSchedule.getCurriculum().getId(),
        name(inSchedule.getSchool()), null));
  }

  public int update(Schedule inSchedule)
  {
    return mUpdate0.update(params(inSchedule.getCongregation(), inSchedule.getCurriculum().getId(),
        name(inSchedule.getSchool()), null, inSchedule.getId()));
  }

  public List<Schedule> select(long inCongregation, int inYear, Hall inSchool)
  {
    return mSelect0.execute(params(inCongregation, EnumUtil.name(inSchool), inYear));
  }

  public List<Schedule> selectSchedules(long inCongregation, int inYear)
  {
    return mSelect1.execute(params(inCongregation, inYear));
  }

  // public int update(Level inLevel, Long inCongregationId, Long
  // inCurriculumId)
  // {
  // return mUpdate2.update(params(EnumUtil.name(inLevel), inCongregationId,
  // inCurriculumId));
  // }

  public Schedule select(long inCongId, long inCurriculumId, Hall inSchool)
  {
    return only(mSelect3.execute(params(inCongId, EnumUtil.name(inSchool), inCurriculumId)));
  }

  public int upsert(long inCongregationId, Hall inSchool, int inYear, int inMonth)
  {
    return mUpsert2
        .update(params(inSchool, inCongregationId, inYear, inMonth, like(inSchool.school())));
  }

  private static String like(String inText)
  {
    return "%" + inText + "%";
  }
}