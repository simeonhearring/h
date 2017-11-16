package h.dao.jdbc;

import javax.sql.DataSource;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.jdbc.object.SqlUpdate;

public abstract class AbstractEncryptSqlUpdate<T> extends SqlUpdate
{
  public abstract Object[] params(T inObject);

  public AbstractEncryptSqlUpdate(DataSource inDataSource, String inSql, int... inTypes)
  {
    super(inDataSource, inSql, inTypes);
    compile();
  }

  public int execute(String inKey, T inObject)
  {
    getJdbcTemplate().execute("set @key = '" + inKey + "'");
    return update(params(inObject));
  }

  public String encrypt(StandardPBEStringEncryptor inEncryptor, String inValue)
  {
    if (inValue == null)
    {
      return null;
    }
    return inEncryptor.encrypt(inValue);
  }

  public String decrypt(StandardPBEStringEncryptor inEncryptor, String inValue)
  {
    if (inValue == null)
    {
      return null;
    }
    return inEncryptor.decrypt(inValue);
  }
}
