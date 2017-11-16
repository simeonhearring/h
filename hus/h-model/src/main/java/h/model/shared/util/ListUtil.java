package h.model.shared.util;

import java.util.Date;

public class ListUtil
{
  public static int compareTo(Object inValue1, Object inValue2)
  {
    int ret = 0;
    if (inValue1 instanceof String)
    {
      String v1 = inValue1 == null ? "" : String.valueOf(inValue1);
      String v2 = inValue2 == null ? "" : String.valueOf(inValue2);
      ret = v1.compareTo(v2);
    }
    else if (inValue1 instanceof Date)
    {
      Long v1 = inValue1 == null ? 0 : ((Date) inValue1).getTime();
      Long v2 = inValue2 == null ? 0 : ((Date) inValue2).getTime();
      ret = v1.compareTo(v2);
    }
    else if (inValue1 instanceof Long || inValue1 instanceof Integer)
    {
      Long v1 = inValue1 == null ? 0 : Long.valueOf(inValue1 + "");
      Long v2 = inValue2 == null ? 0 : Long.valueOf(inValue2 + "");
      ret = v1.compareTo(v2);
    }
    else
    {
      throw new RuntimeException("unhandled type");
    }
    return ret;
  }
}