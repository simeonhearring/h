package h.khall.server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import h.khall.server.dao.Dao;
import h.model.shared.khall.Report;
import h.model.shared.util.EncryptUtil;
import h.model.shared.util.NumberUtil;
import h.model.shared.util.StringUtil;
import h.model.shared.util.TimeUtil;
import h.service.email.EmailMessageAdapter;
import h.service.email.EmailService;
import h.service.http.AbstractService;

public class SubmitReportService extends AbstractService
{
  private static final Logger LOGGER = Logger.getLogger(SubmitReportService.class);

  private Dao mDao;
  private EmailService mEmailService;

  @Override
  public void execute(Map<String, String> inMap, HttpServletResponse inResponse)
  {
    String messasge =
        "Sorry! Nothing was saved. Please contact your secretary to submit or change your ministry report.";

    try
    {
      Converted converted = convert(inMap);
      LOGGER.info("converted [" + converted + "]");

      List<Report> saved = save(converted);

      if (saved.size() > 0)
      {
        String msg = "Congrats!!!\n\nYou saved the following report(s).\n\n";
        messasge = msg + send(converted.getName(), saved, converted.getEmail());
      }
    }
    catch (Exception e)
    {
      LOGGER.error("Error during web submit", e);
    }

    writeResponse(inResponse, messasge);
  }

  private List<Report> save(Converted inConverted)
  {
    List<Report> saved = new ArrayList<>();

    List<Report> reports = inConverted.getReports();
    if (reports.size() > 0)
    {
      Integer congId = inConverted.mCongId;
      Long pubId = inConverted.mPubId;
      LOGGER.info("pub [" + (pubId != null) + "] report size=" + reports.size());

      if (pubId != null)
      {
        for (Report value : reports)
        {
          Report report = mDao.selectReport(congId, pubId, value.getYear(), value.getMonth());
          LOGGER.info("report value [" + value + "] found [" + report + "]");
          if (report == null)
          {
            upsert(saved, pubId, value);
          }
          else if (report.gWebUpdateable())
          {
            report.update(value);
            upsert(saved, pubId, report);
          }
        }
      }
    }
    return saved;
  }

  private String send(final String inName, List<Report> inReport, final String inTo)
  {
    final StringBuilder sb = new StringBuilder();
    for (Report report : inReport)
    {
      sb.append("Name: ").append(inName);
      sb.append("\nMonth: ").append(report.getMonthText()).append(" ").append(report.getYear());
      sb.append("\nPlacements: ").append(toInt(report.getPlacements()));
      sb.append("\nVideo Showings: ").append(toInt(report.getVideoShowings()));
      sb.append("\nHours: ").append(report.getHoursPartial());
      sb.append("\nReturn Visits: ").append(toInt(report.getReturnVisits()));
      sb.append("\nBible Studies: ").append(toInt(report.getBibleStudies()));
      sb.append("\nComments: ").append(StringUtil.notNull(report.getRemarks()));
      sb.append("\n\n");
    }

    LOGGER.info(inName + " " + inTo + " " + sb.toString());

    mEmailService.sendEmail(new EmailMessageAdapter()
    {
      @Override
      public String[] getReplyTo()
      {
        return null;
      }

      @Override
      public String[] getCopyAddress()
      {
        return null;
      }

      @Override
      public String[] getAddress()
      {
        return inTo.split("\\|");
      }

      @Override
      public String getSubject()
      {
        return "Your saved ministry report(s)";
      }

      @Override
      public String getBody()
      {
        return sb.toString();
      }

      @Override
      public BodyType getBodyType()
      {
        return BodyType.TEXT;
      }

      @Override
      public String[] getBlindCopyAddress()
      {
        return null;
      }
    });

    return sb.toString();
  }

  private static int toInt(Integer inValue)
  {
    return NumberUtil.toInt(inValue);
  }

  protected void upsert(List<Report> inSaved, Long inPubId, Report inReport)
  {
    inReport.setPubId(inPubId);
    mDao.upsert(inReport);
    inSaved.add(inReport);
  }

