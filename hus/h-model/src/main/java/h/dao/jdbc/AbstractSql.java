package h.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

public abstract class AbstractSql
{
  public static SqlUpdate newSqlUpdate(DataSource inDataSource, String inSql, int... inTypes)
  {
    SqlUpdate ret = new SqlUpdate(inDataSource, inSql, inTypes);
    ret.compile();
    return ret;
  }

  public static int[] types(int... inNumeric)
  {
    return inNumeric;
  }

  public static Object[] params(Object... inObject)
  {
    return inObject;
  }

  public static <T> T first(List<T> inList)
  {
    return inList != null && inList.size() > 0 ? inList.get(0) : null;
  }

  public static <T> T only(List<T> inList)
  {
    return inList != null && inList.size() == 1 ? inList.get(0) : null;
  }

  public static <T extends Enum<?>> T valueOf(String inString, T[] inValues)
  {
    if (inString == null || "".equals(inString.trim()))
    {
      return null;
    }

    T ret = null;
    for (T value : inValues)
    {
      if (value.name().equals(inString))
      {
        ret = value;
      }
    }
    return ret;
  }

  public static String name(Enum<?> inEnum)
  {
    return inEnum != null ? inEnum.name() : null;
  }

  public static <T extends Enum<?>> List<T> toList(String inString, T[] inValues)
  {
    List<T> list = toValidList(inString, inValues);
    return list.size() == 0 ? null : list;
  }

  public static <T extends Enum<?>> List<T> toValidList(String inString, T[] inValues)
  {
    List<T> ret = new ArrayList<>();

    if (inString != null && inString.indexOf("|") != -1)
    {
      for (String value : inString.split("\\|"))
      {
        T valueOf = valueOf(value, inValues);
        if (valueOf != null)
        {
          ret.add(valueOf);
        }
      }
    }

    return ret;
  }

  public static <T extends Enum<?>> String toString(List<T> inEnum)
  {
    String ret = null;

    if (inEnum != null && inEnum.size() > 0)
    {
      StringBuilder sb = new StringBuilder();
      for (T t : inEnum)
      {
        sb.append(name(t)).append("|");
      }
      ret = sb.toString();
    }

    return ret;
  }

  @SafeVarargs
  public static <T extends Enum<?>> String toString(T... inEnum)
  {
    String ret = null;

    if (inEnum != null && inEnum.length > 0)
    {
      StringBuilder sb = new StringBuilder();
      for (T t : inEnum)
      {
        sb.append(name(t)).append("|");
      }
      ret = sb.toString();
    }

    return ret;
  }

  public static abstract class MappingSqlEncrypt<T> extends MappingSqlQuery<T> implements
  RowMapper<T>
  {
    public MappingSqlEncrypt()
    {
    }

    public MappingSqlEncrypt(DataSource inDataSource, String inSql, int... inTypes)
    {
      super(inDataSource, inSql);
      setTypes(inTypes);
      compile();
    }

    public List<T> select(String inKey, Object[] inParams)
    {
      getJdbcTemplate().execute("set @key = '" + inKey + "'");
      return execute(inParams);
    }
  }

  public static abstract class MappingSql<T> extends MappingSqlQuery<T> implements RowMapper<T>
  {
    public MappingSql(DataSource inDataSource, String inSql, int... inTypes)
    {
      super(inDataSource, inSql);
      setTypes(inTypes);
      compile();
    }
  }
}