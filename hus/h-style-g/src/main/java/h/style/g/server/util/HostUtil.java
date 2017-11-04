package h.style.g.server.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public final class HostUtil
{
  protected static final Logger LOGGER = Logger.getLogger(HostUtil.class);

  public static String getHostName()
  {
    String ret = null;
    try
    {
      ret = InetAddress.getLocalHost().getHostName();
    }
    catch (UnknownHostException e)
    {
      // do nothing
    }
    return ret;
  }

  public static String getRemoteAddr(HttpServletRequest inThreadLocalRequest)
  {
    String ret = inThreadLocalRequest.getHeader("X-Forwarded-For");
    if (ret == null || "".equals(ret))
    {
      ret = inThreadLocalRequest.getRemoteAddr();
    }
    return ret;
  }

  public static Map<String, String> getParameters(String inString)
  {
    Map<String, String> ret = new HashMap<>();

    String[] values = inString.split("&");
    for (String value : values)
    {
      String[] values1 = value.split("=", 2);
      ret.put(values1[0], values1[1]);
    }

    return ret;
  }

  public static Map<String, String> getParameters(String inPrefix, HttpServletRequest inRequest)
  {
    Map<String, String> ret = new HashMap<>();

    Enumeration<?> enumeration = inRequest.getParameterNames();
    while (enumeration != null && enumeration.hasMoreElements())
    {
      String name = (String) enumeration.nextElement();
      String value = inRequest.getParameter(name);
      ret.put(name, value);
      LOGGER.info(inPrefix + " parameters ... [" + name + "] [" + value + "]");
    }

    return ret;
  }
}