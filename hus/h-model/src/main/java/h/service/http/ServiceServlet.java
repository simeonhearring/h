package h.service.http;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import h.util.HostUtil;

@SuppressWarnings("serial")
public class ServiceServlet extends HttpServlet
{
  private static final Logger LOGGER = Logger.getLogger(ServiceServlet.class);

  @Override
  protected void doGet(HttpServletRequest inReq, HttpServletResponse inResp)
      throws ServletException, IOException
  {
    doEither(inReq, inResp);
  }

  @Override
  protected void doPost(HttpServletRequest inReq, HttpServletResponse inResp)
      throws ServletException, IOException
  {
    doEither(inReq, inResp);
  }

  private void doEither(HttpServletRequest inRequest, HttpServletResponse inResponse)
      throws ServletException, IOException
  {
    String serviceName = null;
    try
    {
      serviceName = getServiceName();
      Service service = context().getBean(serviceName, Service.class);
      Map<String, String> map = HostUtil.getParameters(serviceName, inRequest);
      service.execute(map, inResponse);
    }
    catch (Exception e)
    {
      LOGGER.error("error executing service " + serviceName, e);
    }
  }

  private String getServiceName()
  {
    return getServletConfig().getInitParameter("serviceName");
  }

  private ApplicationContext context()
  {
    ServletContext servletContext = getServletConfig().getServletContext();
    return (ApplicationContext) servletContext
        .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
  }
}