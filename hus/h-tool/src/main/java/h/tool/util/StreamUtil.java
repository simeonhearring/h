package h.tool.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;

public final class StreamUtil
{
  private StreamUtil()
  {
  }

  public static String read(InputStream inStream)
  {
    String ret = "";

    if (inStream != null)
    {
      StringBuffer result = new StringBuffer();

      CharBuffer buf = CharBuffer.allocate(4096);
      Reader reader = new InputStreamReader(inStream);
      int n;
      try
      {
        while ((n = reader.read(buf)) > -1)
        {
          buf.flip();
          result.append(new String(buf.array(), 0, n));
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      ret = result.toString();
    }
    return ret;
  }

  public static void close(InputStream inStream)
  {
    try
    {
      inStream.close();
    }
    catch (Exception ignore)
    {
      // do nothing
    }
  }

  public static InputStream loadStream(String inName)
  {
    return loadStream(inName, Thread.currentThread().getContextClassLoader());
  }

  public static InputStream loadStream(String inName, ClassLoader inLoader)
  {
    InputStream ret = inLoader.getResourceAsStream(inName);
    return ret;
  }
}