package h.service.report;

import java.util.Collection;
import java.util.Map;

public interface ReportRunner
{
  byte[] runReportToPdf(String inReport, Map<String, Object> inParameters,
      Collection<?> inCollection);

  byte[] runReportToPdf(String inReport, Map<String, Object> inParameters);

  byte[] runReportToXls(String inReport, Map<String, Object> inParameters);

  byte[] runReportToXls(String inReport, Map<String, Object> inParameters,
      Collection<?> inCollection);

  byte[] runReportToCsv(String inReport, Map<String, Object> inParameters);

  byte[] runReportToCsv(String inReport, Map<String, Object> inParameters,
      Collection<?> inCollection);

  byte[] runReportToHtml(String inReport, Map<String, Object> inParameters);

  byte[] runReportToHtml(String inReport, Map<String, Object> inParameters,
      Collection<?> inCollection);
}