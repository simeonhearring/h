package h.model.shared.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

  /*
   * Organizes collection of numbers to ascending order by 0, 1, 2, etc.
   */
  public static Map<Integer, Integer> organize(Collection<Integer> inCollection)
  {
    Map<Integer, Integer> keys = new HashMap<>();
    List<Integer> list = new ArrayList<>(inCollection);
    Collections.sort(list);
    for (int i = 0; i < list.size(); i++)
    {
      keys.put(i, list.get(i));
    }
    return keys;
  }
}