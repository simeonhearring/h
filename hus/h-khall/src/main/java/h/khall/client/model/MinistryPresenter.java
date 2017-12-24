package h.khall.client.model;

import java.util.Date;

import com.google.gwt.user.client.TakesValue;

import h.khall.shared.command.ReportSaveCommand;
import h.model.shared.Tag;
import h.model.shared.khall.Charts;
import h.model.shared.khall.Part;
import h.model.shared.khall.Report;
import h.model.shared.khall.Roles.Role;
import h.model.shared.util.TimeUtil;
import h.style.g.client.model.CallBack;
import h.style.g.client.model.InputDisplay;
import h.style.g.client.ui.event.RefreshEvent;
import h.style.g.shared.chart.Chart;
import h.style.g.shared.chart.Chart.Dataset;
import h.style.g.shared.chart.Chart.Stat;

public class MinistryPresenter extends AbstractPresenter<MinistryPresenter.Display>
  implements RefreshEvent.Handler, CallBack<Tag>
{
  private enum V
  {
    Assignments,
    Assigned;
  }

  private Long mPubId;

  public MinistryPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mDisplay.setCallback(this);
  }

  public MinistryPresenter handlers()
  {
    register(addHandler(RefreshEvent.TYPE, this));
    return this;
  }

  @Override
  public void dispatch(RefreshEvent inEvent)
  {
  }

  private void report(Report inReport)
  {
    inReport.setPlacements(noZero(mDisplay.getPlacement().getValue()));
    inReport.setVideoShowings(noZero(mDisplay.getVideo().getValue()));
    inReport.setHours(noZero(mDisplay.getHour().getValue()));
    inReport.setReturnVisits(noZero(mDisplay.getRv().getValue()));
    inReport.setBibleStudies(noZero(mDisplay.getStudy().getValue()));
    inReport.setCreditHours(noZero(mDisplay.getCredit().getValue()));
    inReport.setRemarks(mDisplay.getComment().getValue());
    inReport.setPartialHours(mDisplay.getPartial().getValue());
    inReport.setNoActivity(mDisplay.getNoActivity().getValue());
    inReport.setIncludeAllHours(mDisplay.getInclude().getValue());
    inReport.setSendDate(mDisplay.getSend().getValue());
    inReport.setType(mDisplay.getPioneer().getValue());
  }

  private static Integer noZero(Integer inValue)
  {
    return inValue != null && inValue.equals(0) ? null : inValue;
  }

  private void display(Report inReport)
  {
    mDisplay.getPlacement().setValue(inReport.getPlacements());
    mDisplay.getVideo().setValue(inReport.getVideoShowings());
    mDisplay.getHour().setValue(inReport.getHours());
    mDisplay.getRv().setValue(inReport.getReturnVisits());
    mDisplay.getStudy().setValue(inReport.getBibleStudies());
    mDisplay.getCredit().setValue(inReport.getCreditHours());
    mDisplay.getComment().setValue(inReport.getRemarks());
    mDisplay.getPartial().setValue(inReport.getPartialHours());
    mDisplay.getNoActivity().setValue(inReport.getNoActivity());
    mDisplay.getInclude().setValue(inReport.getIncludeAllHours());
    mDisplay.getSend().setValue(inReport.gSendDate());
    mDisplay.getPioneer().setValue(inReport.getType());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    TakesValue<Date> getMonth();

    InputDisplay<Integer> getPlacement();

    InputDisplay<Integer> getVideo();

    InputDisplay<Integer> getHour();

    InputDisplay<Integer> getRv();

    InputDisplay<Integer> getStudy();

    InputDisplay<String> getComment();

    TakesValue<Boolean> getInclude();

    TakesValue<Boolean> getNoActivity();

    TakesValue<Date> getSend();

    TakesValue<Double> getPartial();

    InputDisplay<Integer> getCredit();

    TakesValue<Role> getPioneer();

    void setCallback(CallBack<Tag> inCallBack);
  }

  private static Chart chart()
  {
    Chart ret = new Chart(Chart.Type.LINE);

    Stat stat = new Stat();
    stat.setHead("Assignment Summary");
    stat.setSubHead("Student Parts");
    stat.setTopRight(null);
    stat.setFooter(Part.labels(true, " ", Part.student()));
    ret.setStat(stat);

    ret.setDataType(Charts.REPORT);

    ret.setResponsive(true);

    // ret.addLabel(sMonthNames[0], sMonthNames[1], sMonthNames[2]);

    format(ret.createDataset(V.Assignments.name(), 0.0, 0.0, 0.0), 254);
    format(ret.createDataset(V.Assigned.name(), 0.0, 0.0, 0.0), 352);

    return ret;
  }

  private static void format(Dataset inSet, int inColor)
  {
    // http://standardista.com/webkit/ch7/hsla.html
    String c1 = "hsla(" + inColor + ",67%,51%,";
    inSet.setBorderColor(c1 + "0." + 9 + ")");
    // inSet.setBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    // inSet.setPointBackgroundColor(c1 + "0." + RandomUtil.randomInt(9) + ")");
    inSet.setPointBorderColor("#fff");
  }

  @Override
  public void onCallBack(Tag inResult)
  {
    mPubId = inResult != null ? Long.valueOf(inResult.getId()) : null;
    display(gReport(mPubId, ym()));
  }

  public void changeMonth()
  {
    if (mPubId != null)
    {
      display(gReport(mPubId, ym()));
    }
    else
    {
      mDisplay.notify("Select publisher");
    }
  }

  @SuppressWarnings("deprecation")
  private int[] ym()
  {
    int year = TimeUtil.currentYear();
    int month = TimeUtil.currentServiceMonth();
    Date d = mDisplay.getMonth().getValue();
    if (d != null)
    {
      year = d.getYear() + 1900;
      month = d.getMonth() + 1;
      mDisplay.getMonth().setValue(TimeUtil.getFirstOfMonth(year, month));
    }
    return new int[]
    {
        year, month
    };
  }

  public void save()
  {
    Report report = gReport(mPubId, ym());
    report(report);
    fire(new ReportSaveCommand(report));
  }

  private Report gReport(long inPubId, int[] inYearMonth)
  {
    return mClient.getReports().gReport(mProfile.getCongId(), inPubId, inYearMonth[0], inYearMonth[1]);
  }
}