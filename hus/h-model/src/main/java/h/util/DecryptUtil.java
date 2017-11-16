package h.util;

import org.apache.log4j.Logger;

import com.googlecode.gwt.crypto.client.TripleDesCipher;

public final class DecryptUtil
{
  private static final Logger LOGGER = Logger.getLogger(DecryptUtil.class);

  public static String decrypt(byte[] inKey, String inValue)
  {
    String ret = null;
    if (inValue != null && !"".equals(inValue.trim()))
    {
      TripleDesCipher cipher = new TripleDesCipher();
      cipher.setKey(inKey);
      try
      {
        ret = cipher.decrypt(inValue);
      }
      catch (Exception e)
      {
        LOGGER.error("error decrypting ... ", e);
      }
    }
    return ret;
  }
}