package h.khall.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class Dao extends h.spring.SpringBean
{
  public String test()
  {
    JdbcTemplate jdbc = new JdbcTemplate((DataSource) getBean("Khall"));
    return jdbc.queryForObject("SELECT NOW();", Date.class).toString();
  }

  public String congregation()
  {
    JdbcTemplate jdbc = new JdbcTemplate((DataSource) getBean("Khall"));
    return toTable(jdbc.queryForList("SELECT * FROM CONGREGATION"));
  }

  public static String toTable(List<Map<String, Object>> inRows)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("<div>row count: ").append(inRows.size()).append("</div>");
    sb.append("<table cellpadding='5' border='1' style='border-collapse:collapse;'>");
    boolean first = true;
    int i = 1;
    for (Map<String, Object> value : inRows)
    {
      if (first || i++ % 10 == 0)
      {
        addHead(sb, value.keySet(), first);
        first = false;
      }
      addRow(sb, value.keySet(), value);
    }
    sb.append("</table>");
    return sb.toString();
  }

  private static void addHead(StringBuilder inSb, Collection<String> inList, boolean inFoot)
  {
    inSb.append("<thead><tr>");
    for (String value : inList)
    {
      inSb.append("<th>").append(value).append("</th>");
    }
    inSb.append("</tr></thead>");
    if (inFoot)
    {
      inSb.append("<tfoot><tr>");
      for (String value : inList)
      {
        inSb.append("<th>").append(value).append("</th>");
      }
      inSb.append("</tr></tfoot>");
    }
  }

  private static void addRow(StringBuilder inSb, Collection<String> inList,
      Map<String, Object> inValue)
  {
    inSb.append("<tbody><tr>");
    for (String value : inList)
    {
      Object val = inValue.get(value);
      boolean xml = isXmlValue(val);
      inSb.append("<td>");
      inSb.append(xml ? "<xmp style=\"margin: 0px;\">" : "");
      inSb.append(val);
      inSb.append(xml ? "</xmp>" : "");
      inSb.append("</td>");
    }
    inSb.append("</tr></tbody>");
  }

  private static boolean isXmlValue(Object inValue)
  {
    return inValue != null && inValue.toString().indexOf("xml version") != -1;
  }
}