  private static Double partial(String inValue)
  {
    Double ret = null;

    if ("I_15".equals(inValue))
    {
      ret = Report.Partial.I_15.getValue();
    }
    else if ("I_30".equals(inValue))
    {
      ret = Report.Partial.I_30.getValue();
    }
    else if ("I_45".equals(inValue))
    {
      ret = Report.Partial.I_45.getValue();
    }

    return ret;
  }

  private static Integer get(Map<String, String> inMap, String inKey)
  {
    return NumberUtil.toInteger(inMap.get(inKey));
  }

  public void setDao(Dao inDao)
  {
    mDao = inDao;
  }

  public void setEmailService(EmailService inEmailService)
  {
    mEmailService = inEmailService;
  }

  protected Converted convert(Map<String, String> inMap)
  {
    Converted ret = new Converted();

    String when = TimeUtil.format("MM-dd-yyyy HH:mm", new Date());

    String[] auth = auth(inMap.get("auth"));
    ret.setPubId(NumberUtil.toLong(auth[0]));
    ret.setName(auth[1]);
    ret.setEmail(auth[2]);
    ret.setCongId(NumberUtil.toInteger(auth[3]));

    List<Report> list = new ArrayList<>();
    String[] dates = auth[3].split(",");
    for (String value : dates)
    {
      String hours = inMap.get("hour-" + value).trim();
      boolean ispartial = "on".equals(inMap.get("ispartial-" + value));

      if (!"".equals(hours) || ispartial)
      {
        Date d = TimeUtil.parse("yyyyMMdd", value);
        Report r = newReport(d);
        r.setSendDate(sendDate());

        if ("0".equals(hours))
        {
          r.setNoActivity(true);
        }
        else
        {
          Integer hour = get(inMap, "hour-" + value);
          Integer place = get(inMap, "place-" + value);
          Integer video = get(inMap, "video-" + value);
          Integer rv = get(inMap, "rv-" + value);
          Integer study = get(inMap, "study-" + value);
          Double partial = partial(inMap.get("partial-" + value));

          r.setHours(hour);
          r.setPlacements(place);
          r.setVideoShowings(video);
          r.setReturnVisits(rv);
          r.setBibleStudies(study);

          if (ispartial)
          {
            r.setPartialHours(partial);
          }
        }
        r.setRemarks("[web saved on:" + when + "] " + inMap.get("comment-" + value).trim());

        list.add(r);
      }
    }

    ret.setReports(list);

    return ret;
  }

  @SuppressWarnings("deprecation")
  private Report newReport(Date inDate)
  {
    Report ret = new Report();
    ret.setYear(inDate.getYear() + 1900);
    ret.setMonth(inDate.getMonth() + 1);
    return ret;
  }

  @SuppressWarnings("deprecation")
  private static Date sendDate()
  {
    Date now = new Date();
    return TimeUtil.getFirstOfMonth(now.getYear() + 1900, now.getMonth());
  }

  private static String[] auth(String inString)
  {
    String value = EncryptUtil.decrypt(inString);
    return value.split("\\|");
  }

  protected static class Converted
  {
    private Integer mCongId;
    private Long mPubId;
    private String mName;
    private String mEmail;
    private List<Report> mReports;

    public List<Report> getReports()
    {
      return mReports;
    }

    public void setReports(List<Report> inReports)
    {
      mReports = inReports;
    }

    public Long getPubId()
    {
      return mPubId;
    }

    public void setPubId(Long inPubId)
    {
      mPubId = inPubId;
    }

    public String getName()
    {
      return mName;
    }

    public void setName(String inName)
    {
      mName = inName;
    }

    public String getEmail()
    {
      return mEmail;
    }

    public void setEmail(String inEmail)
    {
      mEmail = inEmail;
    }

    @Override
    public String toString()
    {
      StringBuilder builder = new StringBuilder();
      builder.append("Converted [mPubId=");
      builder.append(mPubId);
      builder.append(", mName=");
      builder.append(mName);
      builder.append(", mEmail=");
      builder.append(mEmail);
      builder.append(", mReports=");
      builder.append(mReports);
      builder.append("]");
      return builder.toString();
    }

    public Integer getCongId()
    {
      return mCongId;
    }

    public void setCongId(Integer inCongId)
    {
      mCongId = inCongId;
    }
  }
}