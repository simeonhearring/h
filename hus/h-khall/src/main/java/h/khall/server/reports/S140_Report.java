package h.khall.server.reports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h.model.shared.ReportBean;

public class S140_Report extends AbstractReportDefault<ReportBean>
{
  @Override
  public Map<String, Object> getParameters(Map<String, String> inParameters)
  {
    Map<String, Object> ret = new HashMap<>();
    return ret;
  }

  @Override
  public Collection<ReportBean> getCollection(Map<String, Object> inParameters)
  {
    List<ReportBean> ret = new ArrayList<>();
    ret.add(new ReportBean());
    return ret;
  }
}