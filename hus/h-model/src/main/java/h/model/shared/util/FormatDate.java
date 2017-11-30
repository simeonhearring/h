package h.model.shared.util;

import java.util.Date;

public interface FormatDate
{
  String format(String inPattern, Date inValue);

  Date parse(String inPattern, String inValue);
}