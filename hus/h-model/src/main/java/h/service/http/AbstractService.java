package h.service.http;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public abstract class AbstractService implements Service
{
  private static final Logger LOGGER = Logger.getLogger(AbstractService.class);

  public void writeResponse(HttpServletResponse inResponse, String inMessage)
  {
    try
    {
      inResponse.getWriter().write(inMessage);
      inResponse.setStatus(200);
    }
    catch (IOException e)
    {
      LOGGER.error("Error writing response", e);
    }
  }
}