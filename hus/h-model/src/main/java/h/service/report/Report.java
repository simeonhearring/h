package h.service.report;

import java.util.Collection;
import java.util.Map;

public interface Report<B>
{
  String getLocation(String inReportName);

  Collection<B> getCollection(Map<String, Object> inParameters);

  Map<String, Object> getParameters(Map<String, String> inParameters);
}