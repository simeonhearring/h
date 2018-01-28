package h.model.shared.khall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h.model.shared.khall.Person.Status;
import h.model.shared.khall.Report.PubRange;
import h.model.shared.khall.Report.Stat;
import h.model.shared.khall.Report.Total;
import h.model.shared.util.NumberUtil;

@SuppressWarnings("serial")
public class Reports implements Serializable
{
  private Map<Long, List<Report>> mReports; // key=mPubId

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

  public static Total gTotal(List<Report> inReports)
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

  // for service year
  public List<Report> gReports(int inCongId, Long inPubId, int inYear, int inMonth)
  {
    List<Report> ret = new ArrayList<>();

    int year = YearMonthRange.yearOfServiceYear(inYear, inMonth);

    int[] months =
    {
        9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8
    };
    for (int month : months)
    {
      if (month == 1)
      {
        year++;
      }
      ret.add(find(inCongId, inPubId, year, month));
    }

    return ret;
  }

  private Report find(Integer inCongId, Long inPubId, int inYear, int inMonth)
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

  public Map<String, Report> gReportByMonth(long inPubId)
  {
    Map<String, Report> ret = new HashMap<>();
    for (Report value : mReports.get(inPubId))
    {
      ret.put(value.getKey(), value);
    }
    return ret;
  }

  public Status getStatus(long inPubId, int inFromYear, int inFromMonth, int inToYear,
      int inToMonth)
  {
    // SHOULD NOT BE IR WHEN START EXISTS?
    Status ret = Status.RE;

    List<String> range = getRange(inFromYear, inFromMonth, inToYear, inToMonth);

    int last = range.size();
    int lastminussix = last - 6;

    Map<String, Report> table = gReportByMonth(inPubId);

    if (lastminussix >= 1)
    {
      Report.Total reports = new Report.Total();
      for (String value : range.subList(lastminussix, last))
      {
        reports.add(table.get(value));
      }

      if (reports.getActiveCount() != 6)
      {
        ret = reports.getActiveCount() == 0 ? Status.IA : Status.IR;
      }
    }

    return ret;
  }

  public static List<String> getRange(int inFromYear, int inFromMonth, int inToYear, int inToMonth)
  {
    List<String> ret = new ArrayList<>();

    for (int y = inFromYear; y <= inToYear; y++)
    {
      if (y != inToYear)
      {
        for (int m = inFromMonth; m <= 12; m++)
        {
          ret.add(y + "-" + m);
        }
        inFromMonth = 1;
      }
      else
      {
        for (int m = inFromMonth; m <= inToMonth; m++)
        {
          ret.add(y + "-" + m);
        }
      }
    }

    return ret;
  }

  public Report find(String inKey, Map<String, Report> inReports)
  {
    Report ret = inReports.get(inKey);

    if (ret == null)
    {
      ret = new Report();
      String[] ym = inKey.split("-");
      ret.setYear(NumberUtil.toInt(ym[0], 0));
      ret.setMonth(NumberUtil.toInt(ym[1], 0));
    }

    return ret;
  }

  public List<Report> find(Long inPubId, List<YrMo> inPast)
  {
    List<Report> ret = new ArrayList<>();
    for (YrMo value : inPast)
    {
      ret.add(find(null, inPubId, value.getYear(), value.getMonth()));
    }
    return ret;
  }

  @Deprecated
  public PubRange gPubRange(Long inPubId, List<YrMo> inPast)
  {
    // PubRange ret = new PubRange();
    // ret.setSize(inPast.size());
    // ret.setPubCount(1);
    //
    // for (int i = 0; i < inPast.size(); i++)
    // {
    // YrMo value = inPast.get(i);
    // Report report = find(null, inPubId, value.getYear(), value.getMonth());
    // ret.setValue(i, report);
    // }
    // return ret;

    List<Long> pubId = new ArrayList<>();
    pubId.add(inPubId);

    return gPubRange(pubId, inPast);
  }

  public PubRange gPubRange(List<Long> inPubId, List<YrMo> inPast)
  {
    PubRange ret = new PubRange();
    ret.setSize(inPast.size());
    ret.setPubCount(inPubId.size());

    for (int i = 0; i < inPast.size(); i++)
    {
      YrMo value = inPast.get(i);
      for (Long id : inPubId)
      {
        Report report = find(null, id, value.getYear(), value.getMonth());
        ret.addValue(i, report);
      }
    }

    return ret;
  }

  public Stat gStat(List<YrMo> inYms, List<Person> inPublishers, int inLength, double inThreshold)
  {
    Stat ret = new Stat();
    ret.setSize(inLength);

    for (Person value : inPublishers)
    {
      List<Report> list = find(value.getIdLong(), inYms);

      for (int i = 0; i < inLength; i++)
      {
        YrMo ym = inYms.get(i);

        int fromIndex = i;
        int toIndex = i + 6;

        Total total = gTotal(list.subList(fromIndex, toIndex));

        if (total.isInactive())
        {
          ret.inactive(ym, i, value.getIdLong());
        }
        else
        {
          if (total.isIrregular())
          {
            ret.irregular(ym, i, value.getIdLong());
          }
          if (total.isBelowThreshold(list.get(i), inThreshold))
          {
            ret.belowThreshold(ym, i, value.getIdLong());
          }
          if (total.isAboveThreshold(list.get(i), inThreshold))
          {
            ret.aboveThreshold(ym, i, value.getIdLong());
          }
          if (total.isReactivated())
          {
            ret.reactivated(ym, i, value.getIdLong());
          }
        }
      }
    }

    return ret;
  }
}