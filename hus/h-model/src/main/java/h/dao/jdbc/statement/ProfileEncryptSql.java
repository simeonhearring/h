package h.dao.jdbc.statement;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.SqlUpdate;

import h.dao.jdbc.AbstractEncryptSqlUpdate;
import h.dao.jdbc.AbstractSql;
import h.model.shared.khall.Profile;

public class ProfileEncryptSql extends AbstractSql
{
  private final SqlUpdate mUpsert0;

  private final AbstractEncryptSqlUpdate<Profile> mUpsert1;
  private final MapProfileEncrypt mSelect1;

  public ProfileEncryptSql(DataSource inDataSource)
  {
    mStmts = getStatements("Profile.xml");

    mUpsert0 = newSqlUpdate(inDataSource, mStmts.getStatement("UPSERT"));

    mUpsert1 = update(inDataSource, mStmts.getStatement("UPSERT_KEY"));

    mSelect1 = new MapProfileEncrypt(inDataSource, mStmts.getStatement("SELECT_LOC"));
  }

  public int upsert(Profile inProfile)
  {
    String profile = writeValue(inProfile);
    return mUpsert0.update(params(inProfile.getType().name(), inProfile.gLocator(), profile, profile));
  }

  public int upsert(String inKey, Profile inProfile)
  {
    return mUpsert1.execute(inKey, inProfile);
  }

  public Profile select(String inKey, String inLocator)
  {
    return only(mSelect1.select(inKey, params(inLocator)));
  }

  private AbstractEncryptSqlUpdate<Profile> update(DataSource inDataSource, Statement inStmt)
  {
    return new AbstractEncryptSqlUpdate<Profile>(inDataSource, inStmt.getSql(), inStmt.types())
    {
      @Override
      public Object[] params(Profile inModel)
      {
        String value = writeValue(inModel);
        return ProfileEncryptSql.params(inModel.getType().name(), inModel.gLocator(), value,
            inModel.gPassword(), value, inModel.gPassword());
      }
    };
   }

  private class MapProfileEncrypt extends MappingSqlEncrypt<Profile>
  {
    public MapProfileEncrypt(DataSource inDataSource, Statement inStmt)
    {
      super(inDataSource, inStmt.getSql(), inStmt.types());
    }

    @Override
    public Profile mapRow(ResultSet inRs, int inRowNum) throws SQLException
    {
      Profile ret = readValue(inRs.getString("mProfile"), Profile.class);
      ret.setPassword(inRs.getString("mKey"));
      return ret;
    }
  }
}