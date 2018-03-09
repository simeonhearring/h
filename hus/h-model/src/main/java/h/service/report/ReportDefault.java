package h.service.report;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import h.spring.SpringBean;

public class ReportDefault<B> extends SpringBean implements Report<B>
{
  @Override
  public Map<String, Object> getParameters(Map<String, String> inParameters)
  {
    Map<String, Object> ret = new HashMap<>();

    for (Entry<String, String> value : inParameters.entrySet())
    {
      ret.put(value.getKey(), value.getValue());
    }

    return ret;
  }

  @Override
  public Collection<B> getCollection(Map<String, Object> inParameters)
  {
    return null;
  }

  @Override
  public final String getLocation(String inReportName)
  {
    try
    {
      return getLocation("classpath:reports/", inReportName);
    }
    catch (IOException e)
    {
      throw new RuntimeException("Error finding report", e);
    }
  }

  public String getLocation(String inPath, String inReportName) throws IOException
  {
    return getApplicationContext().getResource(inPath + inReportName).getURL().getPath();
  }
}