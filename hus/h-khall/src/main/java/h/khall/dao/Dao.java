package h.khall.dao;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class Dao extends h.spring.SpringBean
{
  public String test()
  {
    JdbcTemplate jdbc = new JdbcTemplate((DataSource) getBean("Khall"));
    return jdbc.queryForObject("SELECT NOW();", Date.class).toString();
  }
}