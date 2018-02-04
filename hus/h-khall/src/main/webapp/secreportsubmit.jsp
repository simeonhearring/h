<%@page import="h.model.shared.khall.Person"%>
<%@page import="h.model.shared.khall.Reports"%>
<%@page import="h.model.shared.khall.YearMonthRange"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="h.model.shared.khall.Report"%>
<%@page import="h.khall.server.dao.Dao"%>
<%@page import="h.model.shared.util.NumberUtil"%>
<%@page import="h.model.shared.util.TimeUtil"%>
<%@page import="h.model.shared.util.EncryptUtil"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	String logMessages = "", htmlc1 = "", htmlc2 = "", htmlc3 = "", htmlc4 = "", auth = "", stylecol2 = "", stylesave = "";
	String[] data = null; 

	try
	{
		data = EncryptUtil.decrypt(request.getParameter("auth")).split("\\|");
	}
	catch (Exception e) {}

	if (data != null)
	{
		String userName = data[1];
		String userEmail = data[2];
	
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(config.getServletContext());
	
		SimpleDateFormat parse = new SimpleDateFormat("yyyy-M-d");
		SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");
		SimpleDateFormat formatkey = new SimpleDateFormat("yyyyMMdd");
	
		Date now = new Date();
		Date moAgo = new Date(now.getTime() - 18408222000L); // 7 mo ago
	
		int fromYear = moAgo.getYear() + 1900;
		int fromMonth = moAgo.getMonth() + 1;
		int toYear = now.getYear() + 1900;
		int toMonth = now.getMonth();
		int serviceYear = YearMonthRange.yearOfServiceYear(toYear, toMonth + 1);
	
		Date from = TimeUtil.getFirstOfMonth(fromYear, fromMonth);
		Date to = TimeUtil.getFirstOfMonth(toYear, toMonth);
		try 
		{
			Long pubId = NumberUtil.toLong(data[0]);
			Integer congId = NumberUtil.toInteger(data[3]);
			Date starPub = Person.getPublishing(data[4]);
			if (pubId != null)
			{
				Dao dao = (Dao) context.getBean("Dao");

				Person.Locater pub = dao.selectPersonLocater(pubId);
				boolean isPioneer = pub.isRegular() || pub.isSpecial();
				if (isPioneer)
				{
					moAgo = new Date(now.getTime() - 34186698000L); // 13 mo ago
					fromYear = moAgo.getYear() + 1900;
					fromMonth = moAgo.getMonth() + 1;
					from = TimeUtil.getFirstOfMonth(fromYear, fromMonth);
					to = TimeUtil.getFirstOfMonth(serviceYear + 2, toMonth);
				}

				Reports reports = dao.selectReports(congId, pubId, from, to);
	
				StringBuilder sbHtmlc1 = new StringBuilder();
				StringBuilder sbHtmlc2 = new StringBuilder();
				StringBuilder sbHtmlc4 = new StringBuilder();
				StringBuilder sbAuth = new StringBuilder(pubId + "|" + userName + "|" + userEmail + "|");

				List<String> missing = reports.getMissing(fromYear, fromMonth, toYear, toMonth, true, pubId, starPub);
				boolean partial = pub.is15min();

				if (isPioneer)
				{
					Report.Total rt = reports.getServiceYearTotal(serviceYear, pub.gGoal(), pubId);

					htmlc3 = needMsg(serviceYear, rt.getActiveCount(), reports.getTotalHoursNeeded(serviceYear, pub.gGoal(), pubId));
	
					sbHtmlc4.append("<input type='checkbox' onclick=\"toggle('hist')\">Show History<br/>");
					sbHtmlc4.append("<div id='hist' style=\"display: none;\">");
					sbHtmlc4.append("<table>");
					sbHtmlc4.append(title("<b>HISTORY</b>", "center", "0"));
					for (Report rpt : reports.gServiceYear(serviceYear, pubId))
					{
						sbHtmlc4.append(hours(rpt.getMonthText(), rpt.getYear(), rpt.getHoursWithRBC()));
					}
					sbHtmlc4.append("</table>");
					sbHtmlc4.append("</div>");
					htmlc4 = sbHtmlc4.toString();
				}
	
				boolean alt = true;
				for (String value : missing) 
				{
					Date d = parse.parse(value + "-1");
					String monthName = format.format(d);
					String key = formatkey.format(d);
					sbAuth.append(key).append(",");
	
					if (alt)
					{
						sbHtmlc1.append(table(userName, monthName, key, partial));
					}
					else
					{
						sbHtmlc2.append(table(userName, monthName, key, partial));
					}
					alt = !alt;
				}
	
				if (missing.size() == 0)
				{
					sbHtmlc1.append(userName).append(" - You are up-to-date.  Thanks!");
					stylesave = " display: none;";
				}
				stylecol2 = missing.size() <= 1 ? " display: none;" : "";
	
				htmlc1 = sbHtmlc1.toString();
				htmlc2 = sbHtmlc2.toString();
				auth = EncryptUtil.encrypt(sbAuth.toString());
			}
			else
			{
				logMessages = "Sorry, this isn't working. Please contact your secretary to submit or change your report.";
			}
		}
		catch (Exception e) 
		{
			logMessages = e.getMessage();
		}
	}
	else
	{
		logMessages = "Sorry, this isn't working. Please contact your secretary to submit or change your report.";
	}
