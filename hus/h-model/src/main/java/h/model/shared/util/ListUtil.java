package h.model.shared.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

  public static <T> List<T> subReverse(List<T> inList, int inMax)
  {
    List<T> ret = new ArrayList<>();
  
    if (inList.size() > 0)
    {
      int size = inList.size();
      int min = size < inMax ? 0 : size - inMax;
      int i = size - 1;
  
      for (; i >= min; i--)
      {
        ret.add(inList.get(i));
      }
    }
  
    return ret;
  }
}