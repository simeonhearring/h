package h.khall.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import h.khall.shared.command.ReportSaveCommand;
import h.model.shared.khall.Report;

public class MinistryYearPresenter extends AbstractPresenter<MinistryYearPresenter.Display>
{
  private Long mPubId;
  private int[] mYearMonth;

  private Map<Integer, MinistryMonthPresenter.Display> mMap;

  public MinistryYearPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mMap = new HashMap<>();
  }

  public MinistryYearPresenter handlers()
  {
    return this;
  }

  public interface Display extends h.style.g.client.model.Display
  {
  }

  public void changePub(Long inPubId, int[] inYearMonth)
  {
    mPubId = inPubId;
    mYearMonth = inYearMonth;
    addReports();
  }

  public void changeMonth(int[] inYearMonth)
  {
    mYearMonth = inYearMonth;
    if (mPubId != null)
    {
      addReports();
    }
    else
    {
      mDisplay.notify("Select publisher");
    }
  }

  public void put(int inMonth, MinistryMonthPresenter.Display inDisplay)
  {
    mMap.put(inMonth, inDisplay);
  }

  public void save()
  {
    List<Report> list = new ArrayList<>();
    for (MinistryMonthPresenter.Display value : mMap.values())
    {
      if (value.isDirty())
      {
        int[] ym = value.getYearMonth();
        Report report = mClient.getReports().gReport(mProfile.getCongId(), mPubId, ym[0], ym[1]);
        value.report(report);
        list.add(report);
        value.setDirty(false);
      }
    }

    if (list.size() > 0)
    {
      fire(new ReportSaveCommand(list));
    }
  }

  private void addReports()
  {
    for (Report value : gReports())
    {
      MinistryMonthPresenter.Display display = mMap.get(value.getMonth());
      display.display(value);
      display.setYearMonth(value.getYear(), value.getMonth());
    }
  }

  private List<Report> gReports()
  {
    return mClient.getReports().gReports(mProfile.getCongId(), mPubId, mYearMonth[0], mYearMonth[1]);
  }
}