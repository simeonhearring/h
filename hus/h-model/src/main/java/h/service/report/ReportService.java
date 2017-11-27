package h.service.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import h.model.shared.util.EncryptUtil;
import h.service.http.Service;
import h.util.DecryptUtil;
import h.util.HostUtil;

import net.sf.jasperreports.engine.JRParameter;

public class ReportService implements ApplicationContextAware, Service
{
  private static final Logger LOGGER = Logger.getLogger(ReportService.class);

  private ApplicationContext mApplicationContext;

  private ReportRunner mReportRunner;

  @Override
  public void execute(Map<String, String> inParams, HttpServletResponse inResponse)
  {
    LOGGER.info("execute ... [" + inParams + "]");

    String reportname = null;
    try
    {
      Map<String, String> params =
          HostUtil.getParameters(DecryptUtil.decrypt(EncryptUtil.GWT_DES_KEY,
              inParams.get("params")));

      long diff = System.currentTimeMillis() - Long.valueOf(params.get("date"));
      params.remove("date");

      LOGGER.info("starting running report " + diff);

      if (diff < 30000)
      {
        reportname = params.get("rptnme");
        params.remove("rptnme");

        ReportType type = getReportType(params);
        byte[] b = generate(reportname, params, type);

        inResponse.setHeader("Content-Disposition", "inline; filename=report." + type.name());
        inResponse.setContentType("application/" + type.name());
        inResponse.setContentLength(b.length);

        ServletOutputStream stream = inResponse.getOutputStream();

        stream.write(b, 0, b.length);
        stream.flush();
        stream.close();

        LOGGER.info("completed running report " + reportname);
      }
    }
    catch (Exception e)
    {
      LOGGER.error("error running report " + reportname, e);

      StringWriter stringWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(stringWriter);
      e.printStackTrace(printWriter);
      inResponse.setContentType("text/plain");
      try
      {
        inResponse.getOutputStream().print(stringWriter.toString());
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
  }

  public byte[] generate(String inReportName, Map<String, String> inParameters,
      ReportType inReportType)
  {
    Report<?> report = getReport(inReportName);

    String location = report.getLocation(inReportName);

    Map<String, Object> parameters = report.getParameters(inParameters);
    checkForLocale(parameters, inParameters);

    Collection<?> collection = report.getCollection(parameters);

    return type(inReportType, location, parameters, collection);
  }

  private void checkForLocale(Map<String, Object> inNew, Map<String, String> inExist)
  {
    if (inExist.containsKey("locale"))
    {
      Locale locale = new Locale(inExist.get("locale"));
      inNew.put(JRParameter.REPORT_LOCALE, locale);
    }
  }

  protected Report<?> getReport(String inReportName)
  {
    String name = inReportName.replaceAll(".jasper", "");

    if (mApplicationContext.containsBean(name))
    {
      return mApplicationContext.getBean(name, Report.class);
    }
    else
    {
      return mApplicationContext.getBean("ReportDefault", Report.class);
    }
  }

  private byte[] pdf(String inReport, Map<String, Object> inParameters, Collection<?> inCollection)
  {
    if (inCollection == null)
    {
      return mReportRunner.runReportToPdf(inReport, inParameters);
    }
    else
    {
      return mReportRunner.runReportToPdf(inReport, inParameters, inCollection);
    }
  }

  private byte[] xls(String inReport, Map<String, Object> inParameters, Collection<?> inCollection)
  {
    if (inCollection == null)
    {
      return mReportRunner.runReportToXls(inReport, inParameters);
    }
    else
    {
      return mReportRunner.runReportToXls(inReport, inParameters, inCollection);
    }
  }

  private byte[] csv(String inReport, Map<String, Object> inParameters, Collection<?> inCollection)
  {
    if (inCollection == null)
    {
      return mReportRunner.runReportToCsv(inReport, inParameters);
    }
    else
    {
      return mReportRunner.runReportToCsv(inReport, inParameters, inCollection);
    }
  }

  private byte[] type(ReportType inType, String inReport, Map<String, Object> inParameters,
      Collection<?> inCollection)
  {
    LOGGER.info("running report " + inReport + " " + inType);

    switch (inType)
    {
      case pdf:
        return pdf(inReport, inParameters, inCollection);
      case xls:
        return xls(inReport, inParameters, inCollection);
      case csv:
        return csv(inReport, inParameters, inCollection);
      default:
        return pdf(inReport, inParameters, inCollection);
    }
  }

  private static ReportType getReportType(Map<String, String> inParams)
  {
    ReportType ret = ReportType.pdf;
    if (inParams.containsKey("pType"))
    {
      String type = inParams.get("pType");
      if ("xls".equals(type))
      {
        ret = ReportType.xls;
      }
      else if ("csv".equals(type))
      {
        ret = ReportType.csv;
      }
    }
    return ret;
  }

  public void setReportRunner(ReportRunner inReportRunner)
  {
    mReportRunner = inReportRunner;
  }

  @Override
  public void setApplicationContext(ApplicationContext inApplicationContext) throws BeansException
  {
    mApplicationContext = inApplicationContext;
  }
}
