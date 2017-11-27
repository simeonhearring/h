package h.service.report;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.Exporter;

public class JasperReportRunner implements ReportRunner
{
  private DataSource mDataSource;

  public void setDataSource(DataSource inDataSource)
  {
    mDataSource = inDataSource;
  }

  @Override
  public byte[] runReportToPdf(String inReport, Map<String, Object> inParameters,
      Collection<?> inCollection)
  {
    try
    {
      JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(inCollection);
      return JasperRunManager.runReportToPdf(inReport, inParameters, jrDataSource);
    }
    catch (JRException e)
    {
      throw new RuntimeException("Error running report to PDF with collection", e);
    }
  }

  @Override
  public byte[] runReportToPdf(String inReport, Map<String, Object> inParameters)
  {
    Connection connection = null;
    try
    {
      connection = mDataSource.getConnection();
      return JasperRunManager.runReportToPdf(inReport, inParameters, connection);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Error running report to PDF with connection", e);
    }
    finally
    {
      close(connection);
    }
  }

  @Override
  public byte[] runReportToXls(String inReport, Map<String, Object> inParameters)
  {
    Connection connection = null;
    try
    {
      connection = mDataSource.getConnection();
      JasperPrint print = JasperFillManager.fillReport(inReport, inParameters, connection);
      return runReport(print, new JRXlsExporter());
    }
    catch (Exception e)
    {
      throw new RuntimeException("Error running report to PDF with connection", e);
    }
    finally
    {
      close(connection);
    }
  }

  @Override
  public byte[] runReportToXls(String inReport, Map<String, Object> inParameters,
      Collection<?> inCollection)
  {
    try
    {
      JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(inCollection);
      JasperPrint print = JasperFillManager.fillReport(inReport, inParameters, jrDataSource);
      return runReport(print, new JRXlsExporter());
    }
    catch (JRException e)
    {
      throw new RuntimeException("Error running report to PDF with collection", e);
    }
  }

  @Override
  public byte[] runReportToCsv(String inReport, Map<String, Object> inParameters)
  {
    Connection connection = null;
    try
    {
      connection = mDataSource.getConnection();
      JasperPrint print = JasperFillManager.fillReport(inReport, inParameters, connection);
      return runReport(print, new JRCsvExporter());
    }
    catch (Exception e)
    {
      throw new RuntimeException("Error running report to PDF with connection", e);
    }
    finally
    {
      close(connection);
    }
  }

  @Override
  public byte[] runReportToCsv(String inReport, Map<String, Object> inParameters,
      Collection<?> inCollection)
  {
    try
    {
      JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(inCollection);
      JasperPrint print = JasperFillManager.fillReport(inReport, inParameters, jrDataSource);
      return runReport(print, new JRCsvExporter());
    }
    catch (JRException e)
    {
      throw new RuntimeException("Error running report to PDF with collection", e);
    }
  }

  private byte[] runReport(JasperPrint inPrint, Exporter inExport) throws JRException
  {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    // inExport.setParameter(JRXlsExporterParameter.JASPER_PRINT, inPrint);
    // inExport.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
    // inExport.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
    // Boolean.FALSE);
    // inExport.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
    // Boolean.TRUE);
    // inExport.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
    // Boolean.FALSE);
    // inExport.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
    // Boolean.TRUE);
    // inExport.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
    // Boolean.TRUE);
    // if (inExport instanceof HtmlExporter)
    // {
    // inExport.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
    // false);
    // }
    inExport.exportReport();
    return output.toByteArray();
  }

  private void close(Connection inConnection)
  {
    try
    {
      inConnection.close();
    }
    catch (Exception e)
    {
      // do nothing
    }
  }

  @Override
  public byte[] runReportToHtml(String inReport, Map<String, Object> inParameters)
  {
    Connection connection = null;
    try
    {
      connection = mDataSource.getConnection();
      JasperPrint print = JasperFillManager.fillReport(inReport, inParameters, connection);
      return runReport(print, new HtmlExporter());
    }
    catch (Exception e)
    {
      throw new RuntimeException("Error running report to HTML with connection", e);
    }
    finally
    {
      close(connection);
    }
  }

  @Override
  public byte[] runReportToHtml(String inReport, Map<String, Object> inParameters,
      Collection<?> inCollection)
  {
    try
    {
      JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(inCollection);
      JasperPrint print = JasperFillManager.fillReport(inReport, inParameters, jrDataSource);
      return runReport(print, new HtmlExporter());
    }
    catch (JRException e)
    {
      throw new RuntimeException("Error running report to HTML with collection", e);
    }
  }
}