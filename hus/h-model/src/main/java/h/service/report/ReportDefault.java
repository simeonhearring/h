package h.service.report;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ReportDefault<B> implements Report<B>, ApplicationContextAware
{
  private ApplicationContext mApplicationContext;

  @Override
  public Map<String, Object> getParameters(Map<String, String> inParameters)
  {
    Map<String, Object> ret = new HashMap<String, Object>();

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
    return mApplicationContext.getResource(inPath + inReportName).getURL().getPath();
  }

  @Override
  public final void setApplicationContext(ApplicationContext inApplicationContext)
      throws BeansException
  {
    mApplicationContext = inApplicationContext;
  }

  public ApplicationContext getApplicationContext()
  {
    return mApplicationContext;
  }
}
