package h.style.g.server.rpc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyType;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import h.model.shared.exception.NotifyException;
import h.style.g.client.service.rpc.RpcService;
import h.style.g.server.command.CommandBean;
import h.style.g.server.util.HostUtil;
import h.style.g.shared.command.LoggerCommand;
import h.style.g.shared.rpc.CommandName;
import h.style.g.shared.rpc.common.NotifyResponse;
import h.style.g.shared.rpc.common.RpcCommand;
import h.style.g.shared.rpc.common.RpcResponse;
import h.style.g.shared.rpc.common.WantsServerInfo;
import h.tool.util.PropertyUtil;
import h.tool.util.ServerErrorUtil;
import h.tool.util.StringUtil;

@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService
{
  private static final Logger LOGGER = LogManager.getLogger(RpcServiceImpl.class);

  private ApplicationContext mAppContext;

  @Override
  public void init(ServletConfig inConfig) throws ServletException
  {
    super.init(inConfig);

    mAppContext = (ApplicationContext) inConfig.getServletContext()
        .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
  }

  @SuppressWarnings("unchecked")
  @Override
  public RpcResponse fire(RpcCommand inCommand)
  {
    RpcResponse ret = null;
    String bean = null;

    try
    {
      String hostName = HostUtil.getHostName();
      String userInfo = StringUtil.ensure(inCommand.getUserInfo());
      String ipAddress = HostUtil.getRemoteAddr(getThreadLocalRequest());

      ThreadContext.put("servername", hostName);
      ThreadContext.put("userid", userInfo);
      ThreadContext.put("clientip", ipAddress);

      if (allowDebug(inCommand))
      {
        LOGGER.debug(debugRpcCommand(inCommand));
      }

      bean = commandBeanName(inCommand);

      LOGGER.warn("Executing rpc ... {}", bean);

      ret = ((CommandBean<RpcCommand>) mAppContext.getBean(bean)).execute(inCommand);

      wantsServerInfo(inCommand);
    }
    catch (NotifyException e)
    {
      ret = new NotifyResponse(NotifyType.DANGER, e.getNotifyMessage());
      e.printStackTrace();
      LOGGER.warn("Notify Exception executing rpc ... " + bean + " " + e.getNotifyMessage());
    }
    catch (Exception e)
    {
      ret = new NotifyResponse(NotifyType.DANGER, e.getMessage());
      e.printStackTrace();
      LOGGER.error("Error executing rpc command " + bean, e);
    }

    ThreadContext.clearAll();

    return ret;
  }

  private boolean allowDebug(RpcCommand inCommand)
  {
    return LOGGER.isDebugEnabled() && !(inCommand instanceof LoggerCommand);
  }

  private static String debugRpcCommand(Object inObject)
  {
    return ServerErrorUtil.objectToString(inObject, false);
  }

  private String commandBeanName(RpcCommand inCommand)
  {
    String ret = null;

    CommandName annotation = inCommand.getClass().getAnnotation(CommandName.class);

    if (annotation == null || annotation.value() == null || "".equals(annotation.value()))
    {
      throw new IllegalArgumentException("RpcCommand class [" + inCommand.getClass().getSimpleName()
          + "] must declare a CommandBean annotation.");
    }
    ret = annotation.value();

    return ret;
  }

  private void wantsServerInfo(RpcCommand inCommand)
  {
    if (inCommand instanceof WantsServerInfo)
    {
      WantsServerInfo cmd = (WantsServerInfo) inCommand;
      cmd.setRemoteHost(HostUtil.getRemoteAddr(getThreadLocalRequest()));
      cmd.setHostName(HostUtil.getHostName());
      cmd.setVersion(serverVersion(((WantsServerInfo) inCommand).getMessagePath()));
      cmd.setEnvironment(environment());
    }
  }

  public static String serverVersion(String inPath)
  {
    return PropertyUtil.getValue(inPath, "-", "version", "gwtVersion", "buildNumber");
  }

  private String environment()
  {
    return (String) mAppContext.getBean("Environment");
  }
}