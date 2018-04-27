package h.dao.jdbc;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySqlBaseDaoTest
{
  private static ApplicationContext sAppContext;
  private JdbcTemplate mJdbcTemplate;

  static
  {
    try
    {
      sAppContext = new ClassPathXmlApplicationContext("environmentContext-sec.xml");
    }
    catch (BeansException e)
    {
      e.printStackTrace();
      // do nothing
    }
  }

  protected DataSource mDataSource;
  protected DataSource mDataSourceBkup;

  @Before
  public void createDataSource()
  {
    initDataSource();
    Assume.assumeTrue(mDataSource != null);
  }

  protected void initDataSource()
  {
    mDataSource = (DataSource) bean(getDataSourceBeanName());
    mDataSourceBkup = (DataSource) bean("KhallBkup");
  }

  private static Object bean(String inBeanName)
  {
    Object ret = null;
    if (sAppContext != null)
    {
      ret = sAppContext.getBean(inBeanName);
    }
    return ret;
  }

  protected String getDataSourceBeanName()
  {
    return "Khall";
  }

  public DataSource getDataSource()
  {
    return mDataSource;
  }

  @Test
  public void dataSource()
  {
    assertNotNull(mDataSource);
  }

  @Test
  public void canSelect()
  {
    List<Map<String, Object>> results = getJdbcTemplate().queryForList("SELECT NOW() AS TIME");
    Map<String, Object> map = results.get(0);
    Assert.assertEquals(true, map.containsKey("TIME"));
    Assert.assertNotNull(map.get("TIME"));
  }

  public void runUpdateSql(String inSql)
  {
    Connection conn = null;
    Statement stmt = null;
    try
    {
      conn = mDataSource.getConnection();
      stmt = conn.createStatement();
      stmt.executeUpdate(inSql);
      // conn.commit();
    }
    catch (Exception e)
    {
      Assert.fail("sample data not cleaned up. " + e.getMessage());
    }
    finally
    {
      closeStatement(stmt);
      closeConn(conn);
    }
  }

  public JdbcTemplate getJdbcTemplate()
  {
    if (mJdbcTemplate == null)
    {
      mJdbcTemplate = new JdbcTemplate(mDataSource);
    }
    return mJdbcTemplate;
  }

  protected static void closeResultSet(ResultSet inClosable)
  {
    try
    {
      inClosable.close();
    }
    catch (Exception e)
    {
      // do nothing
    }
  }

  protected static void closeConn(Connection inClosable)
  {
    try
    {
      inClosable.close();
    }
    catch (Exception e)
    {
      // do nothing
    }
  }

  protected static void closeStatement(Statement inClosable)
  {
    try
    {
      inClosable.close();
    }
    catch (Exception e)
    {
      // do nothing
    }
  }

}
