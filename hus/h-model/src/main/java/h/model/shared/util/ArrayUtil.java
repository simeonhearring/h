package h.model.shared.util;

public final class ArrayUtil
{
  public static <T> boolean containsOrNull(T[] inArray, T inObject)
  {
    return inArray == null || contains(inArray, inObject);
  }

  public static <T> boolean contains(T[] inArray, T inObject)
  {
    boolean ret = false;
    for (T value : inArray)
    {
      ret |= value.equals(inObject);
    }
    return ret;
  }

  @SafeVarargs
  public static <T> T[] toArray(T... inTs)
  {
    return inTs;
  }
}