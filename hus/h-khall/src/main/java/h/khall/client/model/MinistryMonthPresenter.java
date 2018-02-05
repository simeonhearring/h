package h.khall.client.model;

import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.HasText;

import h.khall.shared.command.ReportSaveCommand;
import h.model.shared.khall.Profile.Security;
import h.model.shared.khall.Report;
import h.model.shared.khall.Roles.Role;
import h.style.g.client.model.InputDisplay;

public class MinistryMonthPresenter extends AbstractPresenter<MinistryMonthPresenter.Display>
  implements ChangeHandler
{
  private Long mPubId;
  private int[] mYearMonth;
  private boolean mDirty;

  public MinistryMonthPresenter(Display inDisplay)
  {
    initDisplay(inDisplay);
    mDisplay.editReport(isEdit(Security.REPORT));
  }

  public MinistryMonthPresenter handlers()
  {
    return this;
  }

  public void report(Report inReport)
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
    inReport.setType(mDisplay.getPioneer().getValue());
    inReport.setSendDate(firstOfMonth(mDisplay.getSend().getValue()));
  }

  private Date firstOfMonth(Date inValue)
  {
    if (inValue == null)
    {
      return null;
    }
    Date ret = mDisplay.parse("yyyy-MM-dd", mDisplay.format("yyyy-MM", inValue) + "-01");
    return ret;
  }

  private static Integer noZero(Integer inValue)
  {
    return inValue != null && inValue.equals(0) ? null : inValue;
  }

  private static Date checkPubId(Long inPubId, Date inSendDate)
  {
    return inPubId == null ? null : inSendDate;
  }

  public void display(Report inReport)
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
    mDisplay.getPioneer().setValue(inReport.getType());
    mDisplay.getSend().setValue(checkPubId(inReport.getPubId(), inReport.gSendDate()));
    mDisplay.getMonth().setText(mDisplay.format("MMM yy", inReport.gDate()));
    mDisplay.getName().setText(mClient.gName(inReport.getPubId()));
    mDirty = false;
  }

  public interface Display extends h.style.g.client.model.Display
  {
    InputDisplay<Integer> getPlacement();

    void editReport(boolean inEdit);

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

    HasText getMonth();

    void report(Report inReport);

    void display(Report inReport);

    void changePub(Long inPubId, int[] inYearMonth);

    void changeMonth(int[] inYearMonth);

    void save();

    boolean isDirty();

    int[] getYearMonth();

    void setYearMonth(int... inYearMonth);

    void setDirty(boolean inDirty);

    HasText getName();
  }

  public void changePub(Long inPubId, int[] inYearMonth)
  {
    mPubId = inPubId;
    mYearMonth = inYearMonth;
    display(gReport());
  }

  public void changeMonth(int[] inYearMonth)
  {
    mYearMonth = inYearMonth;
    if (mPubId != null)
    {
      display(gReport());
    }
    else
    {
      mDisplay.notify("Select publisher");
    }
  }

  public void save()
  {
    if (mPubId != null)
    {
      Report report = gReport();
      report(report);
      fire(new ReportSaveCommand(report));
    }
  }

  private Report gReport()
  {
    return mClient.getReports().gReport(mProfile.getCongId(), mPubId, mYearMonth[0], mYearMonth[1]);
  }

  @Override
  public void onChange(ChangeEvent inEvent)
  {
    mDirty = true;
  }

  public void setDirty(boolean inDirty)
  {
    mDirty = inDirty;
  }

  public boolean isDirty()
  {
    return mDirty;
  }

  public int[] getYearMonth()
  {
    return mYearMonth;
  }

  public void setYearMonth(int... inYearMonth)
  {
    mYearMonth = inYearMonth;
  }
}