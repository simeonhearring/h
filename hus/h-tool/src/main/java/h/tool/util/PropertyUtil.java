package h.tool.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public abstract class PropertyUtil
{
  private static final String SUFFIX = ".properties";

  private PropertyUtil()
  {
  }

  public static String getValue(String inName, String inDelim, String... inKeys)
  {
    StringBuilder sb = new StringBuilder();
    Properties prop = getStreamProperties(inName);
    if (prop != null)
    {
      for (int i = 0; i < inKeys.length; i++)
      {
        sb.append(prop.getProperty(inKeys[i]));
        if (i + 1 != inKeys.length)
        {
          sb.append(inDelim);
        }
      }
    }
    return sb.toString();
  }

  public static Properties getStreamProperties(String inName)
  {
    return streamProperties(validate(inName), classLoader());
  }

  public static Properties getBundleProperties(String inName)
  {
    return bundleProperties(validate(inName), classLoader());
  }

  private static Properties bundleProperties(String inName, ClassLoader inLoader)
  {
    Properties result = null;

    inName = convertSlashToDot(inName);

    try
    {
      ResourceBundle rb = loadBundle(inName, inLoader);

      result = new Properties();
      for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();)
      {
        String key = keys.nextElement();
        String value = rb.getString(key);
        result.put(key, value);
      }
    }
    catch (Exception e)
    {
      result = null;
    }

    return result;
  }

  private static Properties streamProperties(String inName, ClassLoader inLoader)
  {
    Properties ret = null;

    InputStream stream = null;
    try
    {
      inName = convertDotToSlash(inName);
      stream = StreamUtil.loadStream(inName, inLoader);
      if (stream != null)
      {
        ret = new Properties();
        ret.load(stream);
      }
    }
    catch (Exception e)
    {
      ret = null;
    }
    finally
    {
      close(stream);
    }

    return ret;
  }

  private static String convertSlashToDot(String inName)
  {
    inName = inName.replace('/', '.');
    return inName;
  }

  private static ResourceBundle loadBundle(String inName, ClassLoader inLoader)
  {
    ResourceBundle ret = null;
    try
    {
      ret = ResourceBundle.getBundle(inName, Locale.getDefault(), inLoader);
    }
    catch (MissingResourceException e)
    {
      e.printStackTrace();
    }
    return ret;
  }

  private static ClassLoader classLoader()
  {
    return Thread.currentThread().getContextClassLoader();
  }

  private static void close(InputStream inStream)
  {
    if (inStream != null)
    {
      try
      {
        inStream.close();
      }
      catch (Throwable e)
      {
        // do nothing
      }
    }
  }

  private static String convertDotToSlash(String inName)
  {
    inName = inName.replace('.', '/');

    if (!inName.endsWith(SUFFIX))
    {
      inName = inName.concat(SUFFIX);
    }
    return inName;
  }

  private static String validate(String inName)
  {
    if (inName == null)
    {
      throw new IllegalArgumentException("null input: name");
    }

    if (inName.startsWith("/"))
    {
      inName = inName.substring(1);
    }

    if (inName.endsWith(SUFFIX))
    {
      inName = inName.substring(0, inName.length() - SUFFIX.length());
    }
    return inName;
  }
}