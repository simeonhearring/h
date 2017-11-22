package h.dao.jdbc.statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import h.dao.jdbc.AbstractEncryptSqlUpdate;
import h.dao.jdbc.AbstractSql;
import h.model.shared.khall.Person;

public class PersonEncryptSql extends AbstractSql
{
  private final AbstractEncryptSqlUpdate<Person> mInsert0;
  private final AbstractEncryptSqlUpdate<Person> mUpdate0;
  private final MapPersonEncrypt mSelect0;
  private final MapPersonEncrypt mSelect1;

  public PersonEncryptSql(DataSource inDataSource)
  {
    mStmts = getStatements("Person.xml");

    mInsert0 = insert(inDataSource, mStmts.getStatement("INSERT_PE"));
    mUpdate0 = update(inDataSource, mStmts.getStatement("UPDATE_PE"));
    mSelect0 = new MapPersonEncrypt(inDataSource, mStmts.getStatement("SELECT_PE_ID"));
    mSelect1 = new MapPersonEncrypt(inDataSource, mStmts.getStatement("SELECT_PE_LOC"));
  }

  public int insert(String inKey, Person inValue)
  {
    int ret = mInsert0.execute(inKey, inValue);
    inValue.setId(mInsert0.getJdbcTemplate().queryForObject("SELECT LAST_INSERT_ID()", Long.class));
    return ret;
  }

  public int update(String inKey, Person inValue)
  {
    return mUpdate0.execute(inKey, inValue);
  }

  public List<Person> selectById(String inKey, Long inId)
  {
    return mSelect0.select(inKey, params(inId));
  }

  public List<Person> selectByCongId(String inKey, Integer inId)
  {
    String locater = "%c" + inId + ",%";
    return mSelect1.select(inKey, params(locater));
  }

  public List<Person> selectByFsgId(String inKey, Integer inId)
  {
    String locater = "%f" + inId + ",%";
    return mSelect1.select(inKey, params(locater));
  }

  private AbstractEncryptSqlUpdate<Person> update(DataSource inDataSource, Statement inStmt)
  {
    return new AbstractEncryptSqlUpdate<Person>(inDataSource, inStmt.getSql(), inStmt.types())
    {
      @Override
      public Object[] params(Person inModel)
      {
        String value = writeValue(inModel);
        return PersonEncryptSql.params(value, inModel.getIdLong());
      }
    };
   }

  private AbstractEncryptSqlUpdate<Person> insert(DataSource inDataSource, Statement inStmt)
  {
    return new AbstractEncryptSqlUpdate<Person>(inDataSource, inStmt.getSql(), inStmt.types())
    {
      @Override
      public Object[] params(Person inValue)
      {
        String json = writeValue(inValue);
        return PersonEncryptSql.params(inValue.gLocater(), json);
      }
    };
  }

  private class MapPersonEncrypt extends MappingSqlEncrypt<Person>
  {
    public MapPersonEncrypt(DataSource inDataSource, Statement inStmt)
    {
      super(inDataSource, inStmt.getSql(), inStmt.types());
    }

    @Override
    public Person mapRow(ResultSet inRs, int inRowNum) throws SQLException
    {
      Person ret = readValue(inRs.getString("mProfile"), Person.class);
      ret.setId(inRs.getLong("mId"));
      return ret;
    }
  }
}