%>

<%!// start
	private static String needMsg(int inServiceYear, int inCount, double inHoursNeed)
	{
		StringBuilder msg = new StringBuilder();
		msg.append("<p style='width: 400px;'>");
		msg.append("Congrats on your pioneer activity!  You have submitted !count! ");
		msg.append("report(s) for the !serviceYear!-!serviceYearEnd! service year.  ");
		msg.append("For the remaining <b>!remain!</b> month(s), you need <b>!need!</b> hour(s) each month!</p>");
		msg.append("<p style='width: 400px;'><em>");
		msg.append("\"Remembering that pioneering is about glorifying Jehovah's name will keep us encouraged and humbled about our ministry.\"");
		msg.append("</em></p>");

		String serviceYearEnd = (inServiceYear + 1) + "";
		String remain = (12 - inCount) + "";
		String count = inCount + "";
		String need = inHoursNeed + "";
		String serviceYear = inServiceYear + "";

		String ret = msg.toString();
		ret = ret.replaceAll("!remain!", remain);
		ret = ret.replaceAll("!count!", count);
		ret = ret.replaceAll("!serviceYear!", serviceYear);
		ret = ret.replaceAll("!serviceYearEnd!", serviceYearEnd);
		ret = ret.replaceAll("!need!", need);

		return ret;
	}

	private static String hours(String inMonth, Integer inYear, Integer inHours)
	{
		StringBuilder ret = new StringBuilder();
		ret.append("<tr>");
		ret.append("<td>");
		ret.append(inMonth).append(" ").append(inYear);
		ret.append("</td>");

		ret.append("<td align='right'>");
		ret.append(inHours);
		ret.append("</td>");
		ret.append("</tr>");
		return ret.toString();
	}
	private static String table(String inName, String inMonthName, String inYM, boolean inPartial) 
	{
		StringBuilder ret = new StringBuilder();
		ret.append("<table>");
		ret.append("<tbody>");
		ret.append(title("<b>FIELD SERVICE REPORT</b>", "center", "0"));
		ret.append(title("<b>Name:</b> " + inName, "left", "30"));
		ret.append(title("<b>Month:</b><span style=\"color: blue;\"> " + inMonthName + "</span>", "left", "30"));
		ret.append("<tr>");
		ret.append("<td>Placements (Printed and Electronic)</td>");
		ret.append(input("place-", inYM));
		ret.append("</tr>");
		ret.append("<tr>");
		ret.append("<td>Video Showings</td>");
		ret.append(input("video-", inYM));
		ret.append("</tr>");
		ret.append("<tr>");
		ret.append("<td>Hours").append(ispartial("ispartial-", inYM, inPartial)).append("</td>");
		ret.append(hours("hour-", inYM, inPartial));
		ret.append("</tr>");
		ret.append("<tr>");
		ret.append("<td>Return Visits</td>");
		ret.append(input("rv-", inYM));
		ret.append("</tr>");
		ret.append("<tr>");
		ret.append("<td>Number of <em>Different</em> Bible Studies Conducted</td>");
		ret.append(input("study-", inYM));
		ret.append("</tr>");
		ret.append("<tr>");
		ret.append(comment("comment-", inYM));
		ret.append("</tr>");
		ret.append("<tr>");
		ret.append("<td colspan=\"2\"><hr /></td>");
		ret.append("</tr>");
		ret.append("</tbody>");
		ret.append("</table>");
		return ret.toString();
	}

	private static String title(String inTitle, String inAlign, String inPadLeft)
	{
		return "<tr><td colspan=\"2\" style=\"padding-left: " + inPadLeft + "px ; text-align: " + inAlign + ";\">" + inTitle + "</td></tr>";
	}

	private static String ispartial(String inName, String inYM, boolean inPartial) 
	{
		return inPartial
				? "<span style=\"font-size: smaller;\"> (<input name=\"" + (inName+inYM) + "\" type=\"checkbox\" onclick=\"myFunction(this, '" + inYM + "')\" />in 15 min increments)</span>"
				: "";
	}

	private static String partial(String inName, String inYM) 
	{
		return "<select style=\"display: none;\" id=\"" + (inName+inYM) + "\" name=\"" + (inName+inYM)
				+ "\"><option value=\"I_15\">15 min</option><option value=\"I_30\">30 min</option><option value=\"I_45\">45 min</option></select>";
	}

	private static String comment(String inName, String inYM)
	{
		return "<td colspan=\"2\">Comments<input name=\"" + (inName+inYM)
				+ "\" maxlength=\"350\" type=\"text\" style=\"width: 100%; text-align: left; \"/></td>";
	}

	private static String input(String inName, String inYM) 
	{
		return hours(inName, inYM, false);
	}

	private static String hours(String inName, String inYM, boolean inPartial) 
	{
		return "<td width=\"85px\"><input id=\"" + (inName+inYM) + "\" name=\"" + (inName+inYM)
				+ "\" maxlength=\"4\" type=\"number\" min=\"0\" max=\"9999\" style=\"width: 50px; text-align: right; \"/>"
				+ (inPartial ? partial("partial-", inYM) : "") + "</td>";
	}
	// end%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	function myFunction(inElement, inName) 
	{
		if (inElement.checked) 
		{
			document.getElementById("hour-" + inName).style.display = "none";
			document.getElementById("hour-" + inName).value = "";
			document.getElementById("partial-" + inName).style.display = "block";
		} 
		else 
		{
			document.getElementById("hour-" + inName).style.display = "block";
			document.getElementById("partial-" + inName).style.display = "none";
		}
	}
	function toggle(inDiv) 
	{
		var x = document.getElementById(inDiv);
		if (x.style.display === 'none') 
		{
			x.style.display = 'block';
		} 
		else 
		{
			x.style.display = 'none';
		}
	}
</script>
</head>
<body style="font-family: Trebuchet MS, Arial, sans-serif; font-size: smaller; background-color: #F7F6F3;">
	<h1>Online Ministry Report Submission</h1>
	<form action="submitreport" method="post">
		<input type="hidden" name="auth" value="<%=auth%>" />
		<%
			out.println(htmlc3);
		%>
		<table border="1" cellpadding="15">
			<tr>
				<td style="vertical-align: top;">
					<%
						out.println(htmlc1);
					%>
				</td>
				<td style="vertical-align: top;<%=stylecol2%>">
					<%
						out.println(htmlc2);
					%>
				</td>
			</tr>
		</table>
		<br /> <input style="width: 60px;<%=stylesave%>" type="submit" value="Save"><br /><br /> 
		<%
			out.println(htmlc4);
		%>
	</form>
	<br /><%=logMessages%><br />
</body>
</html>