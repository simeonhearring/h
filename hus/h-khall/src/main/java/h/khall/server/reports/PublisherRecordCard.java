package h.khall.server.reports;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.shared.GwtIncompatible;

import h.model.shared.Person.Gender;
import h.model.shared.khall.Person;
import h.model.shared.khall.Report;
import h.model.shared.khall.Roles;
import h.model.shared.khall.Person.Status;
import h.model.shared.khall.Roles.Role;
import h.model.shared.util.NumberUtil;
import h.model.shared.util.StringUtil;

@SuppressWarnings("serial")
public class PublisherRecordCard implements Serializable
{
  private Person mPublisher;
  private List<Report> mReports;
  private Status mStatus;

  public void normalize()
  {
    mReports = new ArrayList<>();
  }

  public Person getPublisher()
  {
    return mPublisher;
  }

  public Long getPublisherId()
  {
    return mPublisher.getIdLong();
  }

  public void setPublisher(Person inPublisher)
  {
    mPublisher = inPublisher;
  }

  public List<Report> getReports()
  {
    return mReports;
  }

  public void setReports(List<Report> inReports)
  {
    mReports = inReports;
  }

  public String getName()
  {
    return mPublisher.getName();
  }

  @GwtIncompatible
  public String getBirth()
  {
    return format("MM/dd/yy", mPublisher.getBirth());
  }

  @GwtIncompatible
  public String getImmersed()
  {
    return format("MM/dd/yy", mPublisher.getBaptized());
  }

  public int getSortOrder()
  {
    int ret = 0;

    if (getPublisherId() < 0)
    {
      ret = 1;
    }
    else if (Status.IA.equals(mStatus))
    {
      ret = 5;
    }
    else if (mPublisher.isRegular())
    {
      ret = 2;
    }
    else if (mPublisher.isBaptized())
    {
      ret = 3;
    }
    else
    {
      ret = 4;
    }

    return ret;
  }

  @GwtIncompatible
  private String format(String inString, Date inDate)
  {
    if (inDate == null)
    {
      return "";
    }
    return new SimpleDateFormat(inString).format(inDate);
  }

  public String getAddress()
  {
    String email = mPublisher.getEmail();
    return mPublisher.getAddress() + StringUtil.ensure(email, " eMail: ");
  }

  public String getPhone()
  {
    return mPublisher.getHome();
  }

  public String getMobile()
  {
    return mPublisher.getMobile();
  }

  public String getGender()
  {
    return mPublisher.gGenderName();
  }

  public boolean isMale()
  {
    return Gender.Male.equals(mPublisher.getGender());
  }

  public boolean isElder()
  {
    return mPublisher.isElder();
  }

  public boolean isServant()
  {
    return mPublisher.isServant();
  }

  public boolean isPioneer()
  {
    return mPublisher.isRegular();
  }

  public boolean isAnoited()
  {
    return mPublisher.isAnoited();
  }

  public String getComments()
  {
    String ret = null;

    StringBuilder sb = new StringBuilder();
    if (mPublisher.isOn15MinuteIncrement())
    {
      sb.append("On 15-minute increment ");
    }
    if (mPublisher.isInfirmRegularPioneer())
    {
      sb.append("Infirm Regular Pioneer ");
    }

    if (sb.length() > 0)
    {
      ret = sb.toString();
    }

    return ret;
  }

  public void setStatus(Status inStatus)
  {
    mStatus = inStatus;
  }

  public static class Total
  {
    private final PublisherRecordCard mCongregation;
    private final PublisherRecordCard mPublishers;
    private final PublisherRecordCard mRegPioneers;
    private final PublisherRecordCard mAuxPioneers;

    public Total()
    {
      mCongregation = new PublisherRecordCard();
      mCongregation.setReports(new ArrayList<Report>());
      mCongregation.setPublisher(newPublisher("Congregation", -4L));

      mRegPioneers = new PublisherRecordCard();
      mRegPioneers.setReports(new ArrayList<Report>());
      mRegPioneers.setPublisher(newPublisher("Regular", -3L));

      mAuxPioneers = new PublisherRecordCard();
      mAuxPioneers.setReports(new ArrayList<Report>());
      mAuxPioneers.setPublisher(newPublisher("Auxiliary", -2L));

      mPublishers = new PublisherRecordCard();
      mPublishers.setReports(new ArrayList<Report>());
      mPublishers.setPublisher(newPublisher("Publishers", -1L));
    }

    private Person newPublisher(String inString, Long inId)
    {
      Person ret = new Person();
      ret.setLast(inString);
      ret.setFirst("");
      ret.setId(inId);
      return ret;
    }

    public void add(List<Report> inReports)
    {
      for (Report value : inReports)
      {
        add(mCongregation, value);

        if (Roles.Role.REGULAR_PIONEER.equals(value.getType()))
        {
          add(mRegPioneers, value);
        }
        else if (Roles.Role.AUXILIARY_PIONEER.equals(value.getType())
            || Roles.Role.AUXILIARY_PIONEER_30.equals(value.getType()))
        {
          add(mAuxPioneers, value);
        }
        else
        {
          add(mPublishers, value);
        }
      }
    }

    private void add(PublisherRecordCard inCard, Report inReport)
    {
      // Report report = inCard.find(inReport.getKey());
      // report.setPlacements(add(report.getPlacements(),
      // inReport.getPlacements()));
      // report.setVideoShowings(add(report.getVideoShowings(),
      // inReport.getVideoShowings()));
      // report.setHours(add(report.getHours(), inReport.getHours()));
      // report.setReturnVisits(add(report.getReturnVisits(),
      // inReport.getReturnVisits()));
      // report.setBibleStudies(add(report.getBibleStudies(),
      // inReport.getBibleStudies()));
      // report.setCount(count(report.getCount(), inReport.getHours()));
    }

    private int count(Integer inCount, Integer inHours)
    {
      return NumberUtil.intValue(inHours) > 0 ? NumberUtil.intValue(inCount) + 1
          : NumberUtil.intValue(inCount);
    }

    private Integer add(Integer inValue1, Integer inValue2)
    {
      return NumberUtil.intValue(inValue1) + NumberUtil.intValue(inValue2);
    }

    public PublisherRecordCard getAuxPioneers()
    {
      return mAuxPioneers;
    }

    public PublisherRecordCard getCongregation()
    {
      return mCongregation;
    }

    public PublisherRecordCard getPublishers()
    {
      return mPublishers;
    }

    public PublisherRecordCard getRegPioneers()
    {
      return mRegPioneers;
    }

    public void addMissing(int inYear, int inServiceYearsToAdd)
    {
      // mCongregation.addMissing(inYear, inServiceYearsToAdd);
      // mRegPioneers.addMissing(inYear, inServiceYearsToAdd);
      // mAuxPioneers.addMissing(inYear, inServiceYearsToAdd);
      // mPublishers.addMissing(inYear, inServiceYearsToAdd);
    }
  }

  public void add(Report inReport)
  {
    mReports.add(inReport);
  }
}