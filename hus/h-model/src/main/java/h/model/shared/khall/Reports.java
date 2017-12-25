package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h.model.shared.khall.Report.Total;

@SuppressWarnings("serial")
public class Reports implements Serializable
{
  private Map<Long, List<Report>> mReports;

  public void addReports(List<Report> inReports)
  {
    mReports = new HashMap<>();
    for (Report value : inReports)
    {
      if (!mReports.containsKey(value.getPubId()))
      {
        mReports.put(value.getPubId(), new ArrayList<Report>());
      }
      mReports.get(value.getPubId()).add(value);
    }
  }

  public Total gTotal(List<Report> inReports)
  {
    Total ret = new Total();
    for (Report value : inReports)
    {
      ret.add(value);
    }
    return ret;
  }

  public List<Total> gAnnual(int inYear)
  {
    List<Total> ret = new ArrayList<>();
    return ret;
  }

  public Report gReport(int inCongId, Long inPubId, int inYear, int inMonth)
  {
    return find(inCongId, inPubId, inYear, inMonth);
  }

  private Report find(int inCongId, Long inPubId, int inYear, int inMonth)
  {
    Report ret = null;

    List<Report> list = null;

    if (inPubId != null && inPubId.intValue() != 0)
    {
      if (!mReports.containsKey(inPubId))
      {
        mReports.put(inPubId, new ArrayList<Report>());
      }

      list = mReports.get(inPubId);

      for (Report value : list)
      {
        if (value.getYear().equals(inYear) && value.getMonth().equals(inMonth))
        {
          ret = value;
          break;
        }
      }
    }

    if (ret == null)
    {
      ret = new Report();
      ret.setCongId(inCongId);
      ret.setPubId(inPubId);
      ret.setYear(inYear);
      ret.setMonth(inMonth);

      if (list != null)
      {
        list.add(ret);
      }
    }

    return ret;
  }
}