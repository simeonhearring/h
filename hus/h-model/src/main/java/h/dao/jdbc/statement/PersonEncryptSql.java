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
  // private final AbstractEncryptSqlUpdate<Person> mUpdate1;
  // private final AbstractEncryptSqlUpdate<Person> mUpdate2;
  private final MapPersonEncrypt mSelect0;
  private final MapPersonEncrypt mSelect1;

  public PersonEncryptSql(DataSource inDataSource)
  {
    mStmts = getStatements("Person.xml");

    mInsert0 = insertPersonEncryptSql(inDataSource, mStmts.getStatement("INSERT_PE"));
    mUpdate0 = updatePersonEncryptSql(inDataSource, mStmts.getStatement("UPDATE_PE"));
    // mUpdate1 = updateAddressEncryptSql(inDataSource);
    // mUpdate2 = updatePasswordEncryptSql(inDataSource);
    mSelect0 = new MapPersonEncrypt(inDataSource, mStmts.getStatement("SELECT_PE_ID"));
    mSelect1 = new MapPersonEncrypt(inDataSource, mStmts.getStatement("SELECT_PE_LOC"));
  }

  protected int insert(String inKey, Person inValue)
  {
    int ret = mInsert0.execute(inKey, inValue);
    inValue.setId(mInsert0.getJdbcTemplate().queryForObject("SELECT LAST_INSERT_ID()", Long.class));
    return ret;
  }

  public int update(String inKey, Person inValue)
  {
    return mUpdate0.execute(inKey, inValue);
  }

  // public int updateAddress(String inKey, Person inValue)
  // {
  // return mUpdate1.execute(inKey, inValue);
  // }
  //
  // public int updatePassword(String inKey, Person inValue)
  // {
  // return mUpdate2.execute(inKey, inValue);
  // }
  //

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

  //
  // protected static String peE()
  // {
  // String ret = "select
  // pe.mId,pe.mCreated,pe.mUpdated,pe.mCongregation,pe.mHeadOfHouse,"
  // + "AES_DECRYPT(mFirst,@key) AS mFirst,AES_DECRYPT(mLast,@key) AS mLast,"
  // + "AES_DECRYPT(mMiddle,@key) AS mMiddle,AES_DECRYPT(mSuffix,@key) AS
  // mSuffix,"
  // + "AES_DECRYPT(mGender,@key) AS mGender,AES_DECRYPT(mEmail,@key) AS
  // mEmail,"
  // + "AES_DECRYPT(mAddress,@key) AS mAddress,AES_DECRYPT(mPhone,@key) AS
  // mPhone,"
  // + "AES_DECRYPT(mMobile,@key) AS mMobile,AES_DECRYPT(mBirth,@key) AS
  // mBirth,"
  // + "AES_DECRYPT(mChildren,@key) AS mChildren,AES_DECRYPT(mEmergency,@key) AS
  // mEmergency,"
  // + "AES_DECRYPT(mFamily,@key) AS mFamily, AES_DECRYPT(mPassword,@key) AS
  // mPassword";
  // return ret;
  // }
  //
  private AbstractEncryptSqlUpdate<Person> updatePersonEncryptSql(DataSource inDataSource, Statement inStmt)
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

  // private static AbstractEncryptSqlUpdate<Person>
  // updateAddressEncryptSql(DataSource inDataSource)
  // {
  // String upd1 = "update PERSON set
  // mAddress=AES_ENCRYPT(?,@key),mPhone=AES_ENCRYPT(?,@key),"
  // + "mUpdated=CURRENT_TIMESTAMP where mHeadOfHouse=?";
  //
  // int[] types = types(Types.VARCHAR, Types.VARCHAR, Types.NUMERIC);
  //
  // return new AbstractEncryptSqlUpdate<Person>(inDataSource, upd1, types)
  // {
  // @Override
  // public Object[] params(Person inValue)
  // {
  // return PersonEncryptSql.params(inValue.getAddress(), inValue.getPhone(),
  // inValue.getId());
  // }
  // };
  // }
  //
  // private static AbstractEncryptSqlUpdate<Person>
  // updatePasswordEncryptSql(DataSource inDataSource)
  // {
  // String upd1 = "update PERSON set mPassword=AES_ENCRYPT(?,@key),"
  // + "mUpdated=CURRENT_TIMESTAMP where mId=?";
  //
  // int[] types = types(Types.VARCHAR, Types.NUMERIC);
  //
  // return new AbstractEncryptSqlUpdate<Person>(inDataSource, upd1, types)
  // {
  // @Override
  // public Object[] params(Person inValue)
  // {
  // return PersonEncryptSql.params(inValue.getPassword(), inValue.getId());
  // }
  // };
  // }

  private AbstractEncryptSqlUpdate<Person> insertPersonEncryptSql(DataSource inDataSource,
      Statement inStmt)
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