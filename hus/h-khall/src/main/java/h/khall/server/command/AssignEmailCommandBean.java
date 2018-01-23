package h.khall.server.command;

import static h.khall.server.reports.OclmReport.meetingDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import h.khall.server.dao.Dao;
import h.khall.shared.command.AssignEmailCommand;
import h.model.shared.khall.Assignment;
import h.model.shared.khall.Hall;
import h.model.shared.khall.Part;
import h.model.shared.khall.Person;
import h.model.shared.khall.Persons;
import h.model.shared.khall.Profile;
import h.model.shared.khall.StudyPoint;
import h.service.email.AbstractEmailMessage;
import h.service.email.EmailMessage;
import h.service.email.EmailService;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.rpc.common.RpcResponse;
import h.tool.util.ResourceUtil;

public class AssignEmailCommandBean extends AbstractDaoCommandBean<Dao, AssignEmailCommand>
{
  private static final Logger LOGGER = Logger.getLogger(AssignEmailCommandBean.class);

  private EmailService mEmailService;

  @Override
  public RpcResponse execute(AssignEmailCommand inCommand)
  {
    Persons p = mDao.selectPersons((Profile) inCommand.getProfile());

    for (Assignment value : inCommand.getAssignment())
    {
      send(inCommand, p, value);
    }

    return inCommand;
  }

  private void send(AssignEmailCommand inCommand, Persons inPersons, Assignment inAssign)
  {
    String date = meetingDate("EEE MM/dd/yy", inCommand.getMeetingDay(), inAssign.getDate());
    String message = message(inPersons, inCommand.getFrom(), inAssign, date);
    send(date, message, getEmails(inPersons, inAssign), inCommand.getFrom());
  }

  private static void flagGivenIn(Hall inHall,  Map<String, String> inMap)
  {
    switch (inHall)
    {
      case MAIN:
        inMap.put("9", "X");
        break;
      case SECOND:
        inMap.put("10", "X");
        break;
      // case THIRD:
      // inMap.put("11", "X");
      // break;
      default:
        break;
    }
  }

  private static void flagAssignment(Part inPart, Map<String, String> inMap)
  {
    switch (inPart)
    {
      case BIBLE_READING:
        inMap.put("1", "X");
        break;
      case INITIAL_CALL:
        inMap.put("2", "X");
        break;
      case F_RETURN_VISIT:
        inMap.put("3", "X");
        break;
      case S_RETURN_VISIT:
        inMap.put("4", "X");
        break;
      case T_RETURN_VISIT:
        inMap.put("5", "X");
        break;
      case BIBLE_STUDY:
        inMap.put("6", "X");
        break;
      case TALK:
        inMap.put("7", "X");
        break;
      // case OTHER:
      // inMap.put("8", "X");
      // break;
      default:
        break;
    }
  }

  private static String message(Persons inPersons, String inFrom, Assignment inAssignment, String inDate)
  {
    boolean student = inAssignment.isStudent();
    Part part = inAssignment.getPart();

    Map<String, String> map = getDataMap();
    map.put("date", inDate);
    map.put("meeting", part.getMeetingTitle());
    map.put("styleStudent", part.isStudent() ? "" : "display:none;");
    map.put("styleNonStudent", !part.isStudent() ? "" : "display:none;");
    map.put("styleReader", part.isCbs() ? "" : "display:none;");

    map.put("name", getStudentNames(inPersons, inAssignment));
    map.put("assistant", getAssistantNames(inPersons, inAssignment));
    map.put("width", "width: " + (student ? "370px;" : "450px;"));
    map.put("counsel", StudyPoint.display2(inAssignment.getStudyPoint()));
    map.put("part", inAssignment.gPartTimeLabel());

    flagGivenIn(inAssignment.getHall(), map);
    flagAssignment(inAssignment.getPart(), map);

    String template = ResourceUtil.contents("h/khall/server/command/oclmassign.html");
    for (Entry<String, String> value : map.entrySet())
    {
      template = replace(template, value.getKey(), value.getValue());
    }
    return template;
  }

  private static Map<String, String> getDataMap()
  {
    Map<String, String> ret = new HashMap<>();
    ret.put("1", "");
    ret.put("2", "");
    ret.put("3", "");
    ret.put("4", "");
    ret.put("5", "");
    ret.put("6", "");
    ret.put("7", "");
    ret.put("8", "");
    ret.put("9", "");
    ret.put("10", "");
    ret.put("11", "");
    return ret;
  }

  private static String getStudentNames(Persons inPersons, Assignment inAssignment)
  {
    Person student = inPersons.gPerson(inAssignment.getParticipantId());
    return student.gName();
  }

  private static String getAssistantNames(Persons inPersons, Assignment inAssignment)
  {
    StringBuilder sb = new StringBuilder();
    Person assistant = inPersons.gPerson(inAssignment.getAssistantId());
    if (assistant != null)
    {
      sb.append(assistant.gName());
    }
    return sb.toString();
  }

  private static String[] getEmails(Persons inPersons, Assignment inValue)
  {
    Person student = inPersons.gPerson(inValue.getParticipantId());
    Person assistant = inPersons.gPerson(inValue.getAssistantId());

    List<String> list = new ArrayList<>();

    addEmail(list, student);
    addEmail(list, assistant);

    String[] ret = new String[list.size()];
    for (int i = 0; i < ret.length; i++)
    {
      ret[i] = list.get(i);
    }
    return ret;
  }

  private static void addEmail(List<String> inList, Person inStudent)
  {
    if (inStudent != null)
    {
      String value = inStudent.getEmail();
      if (value != null)
      {
        inList.add(value);
      }
    }
  }

  private static String replace(String inText, String inTag, String inReplaceWith)
  {
    return inText.replaceAll("\\$\\{" + inTag + "}", inReplaceWith);
  }

  private void send(final String inDate, final String inMessage, final String[] inAddress,
      final String... inFrom)
  {
    EmailMessage emailMessage = new AbstractEmailMessage()
    {
      @Override
      public String getSubject()
      {
        return "OCLM Meeting Assignment";
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
      public String[] getCopyAddress()
      {
        return inFrom;
      }

      @Override
      public String[] getReplyTo()
      {
        return inFrom;
      }

      @Override
      public BodyType getBodyType()
      {
        return BodyType.HTML;
      }
    };

    try
    {
      mEmailService.sendEmail(emailMessage);
    }
    catch (Exception e)
    {
      LOGGER.error("error sending to " + emailMessage.getAddress(), e);
    }
  }

  public void setEmailService(EmailService inEmailService)
  {
    mEmailService = inEmailService;
  }
}