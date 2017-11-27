package h.khall.server.reports;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import h.khall.server.dao.Dao;
import h.service.report.ReportDefault;

public abstract class AbstractReportDefault<B> extends ReportDefault<B>
{
  public Dao getDao()
  {
    return getApplicationContext().getBean("Dao", Dao.class);
  }

  protected Map<String, Object> objectMap(Map<String, String> inMap)
  {
    Map<String, Object> ret = new HashMap<>();
    for (Entry<String, String> value : inMap.entrySet())
    {
      ret.put(value.getKey(), value.getValue());
    }
    return ret;
  }
}