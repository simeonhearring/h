package h.khall.client.model;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

import h.model.shared.util.FormatDate;

public class ClientFormat implements FormatDate
{
  @Override
  public String format(String inPattern, Date inValue)
  {
    return DateTimeFormat.getFormat(inPattern).format(inValue);
  }

  @Override
  public Date parse(String inPattern, String inValue)
  {
    return DateTimeFormat.getFormat(inPattern).parse(inValue);
  }
}