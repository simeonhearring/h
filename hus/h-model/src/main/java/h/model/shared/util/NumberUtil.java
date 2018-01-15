package h.model.shared.util;

import java.math.BigDecimal;

public final class NumberUtil
{
  private NumberUtil()
  {
  }

  public static int intValue(Integer inValue)
  {
    return inValue != null ? inValue.intValue() : 0;
  }

  public static boolean isNumber(String inValue)
  {
    boolean ret = false;

    if (inValue != null && !"".equals(inValue.trim()))
    {
      try
      {
        Long.valueOf(inValue);
        ret = true;
      }
      catch (Exception e)
      {
        ret = false;
      }
    }

    return ret;
  }

  public static boolean isNumberOrPositive(String inValue)
  {
    boolean ret = false;

    if (inValue != null && !"".equals(inValue.trim()))
    {
      try
      {
        inValue = inValue.replaceAll("\\+", "");
        Long.valueOf(inValue);
        ret = true;
      }
      catch (Exception e)
      {
        ret = false;
      }
    }

    return ret;
  }

  public static boolean isNumberOrEmpty(String inValue)
  {
    return "".equals(inValue) || isNumber(inValue);
  }

  public static Long toLong(String inValue)
  {
    Long ret = null;

    try
    {
      ret = Long.valueOf(inValue);
    }
    catch (Exception e)
    {
      // do nothing
    }

    return ret;
  }

  public static Long toLong(String inValue, Long inDefault)
  {
    Long ret = inDefault;

    try
    {
      ret = Long.valueOf(inValue);
    }
    catch (Exception e)
    {
      // do nothing
    }

    return ret;
  }

  public static Double toDouble(String inValue)
  {
    Double ret = null;

    try
    {
      ret = Double.valueOf(inValue);
    }
    catch (Exception e)
    {
      // do nothing
    }

    return ret;
  }

  public static Integer toInteger(String inValue)
  {
    Integer ret = null;

    try
    {
      ret = Integer.valueOf(inValue);
    }
    catch (Exception e)
    {
      // do nothing
    }

    return ret;
  }

  public static Long toLong(BigDecimal inBigDecimal)
  {
    return inBigDecimal == null ? null : inBigDecimal.longValue();
  }

  public static Integer toInteger(BigDecimal inBigDecimal)
  {
    return inBigDecimal == null ? null : inBigDecimal.intValue();
  }

  public static Long toLong(Integer inInteger)
  {
    return inInteger == null ? null : inInteger.longValue();
  }

  public static Boolean toBoolean(BigDecimal inBigDecimal)
  {
    return inBigDecimal == null ? null : 1 == inBigDecimal.intValue();
  }

  public static Double toDouble(BigDecimal inBigDecimal)
  {
    return inBigDecimal == null ? null : inBigDecimal.doubleValue();
  }

  public static boolean isGreater(Long inValue, Long inGreaterThan)
  {
    if (inGreaterThan == null || inValue == null)
    {
      return false;
    }

    return inGreaterThan.compareTo(inValue) < 0;
  }

  public static boolean isEqual(Integer inValueA, Integer inValueB)
  {
    if (inValueA == null || inValueB == null)
    {
      return false;
    }

    return inValueA.compareTo(inValueB) == 0;
  }

  public static double todouble(String inString)
  {
    double ret = 0.0;

    try
    {
      ret = Double.valueOf(inString);
    }
    catch (Exception e)
    {
    }

    return ret;
  }

  public static Long toLong(Object inObject)
  {
    Long ret = null;

    if (inObject != null)
    {
      if (inObject instanceof Long)
      {
        ret = (Long) inObject;
      }
      else if (inObject instanceof BigDecimal)
      {
        ret = ((BigDecimal) inObject).longValue();
      }
      else
      {
        throw new RuntimeException("object type not handled " + inObject.getClass().getName());
      }
    }

    return ret;
  }

  public static BigDecimal toBigDecimal(String inString)
  {
    BigDecimal ret = BigDecimal.valueOf(0L);

    try
    {
      ret = new BigDecimal(inString);
    }
    catch (Exception e)
    {
    }

    return ret;
  }

  public static Double toDouble(Integer inValue)
  {
    return inValue != null ? inValue.doubleValue() : null;
  }

  public static int toInt(String inValue, int inDefault)
  {
    Integer value = toInteger(inValue);
    return value != null ? value : inDefault;
  }
}
