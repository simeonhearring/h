package h.dao.jdbc;

import java.sql.Types;
import java.util.Date;

import javax.sql.DataSource;

import h.model.shared.khall.Person;

public class PersonOldEncryptSql extends AbstractSql
{
  private final AbstractEncryptSqlUpdate<Person> mInsert0;
  private final AbstractEncryptSqlUpdate<Person> mUpdate0;
  private final AbstractEncryptSqlUpdate<Person> mUpdate1;

  public PersonOldEncryptSql(DataSource inDataSource)
  {
    mInsert0 = insertPersonEncryptSql(inDataSource);
    mUpdate0 = updatePersonEncryptSql(inDataSource);
    mUpdate1 = updateAddressEncryptSql(inDataSource);
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

  public int updateAddress(String inKey, Person inValue)
  {
    return mUpdate1.execute(inKey, inValue);
  }

  protected static String peE()
  {
    String ret = "select pe.mId,pe.mCreated,pe.mUpdated,pe.mCongregation,pe.mHeadOfHouse,"
        + "AES_DECRYPT(mFirst,@key) AS mFirst,AES_DECRYPT(mLast,@key) AS mLast,"
        + "AES_DECRYPT(mMiddle,@key) AS mMiddle,AES_DECRYPT(mSuffix,@key) AS mSuffix,"
        + "AES_DECRYPT(mGender,@key) AS mGender,AES_DECRYPT(mEmail,@key) AS mEmail,"
        + "AES_DECRYPT(mAddress,@key) AS mAddress,AES_DECRYPT(mPhone,@key) AS mPhone,"
        + "AES_DECRYPT(mMobile,@key) AS mMobile,AES_DECRYPT(mBirth,@key) AS mBirth,"
        + "AES_DECRYPT(mChildren,@key) AS mChildren,AES_DECRYPT(mEmergency,@key) AS mEmergency,"
        + "AES_DECRYPT(mFamily,@key) AS mFamily, AES_DECRYPT(mPassword,@key) AS mPassword";
    return ret;
  }

  private static AbstractEncryptSqlUpdate<Person> updatePersonEncryptSql(DataSource inDataSource)
  {
    String upd1 = "update PERSON set mFirst=AES_ENCRYPT(?,@key),mLast=AES_ENCRYPT(?,@key),"
        + "mMiddle=AES_ENCRYPT(?,@key),mSuffix=AES_ENCRYPT(?,@key),mGender=AES_ENCRYPT(?,@key),"
        + "mEmail=AES_ENCRYPT(?,@key),mAddress=AES_ENCRYPT(?,@key),mPhone=AES_ENCRYPT(?,@key),"
        + "mMobile=AES_ENCRYPT(?,@key),mBirth=AES_ENCRYPT(?,@key),mChildren=AES_ENCRYPT(?,@key),"
        + "mEmergency=AES_ENCRYPT(?,@key),mFamily=AES_ENCRYPT(?,@key),mHeadOfHouse=?,"
        + "mUpdated=CURRENT_TIMESTAMP where mId=?";

    int[] types = types(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.NUMERIC, Types.NUMERIC);

    return new AbstractEncryptSqlUpdate<Person>(inDataSource, upd1, types)
    {
      @Override
      public Object[] params(Person inValue)
      {
        return PersonOldEncryptSql.params(inValue.getFirst(), inValue.getLast(), inValue.getMiddle(),
            inValue.getSuffix(), inValue.getGender().name().toUpperCase(), inValue.getEmail(), inValue.getAddress(),
            inValue.getHome(), inValue.getMobile(), getBirthMills(inValue.getBirth()),
            inValue.getChildren(), inValue.getEmergency(), null, inValue.getHead(),
            inValue.gId());
      }
    };
  }

  private static AbstractEncryptSqlUpdate<Person> updateAddressEncryptSql(DataSource inDataSource)
  {
    String upd1 = "update PERSON set mAddress=AES_ENCRYPT(?,@key),mPhone=AES_ENCRYPT(?,@key),"
        + "mUpdated=CURRENT_TIMESTAMP where mHeadOfHouse=?";

    int[] types = types(Types.VARCHAR, Types.VARCHAR, Types.NUMERIC);

    return new AbstractEncryptSqlUpdate<Person>(inDataSource, upd1, types)
    {
      @Override
      public Object[] params(Person inValue)
      {
        return PersonOldEncryptSql.params(inValue.getAddress(), inValue.getHome(), inValue.gId());
      }
    };
  }

  private static AbstractEncryptSqlUpdate<Person> insertPersonEncryptSql(DataSource inDataSource)
  {
    String ins1 =
        "insert into PERSON (mCongregation,mFirst,mLast,mMiddle,mSuffix,mGender,mEmail,mAddress,"
            + "mPhone,mMobile,mBirth,mFamily,mHeadOfHouse,mCreated,mUpdated) values "
            + "(?,AES_ENCRYPT(?,@key),AES_ENCRYPT(?,@key),AES_ENCRYPT(?,@key),AES_ENCRYPT(?,@key),"
            + "AES_ENCRYPT(?,@key),AES_ENCRYPT(?,@key),AES_ENCRYPT(?,@key),AES_ENCRYPT(?,@key),"
            + "AES_ENCRYPT(?,@key),AES_ENCRYPT(?,@key),AES_ENCRYPT(?,@key),?,"
            + "CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";

    int[] types = types(Types.NUMERIC, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.NUMERIC);

    return new AbstractEncryptSqlUpdate<Person>(inDataSource, ins1, types)
    {
      @Override
      public Object[] params(Person inValue)
      {
        return PersonOldEncryptSql.params(inValue.getCongId(), inValue.getFirst(),
            inValue.getLast(), inValue.getMiddle(), inValue.getSuffix(),
            inValue.getGender().name().toUpperCase(), inValue.getEmail(), inValue.getAddress(),
            inValue.getHome(), inValue.getMobile(), getBirthMills(inValue.getBirth()), null,
            inValue.getHead());
      }
    };
  }

  public static Long getBirthMills(Date inBirth)
  {
    return inBirth != null ? inBirth.getTime() : null;
  }
}