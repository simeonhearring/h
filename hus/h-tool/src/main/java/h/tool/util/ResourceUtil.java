package h.tool.util;

import java.io.InputStream;
import java.util.MissingResourceException;

public final class ResourceUtil
{
  private ResourceUtil()
  {
  }

  public static String contents(String inResource)
  {
    InputStream stream = stream(inResource);
    if (stream == null)
    {
      throw new MissingResourceException("no resource: " + inResource, "", "");
    }
    try
    {
      return StreamUtil.read(stream);
    }
    finally
    {
      StreamUtil.close(stream);
    }
  }

  public static InputStream stream(Class<?> inClass, String inType)
  {
    InputStream ret;

    if ((ret = stream(asResourceName(inClass, inType))) == null)
    {
      Class<?>[] interfaces = inClass.getInterfaces();
      for (Class<?> anInterface : interfaces)
      {
        if ((ret = stream(asResourceName(anInterface, inType))) != null)
        {
          break;
        }
      }
    }

    return ret;
  }

  public static InputStream stream(String inResource)
  {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    return loader.getResourceAsStream(inResource);
  }

  public static String getSql(Class<?> inClass)
  {
    return contents(inClass, "sql");
  }

  public static String contents(Class<?> inClass, String inType)
  {
    return contents(asResourceName(inClass, inType));
  }

  private static String asResourceName(Class<?> inClass, String inType)
  {
    return inClass.getName().replaceAll("\\.", "/") + "." + inType;
  }

  public static String getSql(Package inPackage, String inResourceName)
  {
    return contents(inPackage, inResourceName + ".sql");
  }

  public static String contents(Package inPackage, String inResourceName)
  {
    String packageName = inPackage.toString().split(" ")[1];
    String directory = packageName.replaceAll("\\.", "/");
    return contents(directory + '/' + inResourceName);
  }
}