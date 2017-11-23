package h.model.shared.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class MapUtilTest
{
  @Test
  public void test()
  {
    List<Integer> list = new ArrayList<>();
    list.add(5);
    list.add(15);
    list.add(10);
    list.add(25);

    Map<Integer, Integer> results = MapUtil.organize(list);

    Assert.assertEquals(5, results.get(0).intValue());
    Assert.assertEquals(10, results.get(1).intValue());
    Assert.assertEquals(15, results.get(2).intValue());
    Assert.assertEquals(25, results.get(3).intValue());
  }
}