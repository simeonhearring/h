package h.model.shared.util;

import java.util.Date;

import com.googlecode.gwt.crypto.client.TripleDesCipher;

public class EncryptUtil
{
  public static final byte[] GWT_DES_KEY = new byte[]
  {
      (byte) 1,
      (byte) 4,
      (byte) 7,
      (byte) 0,
      (byte) 1,
      (byte) 3,
      (byte) 4,
      (byte) 5,
      (byte) 9,
      (byte) 1,
      (byte) 3,
      (byte) 6,
      (byte) 6,
      (byte) 7,
      (byte) 8,
      (byte) 9,
      (byte) 2,
      (byte) 4,
      (byte) 2,
      (byte) 4,
      (byte) 6,
      (byte) 7,
      (byte) 9,
      (byte) 9,
  };

  public static String encrypt(String inValue)
  {
    return encrypt(GWT_DES_KEY, inValue);
  }

  public static String decrypt(String inValue)
  {
    return decrypt(GWT_DES_KEY, inValue);
  }

  public static String encryptLong(byte[] inKey, Long inValue)
  {
    if (inValue == null)
    {
      return null;
    }
    return encrypt(inKey, String.valueOf(inValue));
  }

  public static Long decryptLong(byte[] inKey, String inValue)
  {
    if (inValue == null)
    {
      return null;
    }
    return Long.valueOf(decrypt(inKey, inValue));
  }

  public static String encrypt(byte[] inKey, Enum<?> inValue)
  {
    if (inValue == null)
    {
      return null;
    }
    return encrypt(inKey, inValue.name());
  }

  public static <T extends Enum<?>> T decrypt(byte[] inKey, String inValue, T[] inValues)
  {
    if (inValue == null)
    {
      return null;
    }
    return EnumUtil.valueOf(decrypt(inKey, inValue), inValues);
  }

  public static String encryptDate(byte[] inKey, Date inValue)
  {
    return encryptLong(inKey, inValue.getTime());
  }

  public static Date decryptDate(byte[] inKey, String inValue)
  {
    if (inValue == null)
    {
      return null;
    }
    return new Date(decryptLong(inKey, inValue));
  }

  public static String encrypt(byte[] inKey, String inValue)
  {
    String ret = null;

    if (inValue != null && !"".equals(inValue.trim()))
    {
      TripleDesCipher cipher = new TripleDesCipher();
      cipher.setKey(inKey);
      try
      {
        ret = cipher.encrypt(inValue);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

    return ret;
  }

  protected static String decrypt(byte[] inKey, String inValue)
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
        e.printStackTrace();
      }
    }
    return ret;
  }

  public static String[] decryptArray(byte[] inKey, String[] inValue)
  {
    if (inValue == null)
    {
      return null;
    }
    for (int i = 0; i < inValue.length; i++)
    {
      inValue[i] = decrypt(inKey, inValue[i]);
    }
    return inValue;
  }

  public static String[] encryptArray(byte[] inKey, String[] inValue)
  {
    if (inValue == null)
    {
      return null;
    }
    for (int i = 0; i < inValue.length; i++)
    {
      inValue[i] = encrypt(inKey, inValue[i]);
    }
    return inValue;
  }
}