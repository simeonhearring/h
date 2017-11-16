package h.model.shared.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil
{
  public static Map<String, String> toMap(String inUrl)
  {
    Map<String, String> ret = new HashMap<>();

    String[] values = inUrl.split("&");
    for (String value : values)
    {
      String[] values1 = value.split("=");
      ret.put(values1[0], values1[1]);
    }

    return ret;
  }
}