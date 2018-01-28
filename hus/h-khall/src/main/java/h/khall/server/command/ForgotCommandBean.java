package h.khall.server.command;

import org.apache.log4j.Logger;

import h.khall.server.dao.Dao;
import h.model.shared.khall.Profile;
import h.model.shared.util.RandomUtil;
import h.service.email.AbstractEmailMessage;
import h.service.email.EmailMessage;
import h.service.email.EmailService;
import h.style.g.server.command.AbstractDaoCommandBean;
import h.style.g.shared.command.ForgotCommand;
import h.style.g.shared.rpc.common.RpcResponse;
import h.tool.util.ResourceUtil;

public class ForgotCommandBean extends AbstractDaoCommandBean<Dao, ForgotCommand>
{
  private static final Logger LOGGER = Logger.getLogger(ForgotCommandBean.class);

  private EmailService mEmailService;

  @Override
  public RpcResponse execute(ForgotCommand inCommand)
  {
    Profile profile = mDao.selectProfile(inCommand.getProfile());
    if (profile != null && profile.canReset())
    {
      String newpswd = RandomUtil.random();
      profile.setPassword("password:" + newpswd);
      mDao.update(inCommand.getProfile().gEncrypt(), profile);

      String template = ResourceUtil.contents("h/khall/server/command/forgot.html");
      template = replace(template, "pswd", newpswd);

      send(template, profile.getUserId(), profile.getUserId());
    }
    else
    {
      throw new RuntimeException("Opps! Cannot reset password.");
    }

    return inCommand;
  }

  private static String replace(String inText, String inTag, String inReplaceWith)
  {
    return inText.replaceAll("\\$\\{" + inTag + "}", inReplaceWith);
  }

  private void send(final String inMessage, final String... inAddress)
  {
    EmailMessage emailMessage = new AbstractEmailMessage()
    {
      @Override
      public String getSubject()
      {
        return "Account change";
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