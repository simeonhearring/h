<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="site.dao.Dao"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%
  String html = "";
  Dao dao = (Dao) WebApplicationContextUtils.getWebApplicationContext(config.getServletContext()).getBean("Dao");
  html = dao.test();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	  out.println(html);
	%>


</body>
</html>