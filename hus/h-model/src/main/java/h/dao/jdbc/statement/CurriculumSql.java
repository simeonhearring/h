package h.dao.jdbc.statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import h.dao.jdbc.AbstractSql;
import h.model.shared.khall.Curriculum;

public class CurriculumSql extends AbstractSql
{
  private final SqlUpdate mUpsert;
  private final MappingSqlQuery<Curriculum> mSelect;
  private final MappingSqlQuery<Curriculum> mSelect2;
  private final MappingSqlQuery<Integer> mSelect1;

  public CurriculumSql(DataSource inDataSource)
  {
    String ui = "INSERT INTO CURRICULUM (mDate,mPart,mTheme,mSource,mNote,mUpdated) "
        + "VALUES (?,?,?,?,?,CURRENT_TIMESTAMP) ON DUPLICATE KEY "
        + "UPDATE mTheme=?,mSource=?,mNote=?,mUpdated=CURRENT_TIMESTAMP";
    mUpsert = newSqlUpdate(inDataSource, ui, Types.DATE, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

    String s = "select * from CURRICULUM c where YEAR(c.mDate)=? order by c.mDate, c.mSort";
    mSelect = new MappingSqlQuery<Curriculum>(inDataSource, s)
    {
      @Override
      protected Curriculum mapRow(ResultSet inRs, int inRowNum) throws SQLException
      {
        return Mapping.mapCurriculum(inRs, "c");
      }
    };
    mSelect.setTypes(types(Types.NUMERIC));
    mSelect.compile();

    String s2 = "select * from CURRICULUM c where c.mDate between ? AND ? order by c.mDate, c.mSort";
    mSelect2 = new MappingSqlQuery<Curriculum>(inDataSource, s2)
    {
      @Override
      protected Curriculum mapRow(ResultSet inRs, int inRowNum) throws SQLException
      {
        return Mapping.mapCurriculum(inRs, "c");
      }
    };
    mSelect2.setTypes(types(Types.DATE, Types.DATE));
    mSelect2.compile();

    String s1 = "SELECT YEAR(mDate) mYear FROM CURRICULUM GROUP BY mYear ORDER BY mYear";
    mSelect1 = new MappingSqlQuery<Integer>(inDataSource, s1)
    {
      @Override
      protected Integer mapRow(ResultSet inRs, int inRowNum) throws SQLException
      {
        return inRs.getInt("mYear");
      }
    };
    mSelect1.compile();
  }

  public List<Integer> selectYears()
  {
    return mSelect1.execute();
  }

  public int upsert(Curriculum inCurriculum)
  {
    return mUpsert.update(params(inCurriculum.getDate(), inCurriculum.getPart().name(),
        inCurriculum.getTheme(), inCurriculum.getSource(), null, inCurriculum.getTheme(),
        inCurriculum.getSource(), null));
  }

  public List<Curriculum> selectByYear(int inYear)
  {
    return mSelect.execute(params(inYear));
  }

  public List<Curriculum> selectBetween(Date inStart, Date inEnd)
  {
    return mSelect2.execute(params(inStart, inEnd));
  }
}