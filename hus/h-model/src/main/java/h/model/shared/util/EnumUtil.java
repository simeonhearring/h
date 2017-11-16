package h.model.shared.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class EnumUtil
{
  protected EnumUtil()
  {
  }

  public static <T extends Enum<?>> T valueOf(String inString, T[] inValues)
  {
    if (inString == null || "".equals(inString.trim()))
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

  public static <T extends Enum<?>> T valueOf(String inString, T[] inValues, T inDefault)
  {
    T ret = valueOf(inString, inValues);
    return ret != null ? ret : inDefault;
  }

  public static String name(Enum<?> inEnum)
  {
    return inEnum != null ? inEnum.name() : null;
  }

  public static String name(Enum<?> inEnum, String inDefault)
  {
    return inEnum != null ? inEnum.name() : inDefault;
  }

  public static <T extends Enum<?>> List<T> toList(String inString, T[] inValues)
  {
    List<T> ret = new ArrayList<>();

    if (inString != null && inString.indexOf(",") != -1)
    {
      for (String value : inString.split(","))
      {
        T valueOf = valueOf(value, inValues);
        if (valueOf != null)
        {
          ret.add(valueOf);
        }
      }
    }

    return ret.size() == 0 ? null : ret;
  }

  // public static <T extends Enum<?>> T toEnum(IsOption inOption, T[] inValues)
  // {
  // if (inOption == null)
  // {
  // return null;
  // }
  // return valueOf(inOption.value(), inValues);
  // }
  //
  // public static <T extends Enum<?>> IsOption toOption(final T inEnum)
  // {
  // return toOption(inEnum, "");
  // }
  //
  // public static <T extends Enum<?>> IsOption toOption(final T inEnum, final
  // String inDisplay)
  // {
  // if (inEnum == null)
  // {
  // return null;
  // }
  //
  // return new IsOption()
  // {
  // @Override
  // public String value()
  // {
  // return inEnum.name();
  // }
  //
  // @Override
  // public String item()
  // {
  // return inDisplay;
  // }
  // };
  // }

  public static <T extends Enum<?>> String toString(List<T> inEnum)
  {
    String ret = null;

    if (inEnum != null && inEnum.size() > 0)
    {
      StringBuilder sb = new StringBuilder();
      for (T t : inEnum)
      {
        sb.append(name(t)).append(",");
      }
      ret = sb.toString();
    }

    return ret;
  }

  public static <T extends Enum<T>> boolean isValue(T inValue, Collection<T> inValues)
  {
    boolean ret = false;

    if (inValues != null)
    {
      for (Enum<T> value : inValues)
      {
        ret |= inValue.equals(value);
      }
    }

    return ret;
  }

  @SafeVarargs
  public static <T extends Enum<T>> boolean isValue(T inValue, T... inValues)
  {
    boolean ret = false;

    if (inValues != null)
    {
      for (Enum<T> value : inValues)
      {
        ret |= inValue.equals(value);
      }
    }

    return ret;
  }

  // public static <T extends Enum<T>> String asString(EnumLookup inLookup,
  // List<T> inEnums)
  // {
  // StringBuilder sb = new StringBuilder();
  // if (inEnums != null)
  // {
  // for (Enum<?> value : inEnums)
  // {
  // sb.append(inLookup.getCode(value)).append(",");
  // }
  // }
  // return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
  // }
}
