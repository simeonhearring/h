package h.khall.server.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import h.khall.server.dao.Dao;
import h.khall.shared.command.ReportEmailCommand;
import h.model.shared.khall.Congregation;
import h.model.shared.khall.FieldServiceGroup;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.model.shared.khall.Reports;
import h.model.shared.khall.YrMo;
import h.model.shared.util.EncryptUtil;
import h.service.email.AbstractEmailMessage;
import h.service.email.EmailMessage;
import h.service.email.EmailService;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;
import h.tool.util.PropertyUtil;

public class ReportEmailCommandBean
  extends AbstractDaoCommandBean<Dao, ReportEmailCommand>
{
  private static final Logger LOGGER = Logger.getLogger(ReportEmailCommandBean.class);

  private EmailService mEmailService;
  private String mDomain;

  @Override
  public RpcResponse execute(ReportEmailCommand inCommand)
  {
    Profile profile = (Profile) inCommand.getProfile();
    int congId = inCommand.getCongId();

    sendPerson(profile, congId, inCommand.getIds());

    return inCommand;
  }

  private void sendPerson(Profile inProfile, int inCongId, List<Long> inIds)
  {
    String cong = mDao.selectCong(inProfile).getName();
    Persons persons = mDao.selectPersons(inProfile);
    Reports reports = mDao.selectReports(inCongId);

    String mtemplate = getValue("emailMissingReports");
    String rtemplate = getValue("emailReportTemplate");

    int[] ym = range();
    int yF = ym[0];
    int mF = ym[1];
    int yT = ym[2];
    int mT = ym[3];

    for (Long id : inIds)
    {
      Person person = persons.gPerson(id);
      if (person.hasEmail())
      {
        List<String> list = reports.getMissing(yF, mF, yT, mT, true, id, person.getPublishing());
        if (list.size() != 0)
        {
          String missing = missing(list, rtemplate);
          String message = message(person.getFirst(), mtemplate, missing, person.gAuthLine(), mDomain);

          email(cong, message, inProfile.getUserId(), person.getEmail());
          LOGGER.info(person.getEmail() + " " + message);
        }
      }
    }
  }

  private void sendFsgo(Profile inProfile, int inCongId)
  {
    Congregation cong = mDao.selectCong(inProfile);
    Persons persons = mDao.selectPersons(inProfile);

    String rtemplate = getValue("emailReportTemplate");

    for (FieldServiceGroup value : cong.getFsgs().values())
    {
      StringBuilder sb = new StringBuilder();
      for (Person person : persons.getFsg(value.getId()))
      {
        if (person.isPublisher())
        {
          message(sb, person.gName(), person.gAuthLine(), mDomain);
        }
      }
      String message = rtemplate.replaceAll("<0>", sb.toString());
    }
  }

  private static String message(String inFirst, String inMsg, String inMissing, String inAuth, String inDomain)
  {
    StringBuilder sb = new StringBuilder();

    sb.append(inMsg.replaceAll("<0>", inFirst).replaceAll("<1>", inMissing));
    sb.append("<br /><br />");

    sb.append("<a href='").append(inDomain).append("secreportsubmit.jsp?");
    sb.append("auth=").append(EncryptUtil.encrypt(inAuth));
    sb.append("'>Click here to submit online!</a>");

    return sb.toString();
  }

  private static void message(StringBuilder inSb, String inName, String inAuth, String inDomain)
  {
    inSb.append("<a href='").append(inDomain).append("secreportsubmit.jsp?");
    inSb.append("auth=").append(EncryptUtil.encrypt(inAuth));
    inSb.append("'>Click here to submit report for ").append(inName).append("</a><br/>");
  }

  private static String missing(List<String> inValue, String inTemplate)
  {
    StringBuilder ret = new StringBuilder();
    for (String value : inValue)
    {
      ret.append(YrMo.convert(value)).append(" ").append(inTemplate).append("<br/>");
    }
    return ret.toString();
  }

  protected static int[] range()
  {
    Calendar cf = Calendar.getInstance();
    Calendar ct = Calendar.getInstance();
    cf.add(Calendar.MONTH, -7);

    SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
    SimpleDateFormat formatMonth = new SimpleDateFormat("MM");

    int yf = Integer.valueOf(formatYear.format(cf.getTime()));
    int mf = Integer.valueOf(formatMonth.format(cf.getTime()));
    int yt = Integer.valueOf(formatYear.format(ct.getTime()));
    int mt = Integer.valueOf(formatMonth.format(ct.getTime())).intValue() - 1;

    return new int[]
    {
        yf, mf, yt, mt
    };
  }

  private void email(final String inCong, final String inMessage, final String inFrom,
      final String... inAddress)
  {
    EmailMessage emailMessage = new AbstractEmailMessage()
    {
      String[] from = new String[]
      {
          inFrom
      };

      @Override
      public String getSubject()
      {
        return inCong + " - Submit ministry reports";
      }

      @Override
      public String getBody()
      {
        return inMessage;
      }

      @Override
      public String[] getAddress()
      {
        return inAddress;
      }

      @Override
      public String[] getReplyTo()
      {
        return from;
      }

      @Override
      public BodyType getBodyType()
      {
        return BodyType.HTML;
      }
    };
    send(emailMessage);
  }

  private void send(EmailMessage inEmailMessage)
  {
    try
    {
      mEmailService.sendEmail(inEmailMessage);
    }
    catch (Exception e)
    {
      LOGGER.error("error sending to " + inEmailMessage.getAddress(), e);
    }
  }

  public void setEmailService(EmailService inEmailService)
  {
    mEmailService = inEmailService;
  }

  public void setDomain(String inDomain)
  {
    mDomain = inDomain;
  }

  public static String getValue(String inKey)
  {
    return PropertyUtil.getValue("h/khall/client/resource/Messages", "", inKey);
  }
}