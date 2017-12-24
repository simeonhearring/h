package h.khall.client.model;

import h.khall.shared.command.ReportSaveCommand;
import h.model.shared.Tag;
import h.model.shared.khall.Charts;
import h.model.shared.khall.Part;
import h.model.shared.khall.Report;
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
    inReport.setPlacements(mDisplay.getPlacement().getValue());
    inReport.setVideoShowings(mDisplay.getVideo().getValue());
    inReport.setHours(mDisplay.getHour().getValue());
    inReport.setReturnVisits(mDisplay.getRv().getValue());
    inReport.setBibleStudies(mDisplay.getStudy().getValue());
    inReport.setRemarks(mDisplay.getComment().getValue());
  }

  private void display(Report inReport)
  {
    mDisplay.getPlacement().setValue(inReport.getPlacements());
    mDisplay.getVideo().setValue(inReport.getVideoShowings());
    mDisplay.getHour().setValue(inReport.getHours());
    mDisplay.getRv().setValue(inReport.getReturnVisits());
    mDisplay.getStudy().setValue(inReport.getBibleStudies());
    mDisplay.getComment().setValue(inReport.getRemarks());
  }

  public interface Display extends h.style.g.client.model.Display
  {
    InputDisplay<String> getMonth();

    InputDisplay<Integer> getPlacement();

    InputDisplay<Integer> getVideo();

    InputDisplay<Integer> getHour();

    InputDisplay<Integer> getRv();

    InputDisplay<Integer> getStudy();

    InputDisplay<String> getComment();

    InputDisplay<String> getNoActivity();

    InputDisplay<String> getSend();

    InputDisplay<String> getPartial();

    InputDisplay<Integer> getCredit();

    InputDisplay<String> getInclude();

    InputDisplay<String> getPioneer();

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

    display(gReport(mPubId, 2018, 1));
  }

  public void save()
  {
    Report report = gReport(mPubId, 2018, 1);
    report(report);
    fire(new ReportSaveCommand(report));
  }

  private Report gReport(long inPubId, int inYear, int inMonth)
  {
    return mClient.getReports().gReport(mProfile.getCongId(), inPubId, inYear, inMonth);
  }
}