package h.dao.jdbc.statement;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.SqlUpdate;

import h.dao.jdbc.AbstractSql;
import h.model.shared.khall.Congregation;

public class CongSql extends AbstractSql
{
  private final SqlUpdate mUpsert;
  private final MapCong mSelect0;
  private final MapCong mSelect1;

  public CongSql(DataSource inDataSource)
  {
    mStmts = getStatements("Cong.xml");

    mUpsert = newSqlUpdate(inDataSource, mStmts.getStatement("UPSERT"));
    mSelect0 = new MapCong(inDataSource, mStmts.getStatement("SELECT_ID"));
    mSelect1 = new MapCong(inDataSource, mStmts.getStatement("SELECT_NUM"));
  }

  public int upsert(Congregation inCong)
  {
    String json = writeValue(inCong);
    return mUpsert
        .update(params(inCong.getNumber(), inCong.getName(), json, inCong.getName(), json));
  }

  public Congregation selectById(Integer inId)
  {
    return only(mSelect0.execute(params(inId)));
  }

  public Congregation selectByCongNum(String inNumber)
  {
    return only(mSelect1.execute(params(inNumber)));
  }

  private class MapCong extends MappingSql<Congregation>
  {
    public MapCong(DataSource inDataSource, Statement inStmt)
    {
      super(inDataSource, inStmt.getSql(), inStmt.types());
    }

    @Override
    public Congregation mapRow(ResultSet inRs, int inRowNum) throws SQLException
    {
      Congregation ret = readValue(inRs.getString("mProfile"), Congregation.class);
      ret.setId(inRs.getInt("mId"));
      return ret;
    }
  }
}