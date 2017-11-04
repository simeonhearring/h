package h.tool.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class ServerErrorUtil
{
  protected static final Logger LOGGER = Logger.getLogger(ServerErrorUtil.class);

  private static final String[] PACKAGE_INCLUDE =
    {
        "net.hus"
    };

  private ServerErrorUtil()
  {
  }

  public static String objectToString(Object inObject, boolean inDeeper)
  {
    StringBuilder ret = new StringBuilder();

    ret.append(inObject.getClass().getSimpleName()).append(" ");

    Method[] values = inObject.getClass().getMethods();
    for (Method value : values)
    {
      String name = value.getName();
      if (Modifier.PUBLIC == value.getModifiers() && value.getParameterTypes().length == 0
          && name != null && (name.startsWith("get") || name.startsWith("is"))
          && !name.startsWith("getBytes"))
      {
        ret.append(name).append(": [");
        try
        {
          Object object = value.invoke(inObject, (Object[]) null);
          String objname = object != null ? object.getClass().getName() : "";
          if (inDeeper && goDeeper(objname))
          {
            ret.append(" [").append(objectToString(object, inDeeper)).append("]");
          }
          else if (object instanceof ArrayList<?>)
          {
            for (Object arrayValue : (ArrayList<?>) object)
            {
              ret.append(" [").append(objectToString(arrayValue, inDeeper)).append("]");
            }
          }
          else if (object instanceof Object[])
          {
            for (Object arrayValue : (Object[]) object)
            {
              ret.append(" [").append(objectToString(arrayValue, inDeeper)).append("]");
            }
          }
          else
          {
            ret.append(object);
          }
        }
        catch (Exception e)
        {
          LOGGER.error(ret.toString(), e);
        }
        ret.append("] ");
      }
    }

    return ret.toString();
  }

  private static boolean goDeeper(String inName)
  {
    boolean ret = false;
    if (!"".equals(inName))
    {
      for (String value : PACKAGE_INCLUDE)
      {
        ret |= inName.startsWith(value);
      }
    }
    return ret;
  }
}
