package h.tool.util;

public final class EnumUtil
{
  private EnumUtil()
  {
    super();
  }

  public static <T extends Enum<?>> T valueOf(String inString, T[] inValues)
  {
    if ((inString == null) || "".equals(inString.trim()))
    {
      return null;
    }

    T ret = null;
    for (T value : inValues)
    {
      if (value.name().equals(inString))
      {
        ret = value;
      }
    }
    return ret;
  }

  public static <T extends Enum<?>> T valueOf(Enum<?> inEnum, T[] inValues)
  {
    if (inEnum == null)
    {
      return null;
    }

    String text = inEnum.name();
    T ret = null;
    for (T value : inValues)
    {
      if (value.name().equals(text))
      {
        ret = value;
      }
    }
    return ret;
  }

  public static <T extends Enum<?>> T valueOf(String inString, T[] inValues, T inDefault)
  {
    T ret = valueOf(inString, inValues);
    return (ret != null ? ret : inDefault);
  }

  public static String name(Enum<?> inEnum)
  {
    return inEnum != null ? inEnum.name() : null;
  }

  public static String name(Enum<?> inEnum, String inDefault)
  {
    return inEnum != null ? inEnum.name() : inDefault;
  }

  public static boolean isOneOf(Enum<?> inValue, Enum<?>... inValues)
  {
    boolean ret = false;
    for (Enum<?> value : inValues)
    {
      ret |= value.equals(inValue);
    }
    return ret;
  }

  public static <T extends Enum<?>> T ordinalOf(Integer inOrdinal, T[] inValues)
  {
    if (inOrdinal == null || inOrdinal < 0)
    {
      return null;
    }
    return inValues[inOrdinal];
  }

  public static Integer ordinal(Enum<?> inEnum)
  {
    return inEnum == null ? null : inEnum.ordinal();
  }
}